package com.zerox.viewController;

import com.zerox.constant.MainConstant;
import com.zerox.controller.MainController;
import com.zerox.engine.GameLoopTimer;
import com.zerox.game.model.MapTile;
import com.zerox.game.view.MainPaneButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.MessageFormat;
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
    private GridPane infoPane;

    @FXML
    private TextField tfMapX;
    @FXML
    private TextField tfMapY;
    @FXML
    private TextField tfForce;
    @FXML
    private TextField tfPlayer;

    private MainPaneButton[][] mainPaneButtons = new MainPaneButton[MainConstant.MINIMAP_VIEW_X][MainConstant.MINIMAP_VIEW_Y];

    private SimpleObjectProperty<MainPaneButton> selectedButton = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMainPaneButtons();

        ImageView imageView = new ImageView(mainController.getMiniMapImage());
        mapPane.getChildren().add(imageView);
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);

        imageView.setOnMouseClicked(event -> handleClickOnMiniMap(imageView, event));

        bindInfoPaneTextFields();

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                // FIXME: 目前是简单测试用，直接改的 view 层的数据，没有去修改 model 里面的数据
                if (Math.random() > 0.9) {
                    int force = mainPaneButtons[0][0].getForce();
                    mainPaneButtons[0][0].setForce(force + 1);
                }
            }
        };
        timer.start();
    }

    private void bindInfoPaneTextFields() {
        tfMapX.textProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(selectedButton.get().getMapX()), selectedButton));

        tfMapY.textProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(selectedButton.get().getMapY()), selectedButton));

        tfForce.textProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(selectedButton.get().getForce()),
                selectedButton, selectedButton.get().forceProperty()));

        tfPlayer.textProperty().bind(Bindings.createStringBinding(
                () -> String.valueOf(selectedButton.get().getPlayer()),
                selectedButton, selectedButton.get().playerProperty()));
    }

    private void initMainPaneButtons() {
        MapTile[][] mapViewTiles = mainController.getMapViewTiles(0, 0, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y);

        for (int i = 0; i < MainConstant.MINIMAP_VIEW_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_VIEW_Y; j++) {
                MainPaneButton button = new MainPaneButton("", i, j, i, j,
                        mapViewTiles[i][j].getForce(), mapViewTiles[i][j].getPlayer());

                // TODO: 给 selectedButton 一个默认值（0,0）的按钮，不然很多绑定不太好处理。如果后续想到了好的处理方法最好改一下这里
                if (i == 0 && j == 0) {
                    selectedButton.set(button);
                }

                // 放在按钮上显示的帮助框
                Tooltip tooltip = new Tooltip();
                tooltip.textProperty().bind(Bindings.createStringBinding(() ->
                                MessageFormat.format("x:{0}, y:{1}, force:{2}, player:{3}",
                                        button.getMapX(), button.getMapY(),
                                        button.forceProperty().get(), button.playerProperty().get()),
                        button.playerProperty(), button.forceProperty()));
                button.setTooltip(tooltip);

                // 点击按钮
                button.setOnAction(event -> {
                    selectedButton.set(button);
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
    }

    private void handleClickOnMiniMap(ImageView imageView, MouseEvent event) {
        logger.info("x: {}, y: {}, sceneX: {}, sceneY: {}, screenX: {}, screenY: {}",
                event.getX(), event.getY(),
                event.getSceneX(), event.getSceneY(),
                event.getScreenX(), event.getScreenY());
        int x = (int) event.getX();
        int y = (int) event.getY();
        // 更新主界面按钮
        MapTile[][] mapViewTilesOnClick = mainController.getMapViewTilesOnClick(x, y, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y);
        for (int i = 0; i < mapViewTilesOnClick.length; i++) {
            for (int j = 0; j < mapViewTilesOnClick[0].length; j++) {
                mainPaneButtons[i][j].setMapX(mapViewTilesOnClick[i][j].getX());
                mainPaneButtons[i][j].setMapY(mapViewTilesOnClick[i][j].getY());
                mainPaneButtons[i][j].setPlayer(mapViewTilesOnClick[i][j].getPlayer());
            }
        }
        // 更新小地图图片
        imageView.setImage(mainController.getNewMiniMap(x, y, MainConstant.MINIMAP_VIEW_X, MainConstant.MINIMAP_VIEW_Y));
    }
}
