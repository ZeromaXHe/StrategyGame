package com.zerox.dao.impl;

import com.zerox.constant.MainConstant;
import com.zerox.dao.MapDao;
import com.zerox.game.model.MapTile;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 13:51
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Repository("MainDaoImpl")
public class MapDaoImpl implements MapDao {
    private static final MapTile[][] map = new MapTile[MainConstant.MINIMAP_X][MainConstant.MINIMAP_Y];

    // 用于测试的初始地图数据
    static {
        int thirdOfX = MainConstant.MINIMAP_X / 3 + 1;
        int thirdOfY = MainConstant.MINIMAP_Y / 3 + 1;
        for (int i = 0; i < MainConstant.MINIMAP_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_Y; j++) {
                MapTile tile = new MapTile();
                tile.setPlayer((i / thirdOfX * 3) + (j / thirdOfY));
                tile.setX(i);
                tile.setY(j);
                map[i][j] = tile;
            }
        }
    }

    @Override
    public MapTile[][] getMapViewTiles(int x, int y, int width, int height) {
        MapTile[][] result = new MapTile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = map[x + i][y + j];
            }
        }
        return result;
    }
}
