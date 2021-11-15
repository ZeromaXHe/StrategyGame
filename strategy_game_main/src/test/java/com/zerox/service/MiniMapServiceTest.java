package com.zerox.service;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 15:29
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class MiniMapServiceTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        WritableImage wi = new WritableImage(100, 100);
        PixelWriter pw = wi.getPixelWriter();
        // 证明图片x轴和y轴的走向
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i < 30 && j < 70) {
                    pw.setColor(i, j, Color.GREEN);
                } else {
                    pw.setColor(i, j, Color.BLACK);
                }
            }
        }
        an.getChildren().add(new ImageView(wi));

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MiniMapServiceTest");
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.show();
    }
}
