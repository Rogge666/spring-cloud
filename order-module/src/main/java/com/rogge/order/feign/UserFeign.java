package com.rogge.order.feign;

import com.rogge.order.model.vo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/11/4 0004.
 * @since 1.0.0
 */
@FeignClient(name = "user-module", fallback = UserFeign.UserFeignFallback.class)
public interface UserFeign {
    @RequestMapping("/user/detail")
    User detail(@RequestParam("id") Long id);

    @Component
    class UserFeignFallback implements UserFeign {

        @Override
        public User detail(Long id) {
            User lUser = new User();
            lUser.setUsername("请求个人数据失败");
            return lUser;
        }
    }
}
