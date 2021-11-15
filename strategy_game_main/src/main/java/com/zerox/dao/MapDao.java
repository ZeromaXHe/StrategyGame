package com.zerox.dao;

import com.zerox.game.model.MapTile;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 13:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface MapDao {
    MapTile[][] getMapViewTiles(int x, int y, int width, int height);
}
