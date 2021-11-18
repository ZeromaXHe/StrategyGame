package com.zerox.service.impl;

import com.zerox.service.MainService;
import org.springframework.stereotype.Component;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 20:06
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Component("MainServiceImpl")
public class MainServiceImpl implements MainService {
    @Override
    public String getAccountHolderName() {
        throw new RuntimeException("一个异常");
//        return "test";
    }
}
