package com.zerox.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
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
     * 游戏地图横轴节点数量
     */
    private int xCount = 10;
    /**
     * 游戏地图纵轴节点数量
     */
    private int yCount = 5;
    /**
     * 游戏地图节点数量
     */
    private int mapNodeCount = 0;
    /**
     * 存储所有地图中节点的二维数组
     */
    private GameMapNode[][] mapNodes = new GameMapNode[xCount][yCount];

    public GameMap() {
        initMap();
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GameMapNode node = newGameMapNode();
                mapNodes[i][j] = node;
                if (i > 0) {
                    node.connect(mapNodes[i - 1][j]);
                }
                if (j > 0) {
                    node.connect(mapNodes[i][j - 1]);
                }
            }
        }
    }

    /**
     * 在日志中记录GameMap中节点id的分布情况
     */
    public void viewMapNodes() {
        logger.debug("进入viewMapNodeConnection方法");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<yCount;i++){
            for(int j=0; j<xCount;j++){
                sb.append(String.format("%4d", mapNodes[j][i].getId()));
            }
            logger.info(sb.toString());
            // 也可以 sb.setLength(0);
            sb.delete(0, sb.length());
        }
        //此处不适合foreach 因为二维数组arr[x][y]先变成 arr[x]的foreach，然后再打印，就变成y行x列的记录
//        for (GameMapNode[] gameMapNodes : mapNodes) {
//            for (GameMapNode node : gameMapNodes) {
//                sb.append(String.format("%4d", node.getId()));
//            }
//            logger.info(sb.toString());
//            // 也可以 sb.setLength(0);
//            sb.delete(0, sb.length());
//        }
        //此处也不适合使用stream流，因为二维数组arr[x][y]先变成 arr[x]的流，然后再打印，就变成y行x列的记录
//        Arrays.stream(mapNodes).forEach(arrayLine -> {
//            Arrays.stream(arrayLine)
//                    .forEach(x -> {
//                        sb.append(String.format("%4d", x.getId()));
//                    });
//            logger.info(sb.toString());
//            // 也可以 sb.setLength(0);
//            sb.delete(0, sb.length());
//        });
        logger.debug("结束viewMapNodeConnection方法");
    }

    /**
     * 生成新的节点
     *
     * @return 新的节点
     */
    private GameMapNode newGameMapNode() {
        GameMapNode node = new GameMapNode(mapNodeCount++);
        return node;
    }
}
