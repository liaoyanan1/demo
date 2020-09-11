package com.example.gatewayservice.mapper;

import com.example.gatewayservice.entity.GrayRelease;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GrayReleaseMapper {

    @Select("select * from gray_release ")
    List<GrayRelease> queryAllGrayRelease();
}
