package com.example.bservice.controller;


import com.example.bservice.ApiInterface.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/** @author lyn
 * TODO b服务接口
 * @date 2020/7/28 14:41
*/
@RestController
public class TestController {

    @Autowired
    AService aService;

    @GetMapping("/B")
    public String b(){
        
        return "b"+aService.getA();
    }
}
