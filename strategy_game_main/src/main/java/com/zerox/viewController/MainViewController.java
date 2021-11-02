package com.zerox.viewController;

import com.zerox.controller.MainController;
import com.zerox.engine.Entity;
import com.zerox.engine.GameLoopTimer;
import com.zerox.engine.KeyPolling;
import com.zerox.engine.Renderer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    // Back-end Controller
    private MainController mainController;

    @Autowired
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Canvas gameCanvas;
    public AnchorPane gameAnchor;
    KeyPolling keys = KeyPolling.getInstance();

    private Entity player = new Entity(buildPlayer());

    private Image buildPlayer() {
        WritableImage wi = new WritableImage(100, 100);
        PixelWriter pw = wi.getPixelWriter();

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i > j && i < 100 - j) {
                    pw.setColor(i, j, Color.valueOf("#FFD700"));
                } else {
                    pw.setColor(i, j, Color.valueOf("#FF0000"));
                }
            }
        }
        return wi;
    }

    private Image buildBackground() {
        WritableImage wi = new WritableImage(1000, 1000);
        PixelWriter pw = wi.getPixelWriter();

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                pw.setColor(i, j, Color.valueOf("#111111"));
            }
        }
        return wi;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initialiseCanvas();

        player.setDrawPosition(350, 200);
        player.setScale(0.5f);

        Renderer renderer = new Renderer(this.gameCanvas);
        renderer.addEntity(player);
        renderer.setBackground(buildBackground());

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement(secondsSinceLastFrame);

                renderer.render();
            }
        };
        timer.start();
    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }

    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.UP)) {
            player.addThrust(20 * frameDuration);
        } else if (keys.isDown(KeyCode.DOWN)) {
            player.addThrust(-20 * frameDuration);
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            player.addTorque(120f * frameDuration);
        } else if (keys.isDown(KeyCode.LEFT)) {
            player.addTorque(-120f * frameDuration);
        }
        player.update();
    }
}
