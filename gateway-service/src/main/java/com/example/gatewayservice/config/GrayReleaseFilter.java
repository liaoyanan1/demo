package com.example.gatewayservice.config;

import com.example.gatewayservice.entity.GrayRelease;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

/** @author lyn
 * TODO 通过过滤器实现灰度发布
 * @date 2020/9/10 16:33
*/
@Configuration
public class GrayReleaseFilter  extends ZuulFilter {
     private  Logger  Log = LoggerFactory.getLogger(GrayReleaseFilter.class);
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private GrayReleaseConfig grayReleaseConfig;
    /*
    默认10%
     */
    private int percent = 10;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {

        String[] path = httpServletRequest.getServletPath().split("/");
        Map<String, GrayRelease> grayReleaseMap = grayReleaseConfig.getGrayReleaseMap();
        if (grayReleaseMap==null){
            return false;
        }
        if (grayReleaseMap.containsKey(path[1])){
            GrayRelease grayRelease = grayReleaseMap.get(path[1]);
            if (grayRelease.getEnableGrayRelease()==1){
                Log.info("启动灰度发布"+path[1]);
                //System.out.println("启动灰度发布"+path[1]);
                percent = grayRelease.getPercent();
                return true;
            }
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        Random random = new Random();
        int rp = random.nextInt(100);
        if (rp>=0 && rp< percent ){
            RibbonFilterContextHolder.getCurrentContext().add("version", "new");
        }else {
            RibbonFilterContextHolder.getCurrentContext().add("version", "current");
        }
        return null;
    }


}
