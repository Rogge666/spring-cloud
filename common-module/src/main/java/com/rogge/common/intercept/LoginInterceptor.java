package com.rogge.common.intercept;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rogge.common.conf.redis.SessionUserInfo;
import com.rogge.common.core.ApiResponse;
import com.rogge.common.core.ResponseCode;
import com.rogge.common.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Resource
	private SessionUserInfo sessionUserInfo;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 未登录
		User user = sessionUserInfo.getCurrentSessionUser(User.class);
		if (user == null) {
			ApiResponse apiResponse = ApiResponse.creatFail(ResponseCode.LoginRegister.NOLOGIN);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JSON.toJSONString(apiResponse, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteEnumUsingToString));
			return false;
		}else {
			sessionUserInfo.setSessionUser(user);
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

}
