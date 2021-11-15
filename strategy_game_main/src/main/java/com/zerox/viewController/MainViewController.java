package com.zerox.viewController;

import com.zerox.constant.MainConstant;
import com.zerox.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: zhuxi
 * @Time: 2021/10/15 15:58
 * @Description: mvc体系下的ViewController，和Spring三层体系下的后端Controller区分开来
 * 参考：https://edencoding.com/mvc-in-javafx/
 * @ModifiedBy: zhuxi
 */
@Controller("MainViewController")
public class MainViewController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);

    // Back-end Controller
    private MainController mainController;

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane mapPane;
    @FXML
    private AnchorPane infoPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < MainConstant.MINIMAP_VIEW_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_VIEW_Y; j++) {
                mainPane.getChildren().add(new MainPaneButton("", i, j, i, j));
            }
        }

        ImageView imageView = new ImageView(mainController.getMiniMapImage());
        mapPane.getChildren().add(imageView);
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);

        imageView.setOnMouseClicked(event -> {
            logger.info("x: {}, y: {}, sceneX: {}, sceneY: {}, screenX: {}, screenY: {}",
                    event.getX(), event.getY(),
                    event.getSceneX(), event.getSceneY(),
                    event.getScreenX(), event.getScreenY());
            imageView.setImage(mainController.getNewMiniMap((int) event.getX(), (int) event.getY()));
        });

//        GameLoopTimer timer = new GameLoopTimer() {
//            @Override
//            public void tick(float secondsSinceLastFrame) {
//                mainController.getAccountHolderName();
//            }
//        };
//        timer.start();
    }

    static class MainPaneButton extends Button {
        private int x;
        private int y;
        private int mapX;
        private int mapY;

        public MainPaneButton(String text, int x, int y, int mapX, int mapY) {
            super(text);
            this.x = x;
            this.y = y;
            this.mapX = mapX;
            this.mapY = mapY;
            // TODO: 20后续可以提取成常量
            this.setPrefHeight(20);
            this.setPrefWidth(20);
            AnchorPane.setTopAnchor(this, 20.0 * x);
            AnchorPane.setLeftAnchor(this, 20.0 * y);
            this.setStyle("-fx-border-color: #FFFFFF;-fx-border-style: solid;-fx-border-width: 1;-fx-border-radius: 2;");

        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getMapX() {
            return mapX;
        }

        public void setMapX(int mapX) {
            this.mapX = mapX;
        }

        public int getMapY() {
            return mapY;
        }

        public void setMapY(int mapY) {
            this.mapY = mapY;
        }
    }
}
