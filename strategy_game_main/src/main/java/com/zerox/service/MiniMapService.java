package com.zerox.service;

import javafx.scene.image.Image;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 11:15
 * @Description: 小地图
 * @ModifiedBy: zhuxi
 */
public interface MiniMapService {
    Image getMiniMapImage();

    Image getNewMiniMap(int x, int y);
}
