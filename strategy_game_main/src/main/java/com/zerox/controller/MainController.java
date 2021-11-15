package com.zerox.controller;

import com.zerox.service.MainService;
import com.zerox.entity.Result;
import com.zerox.exception.annotation.ExceptionHandled;
import com.zerox.service.MiniMapService;
import javafx.scene.image.Image;
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
    private MiniMapService miniMapService;

    @Autowired
    public void setTestService(MainService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setMiniMapService(MiniMapService miniMapService) {
        this.miniMapService = miniMapService;
    }

    @ExceptionHandled
    public Result<String> getAccountHolderName() {
        return new Result<>(testService.getAccountHolderName());
    }

    public Image getMiniMapImage() {
        return miniMapService.getMiniMapImage();
    }

    public Image getNewMiniMap(int x, int y) {
        return miniMapService.getNewMiniMap(x, y);
    }
}
