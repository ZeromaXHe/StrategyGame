package com.zerox;

/**
 * @Author: zhuxi
 * @Time: 2021/11/19 10:50
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class GameStarter {
    public static void main(String[] args) {
        JavaFxApplication javaFxApplication = new JavaFxApplication("fxml/idleGame.fxml", GameStarter.class);
        javaFxApplication.gameStart(args);
    }
}
