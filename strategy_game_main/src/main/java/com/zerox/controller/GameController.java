package com.zerox.controller;

import com.zerox.entity.GameMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 游戏控制器
 *
 * @author ZeromaXHe
 * @date 2020/7/13 22:59
 */
public class GameController {
    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    /**
     * 游戏地图
     */
    private GameMap gameMap;

    public static void main(String[] args) {
        logger.info("这是GameController发送的info日志消息");
        logger.warn("这是GameController发送的warn日志消息");
        logger.error("这是GameController发送的error日志消息 {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
