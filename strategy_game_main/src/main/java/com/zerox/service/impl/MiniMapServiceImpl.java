package com.zerox.service.impl;

import com.zerox.constant.MainConstant;
import com.zerox.dao.MapDao;
import com.zerox.game.model.MapTile;
import com.zerox.game.view.MainPaneButton;
import com.zerox.service.MiniMapService;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 11:15
 * @Description: 小地图
 * @ModifiedBy: zhuxi
 */
@Service("MiniMapServiceImpl")
public class MiniMapServiceImpl implements MiniMapService {
    // TODO: 目前常驻内存，后续可以使用懒汉式优化
    private WritableImage wi = new WritableImage(MainConstant.MINIMAP_X, MainConstant.MINIMAP_Y);

    private MapDao mapDao;

    @Autowired
    public void setMapDao(MapDao mapDao) {
        this.mapDao = mapDao;
    }

    @Override
    public Image getMiniMapImage() {
        PixelWriter pw = wi.getPixelWriter();
        paintBasicImage(pw);
        paintViewBox(pw, -1, MainConstant.MINIMAP_VIEW_X, -1, MainConstant.MINIMAP_VIEW_Y);
        return wi;
    }

    @Override
    public Image getNewMiniMap(int x, int y, int width, int height) {
        PixelWriter pw = wi.getPixelWriter();
        paintBasicImage(pw);
        int[] viewBoxStart = calcViewBoxStart(x, y, width, height);
        paintViewBox(pw, viewBoxStart[0] - 1, viewBoxStart[0] + width,
                viewBoxStart[1] - 1, viewBoxStart[1] + height);
        return wi;
    }

    @Override
    public MapTile[][] getMapViewTilesOnClick(int x, int y, int width, int height) {
        int[] viewBoxStart = calcViewBoxStart(x, y, width, height);
        return mapDao.getMapViewTiles(viewBoxStart[0], viewBoxStart[1], width, height);
    }

    private int[] calcViewBoxStart(int x, int y, int width, int height) {
        int xMin = x - width / 2;
        int xMax = x + width / 2;
        int yMin = y - height / 2;
        int yMax = y + height / 2;
        if (xMax > MainConstant.MINIMAP_X) {
            xMin -= MainConstant.MINIMAP_X - xMax;
            xMax = MainConstant.MINIMAP_X;
        }
        if (yMax > MainConstant.MINIMAP_Y) {
            yMin -= MainConstant.MINIMAP_Y - yMax;
            yMax = MainConstant.MINIMAP_Y;
        }
        if (xMin < 0) {
            xMax -= xMin;
            xMin = 0;
        }
        if (yMin < 0) {
            yMax -= yMin;
            yMin = 0;
        }
        return new int[]{xMin, yMin};
    }

    private void paintBasicImage(PixelWriter pw) {
        MapTile[][] mapAllTiles = mapDao.getMapViewTiles(0, 0, MainConstant.MINIMAP_X, MainConstant.MINIMAP_Y);
        for (int i = 0; i < MainConstant.MINIMAP_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_Y; j++) {
                pw.setColor(i, j, MainPaneButton.getColorOfPlayer(mapAllTiles[i][j].getPlayer()));
            }
        }
    }

    private void paintViewBox(PixelWriter pw, int xMin, int xMax, int yMin, int yMax) {
        for (int i = Math.max(xMin, 0); i <= Math.min(xMax, MainConstant.MINIMAP_X - 1); i++) {
            if (yMin >= 0) {
                pw.setColor(i, yMin, Color.WHITE);
            }
            if (yMax < MainConstant.MINIMAP_Y) {
                pw.setColor(i, yMax, Color.WHITE);
            }
        }
        for (int i = Math.max(yMin, 0); i <= Math.min(yMax, MainConstant.MINIMAP_Y - 1); i++) {
            if (xMin >= 0) {
                pw.setColor(xMin, i, Color.WHITE);
            }
            if (xMax < MainConstant.MINIMAP_X) {
                pw.setColor(xMax, i, Color.WHITE);
            }
        }
    }
}
