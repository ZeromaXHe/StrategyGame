package com.zerox.app.components;

import com.zerox.app.entity.Result;
import com.zerox.app.exception.annotation.ExceptionHandled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 16:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
@RestController("TestController")
public class TestController {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @ExceptionHandled
    @RequestMapping("/test")
    public Result<String> test() {
        return new Result<>(testService.test());
    }
}
