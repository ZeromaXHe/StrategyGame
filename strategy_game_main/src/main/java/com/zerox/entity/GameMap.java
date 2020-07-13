package com.zerox.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 游戏地图
 *
 * @author ZeromaXHe
 * @date 2020/7/13
 */
public class GameMap {
    /**
     * 游戏地图节点数量
     */
    private int mapNodeCount = 0;
    /**
     * 存储所有地图中节点的队列
     */
    private List<GameMapNode> mapNodeList = new LinkedList<GameMapNode>();

    public GameMap() {
        initMap();
    }

    /**
     * 初始化地图
     */
    private void initMap(){
        GameMapNode node1 = newGameMapNode();
        GameMapNode node2 = newGameMapNode();
        GameMapNode node3 = newGameMapNode();
        node1.connect(node2);
        node2.connect(node3);
    }

    /**
     * 生成新的节点
     *
     * @return 新的节点
     */
    private GameMapNode newGameMapNode(){
        GameMapNode node = new GameMapNode(mapNodeCount++);
        mapNodeList.add(node);
        return node;
    }
}
