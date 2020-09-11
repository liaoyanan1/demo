package com.example.gatewayservice.mapper;


import com.example.gatewayservice.entity.GatewayApiRoute;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface GateWayApiRouteMapper {

    @Select("select * from gateway_api_route where enabled = true ")
    List<GatewayApiRoute> queryAllGateWayApiRoute();
}
