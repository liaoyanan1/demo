package com.example.bservice.service.impl;


import com.example.bservice.ApiInterface.AService;
import com.example.bservice.mapper.StorageDao;
import com.example.bservice.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;


@Service("storageServiceImpl")
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageDao storageDao;
    @Autowired
    private AService aService;
    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("------->扣减库存结束");
        LOGGER.info("------->调用服务A开始");
        if ("降级".equals(aService.decrease(productId,new BigDecimal("10")))){
            System.out.println("降级");
            Assert.notNull(null,"返回了降级方法还需回滚");

        }
        LOGGER.info("------->调用服务A结束");
    }
}
