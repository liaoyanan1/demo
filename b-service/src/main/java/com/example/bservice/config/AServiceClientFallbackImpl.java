package com.example.bservice.config;

import com.example.bservice.ApiInterface.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
/** @author lyn
 * TODO hystrix断路由单接口实现fallback
 * @date 2020/7/28 14:39
*/
@Component
public class AServiceClientFallbackImpl implements AService {

    @Autowired
    CacheManager cacheManager;

    @Override
    public String getA(/*String id*/) {
      //  Cache.ValueWrapper aService = cacheManager.getCache("aServiceCache").get(/*id*/);
     //   if (aService!=null){
     //       return  (String)aService.get();
    //    }
        return "a";
    }
}
