package com.example.bservice.config;

import com.example.bservice.ApiInterface.AService;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

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
            public String getA(/*String id*/) {

          //        Cache.ValueWrapper aService = cacheManager.getCache("aServiceCache").get(id);
             //   if (aService!=null) {
         //           return (String) aService.get();
         //       }
                return "A";
            }
        };
    }
}
