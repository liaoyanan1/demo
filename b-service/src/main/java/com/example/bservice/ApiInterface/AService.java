package com.example.bservice.ApiInterface;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "a-service")
public interface AService {
    @GetMapping("/A/A")
    String getA();
}
