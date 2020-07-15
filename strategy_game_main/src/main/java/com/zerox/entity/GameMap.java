package com.zerox.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 游戏地图
 *
 * @author ZeromaXHe
 * @date 2020/7/13
 */
public class GameMap {

    private static final Logger logger = LoggerFactory.getLogger(GameMap.class);
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
    private void initMap() {
        GameMapNode node1 = newGameMapNode();
        GameMapNode node2 = newGameMapNode();
        GameMapNode node3 = newGameMapNode();
        node1.connect(node2);
        node2.connect(node3);
    }

    /**
     * 在日志中记录GameMap的连接情况
     */
    public void viewMapNodeConnection() {
        logger.debug("进入viewMapNodeConnection方法");
        boolean[][] mapNodeConnection = new boolean[mapNodeList.size()][mapNodeList.size()];
        mapNodeList.forEach(node -> {
            node.getConnectNodeList().forEach(node2 -> {
                mapNodeConnection[node.getId()][node2.getId()] = true;
            });
        });
        StringBuilder sb = new StringBuilder();
        Arrays.stream(mapNodeConnection).forEach(arrayLine -> {
            // boolean[] 无法使用Arrays.stream。只能采用下面方式
            IntStream.range(0, arrayLine.length)
                    .mapToObj(idx -> arrayLine[idx])
                    .forEach(x -> {
                        if (x) {
                            sb.append("1 ");
                        } else {
                            sb.append("0 ");
                        }
                    });
            logger.info(sb.toString());
            // 也可以 sb.setLength(0);
            sb.delete(0, sb.length());
        });
    }

    /**
     * 生成新的节点
     *
     * @return 新的节点
     */
    private GameMapNode newGameMapNode() {
        GameMapNode node = new GameMapNode(mapNodeCount++);
        mapNodeList.add(node);
        return node;
    }
}
