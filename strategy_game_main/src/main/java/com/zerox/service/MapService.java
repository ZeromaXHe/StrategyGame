package com.zerox.service;

import com.zerox.game.model.MapTile;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 14:30
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface MapService {
    MapTile[][] getMapViewTiles(int x, int y, int width, int height);
}
