package com.example.aservice.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(Long userId, BigDecimal money);
}
