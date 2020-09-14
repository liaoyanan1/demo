package com.example.bservice;

import com.example.bservice.ApiInterface.AService;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.TimeUnit;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static io.specto.hoverfly.junit.dsl.matchers.HoverflyMatchers.startsWith;

//@SpringBootTest
class BServiceApplicationTests {

    Logger logger =  LoggerFactory.getLogger(BServiceApplicationTests.class);

   // @Autowired
    AService aService;

   // @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inSimulationMode(dsl(
                    service("a-service:8080")
                            .andDelay(200, TimeUnit.MILLISECONDS).forAll()
                            .get(startsWith("/a-service/A"))
                            .willReturn(success("", "application/json")),
                    service("a-service:8081")
                            .andDelay(10000, TimeUnit.MILLISECONDS).forAll()
                            .get(startsWith("/a-service/A"))
                            .willReturn(success("", "application/json")),
                    service("a-service:8082")
                            .andDelay(50, TimeUnit.MILLISECONDS).forAll()
                            .get(startsWith("/a-service/A"))
                            .willReturn(success("", "application/json"))))
            .printSimulationData();


    //@Test
    void contextLoads() {
        for (int i = 0; i < 1000; i++) {
            String a = aService.getA(i);
            logger.info("get {}",a);
        }

    }

}
