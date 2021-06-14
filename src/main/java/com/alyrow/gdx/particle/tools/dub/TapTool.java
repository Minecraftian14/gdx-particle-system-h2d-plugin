package com.alyrow.gdx.particle.tools.dub;

import com.badlogic.gdx.math.Vector2;

public interface TapTool extends ToolAdaptor {

    Vector2 point = new Vector2(0, 0);

    @Override
    default boolean stageMouseDown(float x, float y) {
        point.set(x, y);
        return true;
    }

    @Override
    default void stageMouseUp(float x, float y) {
        if (point.epsilonEquals(x, y)) at(x, y);
    }

    void at(float x, float y);

}
