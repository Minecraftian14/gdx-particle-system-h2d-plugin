package com.alyrow.gdx.particle.view.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import static com.alyrow.gdx.particle.utilities.CUD.tag;

public class CollapsibleEntry<ValueType> extends Entry<ValueType> {

    private Table contentTable;

    private CollapseAction collapseAction = new CollapseAction();
    private float collapseDuration = 0.3f;
    private Interpolation collapseInterpolation = Interpolation.pow3Out;

    private boolean collapsed;
    private boolean actionRunning;

    private float currentHeight;

    public CollapsibleEntry() {
        this(new VisTable());
    }

    public CollapsibleEntry(Table contentTable) {
        this(contentTable, false);
    }

    public CollapsibleEntry(Table contentTable, boolean collapsed) {
        this.collapsed = collapsed;
        this.contentTable = contentTable;

        updateTouchable();

        if (contentTable != null) setContentTable(contentTable);
    }

    public void setCollapsed(boolean collapse, boolean withAnimation) {
        this.collapsed = collapse;
        updateTouchable();

        if (contentTable == null) return;

        actionRunning = true;

        if (withAnimation) {
            collapseAction.reset();
            collapseAction.setStart(currentHeight);
            collapseAction.setEnd(collapse ? 0f : contentTable.getPrefHeight());
            collapseAction.setDuration(collapseDuration);
            collapseAction.setInterpolation(collapseInterpolation);
            addAction(collapseAction);
        } else {
            if (collapse) {
                currentHeight = 0;
                collapsed = true;
            } else {
                currentHeight = contentTable.getPrefHeight();
                collapsed = false;
            }

            actionRunning = false;
            invalidateHierarchy();
        }
    }

    public void setCollapsed(boolean collapse) {
        setCollapsed(collapse, true);
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    private void updateTouchable() {
        if (collapsed)
            setTouchable(Touchable.disabled);
        else
            setTouchable(Touchable.enabled);
    }

    public void setCollapseDuration(float collapseDuration) {
        this.collapseDuration = collapseDuration;
    }

    public void setCollapseInterpolation(Interpolation collapseInterpolation) {
        this.collapseInterpolation = collapseInterpolation;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (currentHeight > 1 && getY() + currentHeight > 1) {
            if (actionRunning) {
                batch.flush();
                boolean clipEnabled = clipBegin(getX(), getY(), getWidth(), currentHeight);

                super.draw(batch, parentAlpha);

                batch.flush();
                if (clipEnabled) clipEnd();
            } else {
                super.draw(batch, parentAlpha);
            }
        }
    }

    @Override
    public void layout() {
        if (contentTable == null) return;

        contentTable.setBounds(0, 0, contentTable.getPrefWidth(), contentTable.getPrefHeight());

        if (!actionRunning) {
            if (collapsed)
                currentHeight = 0;
            else
                currentHeight = contentTable.getPrefHeight();
        }
    }

    @Override
    public float getPrefWidth() {
        return contentTable == null ? 0 : contentTable.getPrefWidth();
    }

    @Override
    public float getPrefHeight() {
        if (contentTable == null) return 0;

        if (!actionRunning) {
            if (collapsed)
                return 0;
            else
                return contentTable.getPrefHeight();
        }

        return currentHeight;
    }

    public Cell<Table> setContentTable(Table contentTable) {
        this.contentTable = contentTable;
        clearChildren();
        return add(contentTable).colspan(2).grow();
    }

    public VisImageButton getCollaspButton() {
        return new CollapseButton();
    }

    @Override
    protected void childrenChanged() {
        super.childrenChanged();
        if (getChildren().size > 1) throw new GdxRuntimeException("Only one actor can be added to CollapsibleEntry");
    }

    @Override
    public void addEntry(String name, Actor actor) {
        contentTable.add(new VisLabel(tag(name))).space(5).growX();
        contentTable.add(actor).space(5).growX().row();
    }

    @Override
    public ValueType getValue() {
        return null;
    }

    private class CollapseAction extends FloatAction {

        @Override
        protected void update(float percent) {
            super.update(percent);
            currentHeight = getValue();

            if (percent == 1) {
                actionRunning = false;
                collapsed = currentHeight == 0;
            }

            invalidateHierarchy();
        }
    }

    private class CollapseButton extends VisImageButton {

        // TODO: Add the images
        static BaseDrawable upArrow = new BaseDrawable();
        static BaseDrawable downArrow = new BaseDrawable();

        public CollapseButton() {
            super(upArrow);
            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    setCollapsed(!isCollapsed());
                    getStyle().imageUp = isCollapsed() ? downArrow : upArrow;
                }
            });
        }


    }
}
