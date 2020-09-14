package com.example.aservice.service.impl;

import com.example.aservice.mapper.AccountDao;
import com.example.aservice.service.AccountService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountDao accountDao;


    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->扣减账户开始account中");
        accountDao.decrease(userId,money);
        LOGGER.info("------->扣减账户结束account中");
      //  throw new RuntimeException("抛出错误");

    }
}
