package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.utilities.CUD;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;

import static com.alyrow.gdx.particle.utilities.CUD.tag;

public abstract class Entry<ValueType> extends VisTable {

    public Entry() {
    }

    public Entry(String name) {
        add(new VisLabel(CUD.title(name))).left().top().space(5).growX().row();
    }

    public void addEntry(String name, Actor actor) {
        add(new VisLabel(tag(name))).space(5).growX();
        add(actor).space(5).growX().row();
    }

    public abstract ValueType getValue();

}
