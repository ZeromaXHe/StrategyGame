package com.zerox.app.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

        Button button = new Button(testController.test());
        an.getChildren().add(button);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}
