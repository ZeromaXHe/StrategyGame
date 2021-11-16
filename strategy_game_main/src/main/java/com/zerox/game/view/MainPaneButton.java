package com.zerox.game.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 14:38
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class MainPaneButton extends Button {
    private int x;
    private int y;
    private int mapX;
    private int mapY;

    private SimpleIntegerProperty force = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty player = new SimpleIntegerProperty(0);

    public MainPaneButton(String text, int x, int y, int mapX, int mapY, int force, int player) {
        super(text);
        this.x = x;
        this.y = y;
        this.mapX = mapX;
        this.mapY = mapY;
        this.force.set(force);
        this.player.set(player);

        double buttonSize = 20.0;
        this.setPrefHeight(buttonSize);
        this.setPrefWidth(buttonSize);
        // GUI的坐标系（横x纵y）和头脑里的二维数组（纵x横y）的坐标系不同
        AnchorPane.setTopAnchor(this, buttonSize * y);
        AnchorPane.setLeftAnchor(this, buttonSize * x);
        this.setStyle("-fx-border-color: #FFFFFF;-fx-border-style: solid;-fx-border-width: 1;-fx-border-radius: 2;");

        this.player.addListener((o, ov, nv) -> {
            Background bg = new Background(
                    new BackgroundFill(
                            getColorOfPlayer((nv.intValue())),
                            new CornerRadii(0),
                            new Insets(0)));
            this.setBackground(bg);
        });
    }

    public static Color getColorOfPlayer(int player) {
        switch (player) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.CRIMSON;
            case 2:
                return Color.AQUAMARINE;
            case 3:
                return Color.GREENYELLOW;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.PINK;
            case 6:
                return Color.VIOLET;
            case 7:
                return Color.CHOCOLATE;
            case 8:
                return Color.DARKGREEN;
            default:
                return Color.GREY;
        }
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

    public int getForce() {
        return force.get();
    }

    public SimpleIntegerProperty forceProperty() {
        return force;
    }

    public void setForce(int force) {
        this.force.set(force);
    }

    public int getPlayer() {
        return player.get();
    }

    public SimpleIntegerProperty playerProperty() {
        return player;
    }

    public void setPlayer(int player) {
        this.player.set(player);
    }
}
