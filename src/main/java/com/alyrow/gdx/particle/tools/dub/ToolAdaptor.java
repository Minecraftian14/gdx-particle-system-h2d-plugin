package com.alyrow.gdx.particle.tools.dub;

import com.badlogic.ashley.core.Entity;
import games.rednblack.h2d.common.view.tools.Tool;
import org.puremvc.java.interfaces.INotification;

public interface ToolAdaptor extends Tool {
    @Override
    default void initTool() {
    }

    @Override
    default boolean stageMouseDown(float x, float y) {
        return false;
    }

    @Override
    default void stageMouseUp(float x, float y) {
    }

    @Override
    default void stageMouseDragged(float x, float y) {
    }

    @Override
    default void stageMouseDoubleClick(float x, float y) {
    }

    @Override
    default boolean stageMouseScrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    default boolean itemMouseDown(Entity entity, float x, float y) {
        return false;
    }

    @Override
    default void itemMouseUp(Entity entity, float x, float y) {
    }

    @Override
    default void itemMouseDragged(Entity entity, float x, float y) {
    }

    @Override
    default void itemMouseDoubleClick(Entity entity, float x, float y) {
    }

    @Override
    default String getName() {
        return "";
    }

    @Override
    default String getTitle() {
        return "";
    }

    @Override
    default String getShortcut() {
        return "";
    }

    @Override
    default void handleNotification(INotification notification) {
    }

    @Override
    default void keyDown(Entity entity, int keycode) {
    }

    @Override
    default void keyUp(Entity entity, int keycode) {
    }
}
