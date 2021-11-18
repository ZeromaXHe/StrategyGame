package com.zerox.controller;

import com.zerox.game.model.MapTile;
import com.zerox.service.MapService;
import com.zerox.service.MiniMapService;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: zhuxi
 * @Time: 2021/9/28 16:32
 * @Description:
 * @ModifiedBy: zhuxi
 */
@Controller("MainController")
public class MainController {
    private MiniMapService miniMapService;
    private MapService mapService;

    @Autowired
    public void setMiniMapService(MiniMapService miniMapService) {
        this.miniMapService = miniMapService;
    }

    @Autowired
    public void setMapService(MapService mapService) {
        this.mapService = mapService;
    }

    public Image getDefaultMiniMapImage() {
        return miniMapService.getDefaultMiniMapImage();
    }

    public Image getNewMiniMap(int x, int y, int width, int height) {
        return miniMapService.getNewMiniMap(x, y, width, height);
    }

    public MapTile[][] getMapViewTiles(int x, int y, int width, int height) {
        return mapService.getMapViewTiles(x, y, width, height);
    }

    public MapTile[][] getMapViewTilesOnClick(int x, int y, int width, int height) {
        return miniMapService.getMapViewTilesOnClick(x, y, width, height);
    }
}
