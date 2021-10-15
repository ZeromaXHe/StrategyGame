package com.zerox.controller;

import com.zerox.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 15:01
 * @Description: TestController的单元测试
 * @ModifiedBy: zhuxi
 */
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MainController mainController;

    @Test
    public void testTest() {
        System.out.println(JacksonUtil.toJson(mainController.getAccountHolderName()));
    }
}
