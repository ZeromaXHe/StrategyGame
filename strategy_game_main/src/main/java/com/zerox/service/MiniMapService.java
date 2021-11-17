package com.zerox.service;

import com.zerox.game.model.MapTile;
import javafx.scene.image.Image;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 11:15
 * @Description: 小地图
 * @ModifiedBy: zhuxi
 */
public interface MiniMapService {
    Image getDefaultMiniMapImage();

    Image getNewMiniMap(int x, int y, int width, int height);

    MapTile[][] getMapViewTilesOnClick(int x, int y, int width, int height);
}
