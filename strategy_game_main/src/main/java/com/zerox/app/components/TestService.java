package com.zerox.app.components;

import org.springframework.stereotype.Component;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 20:06
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Component("TestService")
public class TestService {
    public String test() {
        return "test";
    }
}
