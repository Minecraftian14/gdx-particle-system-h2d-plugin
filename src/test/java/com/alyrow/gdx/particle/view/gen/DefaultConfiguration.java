package com.alyrow.gdx.particle.view.gen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DefaultConfiguration {

    public static LwjglApplicationConfiguration get(int w, int h) {
        return new LwjglApplicationConfiguration() {{
            for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
                if (element.getClassName().endsWith("Test")) {
                    this.title = element.getClassName();
                    break;
                }
            }
            this.width = w;
            this.height = h;
        }};
    }

}
