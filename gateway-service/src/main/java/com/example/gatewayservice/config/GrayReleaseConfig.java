package com.example.gatewayservice.config;

import com.example.gatewayservice.entity.GrayRelease;
import com.example.gatewayservice.mapper.GrayReleaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableScheduling
public class GrayReleaseConfig {

    @Autowired
    private GrayReleaseMapper grayReleaseMapper;

    private Map<String, GrayRelease> grayReleaseMap = new ConcurrentHashMap<String, GrayRelease>();

    public Map<String, GrayRelease> getGrayReleaseMap() {
        return grayReleaseMap;
    }

    @Scheduled(fixedRate = 10000)
    private void setGrayReleaseMap(){
       // System.out.println("定时刷新灰度发布表");
        List<GrayRelease> grayReleases = grayReleaseMapper.queryAllGrayRelease();
        for (GrayRelease grayRelease : grayReleases) {
            grayReleaseMap.put(grayRelease.getPath(),grayRelease);
        }
    }

}
