package com.zerox.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 游戏地图节点
 *
 * @author ZeromaXHe
 * @date 2020/7/13 22:52
 */
public class GameMapNode {
    /**
     * 节点id
     */
    private int id;
    /**
     * 节点名字
     */
    private String name;
    /**
     * 节点人口
     */
    private int population;
    /**
     * 连接该节点的节点队列
     */
    private List<GameMapNode> connectNodeList = new LinkedList<GameMapNode>();
    /**
     * 占领者
     */
    private Player conqueror = null;

    public GameMapNode(int id) {
        this.id = id;
        this.name = "node" + id;
        this.population = 0;
    }

    /**
     * 连接本节点和另一个节点
     *
     * @param node
     * @return 是否成功连接 true 是 false 否
     */
    public boolean connect(GameMapNode node) {
        if (this.equals(node)
                || this.getConnectNodeList().contains(node)
                || node.getConnectNodeList().contains(this)) {
            return false;
        }
        node.getConnectNodeList().add(this);
        this.getConnectNodeList().add(node);
        return true;
    }

    public List<GameMapNode> getConnectNodeList() {
        return connectNodeList;
    }

    public void setConnectNodeList(List<GameMapNode> connectNodeList) {
        this.connectNodeList = connectNodeList;
    }

    public Player getConqueror() {
        return conqueror;
    }

    public void setConqueror(Player conqueror) {
        this.conqueror = conqueror;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
