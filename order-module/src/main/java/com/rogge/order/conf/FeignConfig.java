package com.rogge.order.conf;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

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
@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
                if (!Strings.isNullOrEmpty(sessionId)) {
                    requestTemplate.header("Cookie", "SESSION=" + sessionId);
                }
            }
        };
    }

}
