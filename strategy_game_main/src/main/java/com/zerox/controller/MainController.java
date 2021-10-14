package com.zerox.controller;

import com.zerox.service.MainService;
import com.zerox.entity.Result;
import com.zerox.exception.annotation.ExceptionHandled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 16:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Controller("MainController")
public class MainController {
    private MainService testService;

    @Autowired
    public void setTestService(MainService testService) {
        this.testService = testService;
    }

    @ExceptionHandled
    public Result<String> getButtonName() {
        return new Result<>(testService.getButtonName());
    }
}
