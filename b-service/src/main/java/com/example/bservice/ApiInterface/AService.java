package com.example.bservice.ApiInterface;



import com.example.bservice.config.AServiceClientFallbackImpl;
import com.example.bservice.config.FeignConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/** @author lyn
 * TODO feign调用eureka注册的a-service 携带oauth2头访问（FeignConfig） 断路由（AServiceClientFallbackImpl）
 * @date 2020/7/28 14:37
*/
@Component("aService")
@FeignClient(name = "a-service",configuration = FeignConfig.class
,fallback = AServiceClientFallbackImpl.class
/*,fallbackFactory = AServiceClientFallbackFactory.class*/)
public interface AService {

    @CachePut(value = "getA",key = "#id")
    @RequestMapping(value = "/a",method = RequestMethod.GET)
    String getA(@RequestParam("id") Integer id);

    @CachePut(value = "decrease",key = "#userId")
    @RequestMapping(value = "/decrease",method = RequestMethod.GET)
    String decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
