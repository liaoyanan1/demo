package com.example.bservice.ApiInterface;



import com.example.bservice.config.AServiceClientFallbackFactory;
import com.example.bservice.config.AServiceClientFallbackImpl;
import com.example.bservice.config.FeignConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
/** @author lyn
 * TODO feign调用eureka注册的a-service 携带oauth2头访问（FeignConfig） 断路由（AServiceClientFallbackImpl）
 * @date 2020/7/28 14:37
*/

@FeignClient(name = "a-service",configuration = FeignConfig.class
,fallback = AServiceClientFallbackImpl.class
/*,fallbackFactory = AServiceClientFallbackFactory.class*/)
public interface AService {

    @CachePut("a-service")
    @GetMapping("/A")
    String getA();
}
