package com.zerox.service.impl;

import com.zerox.constant.MainConstant;
import com.zerox.service.MiniMapService;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
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

    @Override
    public Image getMiniMapImage() {
        PixelWriter pw = wi.getPixelWriter();
        paintBasicImage(pw);
        paintViewBox(pw, -1, MainConstant.MINIMAP_VIEW_X, -1, MainConstant.MINIMAP_VIEW_Y);
        return wi;
    }

    @Override
    public Image getNewMiniMap(int x, int y) {
        PixelWriter pw = wi.getPixelWriter();
        paintBasicImage(pw);
        paintViewBox(pw, x - MainConstant.MINIMAP_VIEW_X / 2 - 1, x + MainConstant.MINIMAP_VIEW_X / 2,
                y - MainConstant.MINIMAP_VIEW_Y / 2 - 1, y + MainConstant.MINIMAP_VIEW_Y / 2);
        return wi;
    }

    private void paintBasicImage(PixelWriter pw) {
        for (int i = 0; i < MainConstant.MINIMAP_X; i++) {
            for (int j = 0; j < MainConstant.MINIMAP_Y; j++) {
                pw.setColor(i, j, Color.BLACK);
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
