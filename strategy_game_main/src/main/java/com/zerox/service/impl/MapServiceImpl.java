package com.zerox.service.impl;

import com.zerox.dao.MapDao;
import com.zerox.game.model.MapTile;
import com.zerox.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 14:31
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Service("MapServiceImpl")
public class MapServiceImpl implements MapService {
    private MapDao mapDao;

    @Autowired
    public void setMapDao(MapDao mapDao) {
        this.mapDao = mapDao;
    }

    @Override
    public MapTile[][] getMapViewTiles(int x, int y, int width, int height) {
        return mapDao.getMapViewTiles(x, y, width, height);
    }
}
