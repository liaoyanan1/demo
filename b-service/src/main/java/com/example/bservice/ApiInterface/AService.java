package com.example.bservice.ApiInterface;



import com.example.bservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "a-service",configuration = FeignConfig.class)
public interface AService {
    @GetMapping("/A")
    String getA();
}
