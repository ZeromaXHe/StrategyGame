package entity;

/**
 * 游戏玩家
 *
 * @author ZeromaXHe
 * @date 2020/7/13 22:56
 */
public class Player {
    /**
     * 名字
     */
    private String name;
    /**
     * 占领节点数量
     */
    private int nodeCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
}
