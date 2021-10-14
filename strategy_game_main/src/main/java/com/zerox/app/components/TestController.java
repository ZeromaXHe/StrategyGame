package com.zerox.app.components;

import com.zerox.app.entity.Result;
import com.zerox.app.exception.annotation.ExceptionHandled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 16:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Controller("TestController")
public class TestController {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @ExceptionHandled
    public Result<String> test() {
        return new Result<>(testService.test());
    }
}
