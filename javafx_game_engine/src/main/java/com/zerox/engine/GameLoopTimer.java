package com.zerox.engine;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @Author: zhuxi
 * @Time: 2021/10/26 20:07
 * @Description: 参考 https://github.com/edencoding/javafx-game-dev/blob/master/SpaceShooter/src/main/java/com/edencoding/animation/GameLoopTimer.java
 * @ModifiedBy: zhuxi
 */
public abstract class GameLoopTimer extends AnimationTimer {
    private long pauseStart;
    private long animationStart;
    private DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    private long lastFrameTimeNanos;

    private boolean isPaused;
    private boolean isActive;

    private boolean pauseScheduled;
    private boolean playScheduled;
    private boolean restartScheduled;

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    public DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    public void pause() {
        if (!isPaused) {
            pauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused) {
            playScheduled = true;
        }
    }

    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
        animationDuration.set(0);
    }

    @Override
    public void handle(long now) {
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            animationStart += (now - pauseStart);
            isPaused = false;
            playScheduled = false;
            // 不加这一行，每次开始的第一帧 secondsSinceLastFrame 会等于当前时间戳毫秒数
            lastFrameTimeNanos = now;
        }

        if (restartScheduled) {
            isPaused = false;
            animationStart = now;
            restartScheduled = false;
            // 不加这一行，每次暂停后恢复的第一帧 secondsSinceLastFrame 会等于（当前时间戳毫秒数 - 暂停的时间戳毫秒数）
            lastFrameTimeNanos = now;
        }

        if (!isPaused) {
            long animDuration = now - animationStart;
            animationDuration.set(animDuration / 1e9);

            float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(secondsSinceLastFrame);
        }
    }

    public abstract void tick(float secondsSinceLastFrame);
}
