package com.zerox.engine.bootstrap;

/**
 * @Author: zhuxi
 * @Time: 2021/11/22 11:46
 * @Description:
 * @ModifiedBy: zhuxi
 */
public enum Breakpoint {
    XSMALL(0),
    SMALL(1),
    MEDIUM(2),
    LARGE(3),
    XLARGE(4);

    private int value;

    Breakpoint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
