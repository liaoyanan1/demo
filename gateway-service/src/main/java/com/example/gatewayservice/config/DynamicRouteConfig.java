package com.example.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/** @author lyn
 * TODO 配置注入bean 给spring容器
 * @date 2020/9/10 15:33
*/
@Configuration
@EnableScheduling
public class DynamicRouteConfig{

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties server;

    @Bean
    public DynamicRouteLocator routeLocator() {
        DynamicRouteLocator routeLocator = new DynamicRouteLocator(
                server.getServlet().getContextPath(), this.zuulProperties);
        return routeLocator;
    }


    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private RouteLocator routeLocator;

    @Scheduled(fixedRate = 5000)
    private void refreshRoute() {
        System.out.println("定时刷新路由表");
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }

}