package com.zerox;

/**
 * @Author: zhuxi
 * @Time: 2021/11/18 10:18
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class GameStarter {
    public static void main(String[] args) {
        JavaFxApplication javaFxApplication = new JavaFxApplication("fxml/mainView.fxml", GameStarter.class);
        javaFxApplication.gameStart(args);
    }
}
