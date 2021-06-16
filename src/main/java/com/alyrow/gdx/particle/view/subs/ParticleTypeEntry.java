package com.alyrow.gdx.particle.view.subs;

import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.view.util.Entry;
import com.alyrow.gdx.particle.view.util.StaticFieldListView;
import com.kotcrab.vis.ui.widget.VisLabel;

import static com.alyrow.gdx.particle.utilities.CUD.title;

public class ParticleTypeEntry extends Entry<Integer> {

    private StaticFieldListView view;

    public ParticleTypeEntry() {
        add(new VisLabel(title("PARTICLE TYPE"))).padTop(15).top().space(5).growX();
        add((view = new StaticFieldListView(ParticleType.class)).getMainTable()).space(5).growX().row();
    }

    private void setValue(String s) {
        view.setValue(s);
    }

    @Override
    public Integer getValue() {
        return view.getValue();
    }
}
