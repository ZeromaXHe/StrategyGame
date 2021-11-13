package com.zerox.test;

import com.zerox.engine.GameLoopTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2021/11/2 14:30
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class DemoTest extends Application implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(DemoTest.class);

    private SimpleStringProperty text = new SimpleStringProperty("0");
    private SimpleStringProperty fps = new SimpleStringProperty("0.0");
    private GameLoopTimer timer = new GameLoopTimer() {
        @Override
        public void tick(float secondsSinceLastFrame) {
            int num = Integer.parseInt(text.get());
            num++;
            text.set(String.valueOf(num));
            fps.set(String.valueOf(1 / secondsSinceLastFrame));
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error("sleep error: ", e);
            }
        }
    };

    // 貌似 public 的就不需要注解 @FXML？
    @FXML
    private TextField textField;
    @FXML
    private TextField fpsTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("../fxml/demoFxml.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        logger.info("loader.load()");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show();

        // 这里无效了，不知道为什么。
        // 放在 initialize() 里面倒是可以
        // 只好新加一个按钮来开始
//        timer.start();
    }

    @FXML
    public void start() {
        timer.start();
        logger.info("timer.start()");
    }

    @FXML
    public void pause() {
        timer.pause();
        logger.info("timer.pause()");
    }

    @FXML
    public void play() {
        timer.play();
        logger.info("timer.play()");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.textProperty().bind(text);
        fpsTextField.textProperty().bind(fps);
        logger.info("initialize()");
    }
}
