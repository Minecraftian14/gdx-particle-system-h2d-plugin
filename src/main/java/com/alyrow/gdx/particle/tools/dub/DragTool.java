package com.alyrow.gdx.particle.tools.dub;

import com.badlogic.gdx.math.Rectangle;

public interface DragTool extends ToolAdaptor {

    Rectangle dragRect = new Rectangle(0,0,0,0);

    @Override
    default boolean stageMouseDown(float x, float y) {
        dragRect.setPosition(x, y);
        mouseDown(x, y);
        return true;
    }

    void mouseDown(float x, float y);

    private void updateDragRect(float x1, float y1, float x2, float y2) {
        dragRect.setPosition(Math.min(x1, x2), Math.min(y1, y2));
        dragRect.setSize(
                Math.max(x1, x2) - dragRect.x,
                Math.max(y1, y2) - dragRect.y
        );
    }

    @Override
    default void stageMouseUp(float x2, float y2) {
        updateDragRect(dragRect.x, dragRect.y, x2, y2);
        mouseUp(x2, y2);
    }

    void mouseUp(float x, float y);

    @Override
    default void stageMouseDragged(float x2, float y2) {
        updateDragRect(dragRect.x, dragRect.y, x2, y2);
        mouseDragged(x2, y2);
    }

    void mouseDragged(float x, float y);

}
