package com.zerox.test;

import com.zerox.engine.GameLoopTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/11/2 14:30
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class DemoTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        anchorPane.getChildren().add(vBox);
        AnchorPane.setTopAnchor(vBox, 30.0);
        AnchorPane.setLeftAnchor(vBox, 50.0);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        vBox.getChildren().add(hBox);

        SimpleStringProperty text = new SimpleStringProperty("0");
        SimpleStringProperty fps = new SimpleStringProperty("0.0");
        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                int num = Integer.parseInt(text.get());
                num++;
                text.set(String.valueOf(num));
                fps.set(String.valueOf(1 / secondsSinceLastFrame));
            }
        };

        Button pauseBtn = new Button("pause");
        pauseBtn.setOnAction(event -> timer.pause());
        Button playBtn = new Button("play");
        playBtn.setOnAction(event -> timer.play());

        TextField textField = new TextField();
        textField.setPrefWidth(100);
        textField.setEditable(false);
        textField.textProperty().bind(text);

        hBox.getChildren().addAll(pauseBtn, playBtn, textField);

        HBox fpsHBox = new HBox();
        fpsHBox.setSpacing(10);
        vBox.getChildren().add(fpsHBox);

        Label fpsLabel = new Label("fps");
        TextField fpsTextField = new TextField();
        fpsTextField.setPrefWidth(100);
        fpsTextField.setEditable(false);
        fpsTextField.textProperty().bind(fps);

        fpsHBox.getChildren().addAll(fpsLabel, fpsTextField);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show();

        timer.start();
    }
}
