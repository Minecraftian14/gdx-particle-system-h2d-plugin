package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.utilities.CUD;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisLabel;

public class TitledEntry<ValueType> extends Entry<ValueType> {

    CollapsibleEntry<ValueType> content;

    public TitledEntry(String name) {

        this.content = new CollapsibleEntry<>();

        add(new VisLabel(CUD.title(name))).growX().space(5);
        add(content.getCollaspButton()).space(5).row();
        add(content).colspan(2).space(5).growX();

    }

    @Override
    public void addEntry(String name, Actor actor) {
        content.addEntry(name, actor);
    }

    @Override
    public ValueType getValue() {
        return content.getValue();
    }
}
