package com.rogge.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.rogge.common.conf.redis.SessionUserInfo;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.ResponseCode;
import com.rogge.common.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/11/7.
 * @since 1.0.0
 */
@Component
public class SessionFilter extends ZuulFilter {

    @Autowired
    private SessionRepository<?> repository;

    @Resource
    public SessionUserInfo mSessionUserInfo;

    private static final String[] ignoreUrl = {"/user/login","/user/list"};

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpSession httpSession = ctx.getRequest().getSession();
        Session session = repository.getSession(httpSession.getId());
        ctx.addZuulRequestHeader("Cookie", "SESSION=" + session.getId());

        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String requestUri = request.getRequestURI();
        //当前请求为不需要检查权限的url配置时
        if(isStartWith(requestUri)){
            return null;
        }
        User user = mSessionUserInfo.getCurrentSessionUser(User.class);
        if (user == null){
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ApiResponse apiResponse = ApiResponse.creatFail(ResponseCode.LoginRegister.NOLOGIN);
            response.setContentType("application/json;charset=UTF-8");
            try {
                response.getWriter().print(JSON.toJSONString(apiResponse, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            mSessionUserInfo.setSessionUser(user);
        }
        return null;
    }

    /**
     * 是否配置的不用检查的url权限
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : ignoreUrl) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

}
