package com.rogge.common.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*60)
public class SessionConfig {
    //EnableRedisHttpSession为设置session失效时间默认半小时
}