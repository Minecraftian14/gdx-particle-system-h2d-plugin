package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.utilities.CUD;
import com.alyrow.gdx.particle.utilities.Try;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.util.adapter.SimpleListAdapter;
import com.kotcrab.vis.ui.widget.ListView;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

public class StaticFieldListView extends ListView<String> {

    private ObjectMap<String, Integer> map = new ObjectMap<>();


    private int value;

    public StaticFieldListView(Class<?> clazz) {
        super(new SimpleListAdapter<>(CUD.getStaticFields(clazz)
                        .map(Field::getName)
                        .collect(CUD.toArray())));

        CUD.getStaticFields(clazz).forEach(field -> map.put(field.getName(), Try.ignore(() -> field.getInt(null), -1)));

        value = map.values().toArray().get(0);

        setItemClickListener(this::setValue);
    }

    public void setValue(String s) {
        value = map.get(s);
    }

    public int getValue() {
        return value;
    }

}
