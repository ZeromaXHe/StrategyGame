package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

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
    public void viewIdOfMapNodes() {
        Function<GameMapNode, Integer> func1 = GameMapNode::getId;
        Function<GameMapNode, String> func2 = func1.andThen(String::valueOf);
        viewMapNodes(4, func2);
    }

    /**
     * 在日志中记录GameMap中节点对应数据的分布情况
     *
     * @param stringCellLen 日志每格的长度
     * @param function      需要查询GameMapNode节点对应数据的getter
     */
    public void viewMapNodes(int stringCellLen, Function<GameMapNode, String> function) {
        logger.debug("进入viewMapNodes方法");
        String formatter = "%" + stringCellLen + "s";
        //logger.debug("formatter: {}", formatter);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < yCount; i++) {
            for (int j = 0; j < xCount; j++) {
                //logger.debug("function.apply(mapNodes[j][i])):{}", function.apply(mapNodes[j][i]));
                sb.append(String.format(formatter, function.apply(mapNodes[j][i])));
            }
            logger.info(sb.toString());
            // 也可以 sb.setLength(0);
            sb.delete(0, sb.length());
        }
        logger.debug("结束viewMapNodes方法");
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
