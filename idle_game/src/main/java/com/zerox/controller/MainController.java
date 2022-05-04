package com.zerox.controller;

import com.zerox.engine.GameLoopTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
    private ProgressBar myHpBar;
    @FXML
    private Label myHpLabel;
    @FXML
    private ProgressBar myAttackIntervalBar;
    @FXML
    private Label myAttackIntervalLabel;
    @FXML
    private ProgressBar enemyHpBar;
    @FXML
    private Label enemyHpLabel;
    @FXML
    private ProgressBar enemyAttackIntervalBar;
    @FXML
    private Label enemyAttackIntervalLabel;
    @FXML
    private Button foodButton;
    @FXML
    private CheckBox autoSwapFood;
    @FXML
    private Button pauseButton;

    private BooleanProperty start = new SimpleBooleanProperty(false);
    private FloatProperty myHp = new SimpleFloatProperty(1000f);
    private FloatProperty myMaxHp = new SimpleFloatProperty(1000f);
    private FloatProperty myAttackInterval = new SimpleFloatProperty(0f);
    private FloatProperty myMaxAttackInterval = new SimpleFloatProperty(3f);
    private FloatProperty enemyHp = new SimpleFloatProperty(100f);
    private FloatProperty enemyMaxHp = new SimpleFloatProperty(100f);
    private FloatProperty enemyAttackInterval = new SimpleFloatProperty(0f);
    private FloatProperty enemyMaxAttackInterval = new SimpleFloatProperty(1.4f);

    private final float MY_ATTACK_POINT = 48;
    private final float ENEMY_ATTACK_POINT = 13;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myHp.set(0f);
        myHpBar.progressProperty().bind(myHp.divide(myMaxHp));
        enemyHpBar.progressProperty().bind(enemyHp.divide(enemyMaxHp));
        myAttackIntervalBar.progressProperty().bind(myAttackInterval.divide(myMaxAttackInterval));
        enemyAttackIntervalBar.progressProperty().bind(enemyAttackInterval.divide(enemyMaxAttackInterval));

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                float myNewAttackInterval = myAttackInterval.get() + secondsSinceLastFrame;
                myAttackInterval.set(myNewAttackInterval);
                if (myNewAttackInterval > myMaxAttackInterval.get()) {
                    logger.info("player attack! before value:{}", myNewAttackInterval);
                    myAttackInterval.set(myNewAttackInterval % myMaxAttackInterval.get());
                    logger.info("after value:{}", myAttackInterval.getValue());

                    float enemyNewHp = Math.max(enemyHp.get() - MY_ATTACK_POINT, 0);
                    if (enemyNewHp == 0) {
                        enemyNewHp = enemyMaxHp.get();
                        logger.info("enemy dead!");
                        enemyAttackInterval.set(0);
                    }
                    enemyHp.set(enemyNewHp);
                }

                float enemyNewAttackInterval = enemyAttackInterval.get() + secondsSinceLastFrame;
                enemyAttackInterval.set(enemyNewAttackInterval);
                if (enemyNewAttackInterval > enemyMaxAttackInterval.get()) {
                    logger.info("enemy attack! before value:{}", enemyNewAttackInterval);
                    enemyAttackInterval.set(enemyNewAttackInterval % enemyMaxAttackInterval.get());
                    logger.info("after value:{}", enemyAttackInterval.getValue());

                    float myNewHp = Math.max(myHp.get() - ENEMY_ATTACK_POINT, 0);
                    if (myNewHp == 0) {
                        myNewHp = myMaxHp.get();
                        logger.info("player dead!");
                        myAttackInterval.set(0);
                    }
                    myHp.set(myNewHp);
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

        myHpLabel.textProperty().bind(Bindings.concat(myHp, "/", myMaxHp, " HP"));
        myAttackIntervalLabel.textProperty().bind(Bindings.concat("Attack Interval: ", myMaxAttackInterval, " s"));
        enemyHpLabel.textProperty().bind(Bindings.concat(enemyHp, "/", enemyMaxHp, " HP"));
        enemyAttackIntervalLabel.textProperty().bind(Bindings.concat("Attack Interval: ", enemyMaxAttackInterval, " s"));
    }

    @FXML
    private void onFoodButtonClick() {
        myHp.set(Math.min(myMaxHp.get(), myHp.get() + 100));
        myAttackInterval.set(0);
    }

    @FXML
    private void onPauseButtonClick() {
        start.setValue(!start.getValue());
        logger.info("timer play: {}, progress: {}", start.getValue(), myHp.getValue());
    }
}
