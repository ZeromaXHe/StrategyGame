package com.zerox.game.model;

/**
 * @Author: zhuxi
 * @Time: 2021/11/15 13:55
 * @Description: 地图地块
 * @ModifiedBy: zhuxi
 */
public class MapTile {
    private int x;
    private int y;
    /**
     * 兵力数量
     */
    private int force;
    /**
     * 所属玩家，0表示没有
     */
    private int player;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
