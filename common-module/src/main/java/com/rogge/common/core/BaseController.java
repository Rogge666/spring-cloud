package com.rogge.common.core;

import com.rogge.common.conf.redis.RedisDao;
import com.rogge.common.conf.redis.SessionUserInfo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

    protected static final String PAGE_SIZE = "10";
    /**
     * 默认每页显示条数
     */

    @Resource
    public RedisDao mRedisDao;
    @Resource
    public SessionUserInfo mSessionUserInfo;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
