package com.zerox.viewController;

import com.zerox.constant.MainConstant;
import com.zerox.controller.MainController;
import com.zerox.game.model.MapTile;
import com.zerox.game.view.MainPaneButton;
import com.zerox.util.JacksonUtil;
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

    private MainPaneButton[][] mainPaneButtons = new MainPaneButton[MainConstant.MINIMAP_VIEW_X][MainConstant.MINIMAP_VIEW_Y];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapTile[][] mapViewTiles = mainController.getMapViewTiles(0, 0, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y);

        for (int i = 0; i < MainConstant.MINIMAP_VIEW_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_VIEW_Y; j++) {
                // GUI的坐标系（横x纵y）和头脑里的二维数组（纵x横y）的坐标系不同
                // TODO: 颠倒坐标系的操作逻辑需要优化，现在仅是实现，但读起代码来很难理解
                MainPaneButton button = new MainPaneButton("", i, j, j, i,
                        mapViewTiles[j][i].getForce(), mapViewTiles[j][i].getPlayer());
                button.setOnAction(event -> {
                    logger.info("[button] x:{}, y:{}, mapX:{}, mapY:{}, force:{}, player:{}",
                            button.getX(), button.getY(),
                            button.getMapX(), button.getMapY(),
                            button.getForce(), button.getPlayer());
                    // JsonUtil 好像解析不了 Button 里面涉及到的 javafx.scene.SceneAntialiasing 这个类？太多继承的字段
//                    logger.info(JacksonUtil.toJson(event.getSource()));
                });
                mainPane.getChildren().add(button);
                mainPaneButtons[i][j] = button;
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
            int x = (int) event.getX();
            int y = (int) event.getY();
            MapTile[][] mapViewTilesOnClick = mainController.getMapViewTilesOnClick(x, y, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y);
            for (int i = 0; i < mapViewTilesOnClick.length; i++) {
                for (int j = 0; j < mapViewTilesOnClick[0].length; j++) {
                    // TODO: 颠倒坐标系的操作逻辑需要优化，现在仅是实现，但读起代码来很难理解
                    mainPaneButtons[i][j].setMapX(mapViewTilesOnClick[j][i].getX());
                    mainPaneButtons[i][j].setMapY(mapViewTilesOnClick[j][i].getY());
                    mainPaneButtons[i][j].setPlayer(mapViewTilesOnClick[j][i].getPlayer());
                }
            }
            imageView.setImage(mainController.getNewMiniMap(x, y, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y));
        });

//        GameLoopTimer timer = new GameLoopTimer() {
//            @Override
//            public void tick(float secondsSinceLastFrame) {
//                mainController.getAccountHolderName();
//            }
//        };
//        timer.start();
    }
}
