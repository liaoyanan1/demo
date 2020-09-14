package com.example.bservice.config;

import com.example.bservice.ApiInterface.AService;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/** @author lyn
 * TODO hystrix断路由工厂模式实现fallback
 * @date 2020/7/28 14:38
*/
//@Component
public class AServiceClientFallbackFactory  implements FallbackFactory<AService> {
    @Autowired
    CacheManager cacheManager;
    @Override
    public AService create(Throwable cause) {
        return new AService() {
            @Override
            public String getA(Integer id) {
                System.out.println("熔断");
                  Cache.ValueWrapper aService = cacheManager.getCache("aServiceA").get(id);
                if (aService!=null) {
                    System.out.println("缓存");
                    return (String) aService.get();
                }
                return "A";
            }

            @Override
            public String decrease(Long userId, BigDecimal money) {
                return "熔断";
            }
        };
    }
}
