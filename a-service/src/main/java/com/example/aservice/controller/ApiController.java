package com.example.aservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author lyn
 * TODO 开放接口api
 * @date 2020/7/28 14:33
*/
@Api(tags = "开放Api 提供外部接口")
@RequestMapping("/api")
@RestController
public class ApiController {

    @HystrixCommand(fallbackMethod = "aerror")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id"
                            ,value = "传入id"
                            ,required = true
                            ,paramType = "path"
                            ,dataType = "Integer"
                          //  ,defaultValue = "0"
            )
    )
    @ApiOperation("获取A+传入id")
    @GetMapping("/get/{id}")
    public String a(@PathVariable(value = "id") Integer id){
        return  "A"+id;
    }

    public String aerror(@PathVariable(value = "id") Integer id){

            return  "a"+id;
    }
}
