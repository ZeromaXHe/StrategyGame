package com.zerox.controller;

import com.zerox.engine.GameLoopTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: zhuxi
 * @Time: 2021/11/22 10:20
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Controller("MainController")
public class MainController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button button;

    private BooleanProperty start = new SimpleBooleanProperty(false);
    private FloatProperty value = new SimpleFloatProperty(0f);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        value.set(0f);
        progressBar.progressProperty().bind(value);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                if (secondsSinceLastFrame < 0.1) {
                    // secondsSinceLastFrame 在暂停后那一帧会是和暂停前的时间差，第一次开始时也会是现在的时间戳
                    // 所以只使用小于0.1的值
                    value.set(value.get() + secondsSinceLastFrame / 10);
                }
                if (value.get() > 1) {
                    logger.info("progress complete! before value:{}", value.getValue());
                    value.set(value.get() % 1);
                    logger.info("after value:{}", value.getValue());
                }
            }
        };

        start.addListener((o, ov, nv) -> {
            if (nv) {
                if (!timer.isActive()) {
                    timer.start();
                } else {
                    timer.play();
                }
            } else {
                timer.pause();
            }
        });
    }

    @FXML
    private void onButtonClick() {
        start.setValue(!start.getValue());
        logger.info("timer play: {}, progress: {}", start.getValue(), value.getValue());
    }
}
