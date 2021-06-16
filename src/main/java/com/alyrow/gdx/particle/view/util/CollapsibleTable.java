package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.utilities.CUD;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Null;
import com.kotcrab.vis.ui.widget.*;

import static com.alyrow.gdx.particle.utilities.CUD.tag;
import static com.alyrow.gdx.particle.utilities.CUD.title;

public class CollapsibleTable<ValueType> extends Entry<ValueType> {

    VisTable contentTable;

    // TODO: Add the images
    BaseDrawable upArrow = new BaseDrawable();
    BaseDrawable downArrow = new BaseDrawable();

    public CollapsibleTable(String title) {
        add(new VisLabel(title(title))).space(5).growX();

        VisImageButton action = new VisImageButton(downArrow);
        add(action).space(5).right().row();

        CollapsibleWidget widget = new CollapsibleWidget(contentTable = new VisTable());
        add(widget).expandX().fillX().colspan(2).space(5);

        action.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                widget.setCollapsed(!widget.isCollapsed());
                action.getStyle().imageUp = widget.isCollapsed() ? downArrow : upArrow;
            }
        });
    }

    @Override
    public void addEntry(String name, Actor actor) {
        contentTable.add(new VisLabel(tag(name))).top().space(5).growX();
        contentTable.add(actor).space(5).growX().row();
    }

    @Override
    public ValueType getValue() {
        return null;
    }

}
