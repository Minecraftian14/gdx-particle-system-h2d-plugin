package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.ParticleType;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.utilities.Try;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.util.adapter.SimpleListAdapter;
import com.kotcrab.vis.ui.widget.ListView;
import com.kotcrab.vis.ui.widget.VisLabel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ParticleEmissionModeEntry extends Entry<Integer> {

    public static final ObjectMap<String, Integer> map = new ObjectMap<>();

    static {
        Field[] fields = ParticleEmissionNumber.class.getFields();
        for (Field field : fields)
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers()))
                Try.ignore(() -> map.put(field.getName(),field.getInt(null)));
    }

    private int value;

    public ParticleEmissionModeEntry() {

        add(new VisLabel("Particle Emission:")).left().top().growX().space(5);

        value = map.values().toArray().get(0);

        var adapter = new SimpleListAdapter<>(map.keys().toArray());
        ListView<String> view = new ListView<>(adapter);
        view.setItemClickListener(this::setValue);

        add(view.getMainTable()).grow().space(5);

    }

    private void setValue(String s) {
        value = map.get(s);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
