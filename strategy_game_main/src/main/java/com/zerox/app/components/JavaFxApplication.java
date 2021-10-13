package com.zerox.app.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 15:52
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Component("JavaFxApplication")
public class JavaFxApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(JavaFxApplication.class);

    private static TestController testController;

    @Autowired
    public void setTestController(TestController testController) {
        JavaFxApplication.testController = testController;
    }

    @PostConstruct
    public void initMethod() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

//        Button button = new Button(testController.test());
        Button button = new Button("testController.test()");
        an.getChildren().add(button);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
        logger.info("JavaFxApplication started!");
    }

    /**
     * 经测试，JavaFxApplication 执行 stop() 方法后，Spring Boot 的 Tomcat服务器才会启动并开放端口供web调用
     *
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        logger.info("JavaFxApplication stopped!");
    }
}
