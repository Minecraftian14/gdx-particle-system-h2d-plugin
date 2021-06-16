package com.alyrow.gdx.particle.view.subs;

import com.alyrow.gdx.particle.view.util.CollapsibleTable;
import com.alyrow.gdx.particle.view.util.Entry;
import com.alyrow.gdx.particle.view.util.NumericField;
import com.badlogic.gdx.math.Vector2;

public class ParticlePositionEntry extends Entry<Vector2> {

    private NumericField fie_abs;
    private NumericField fie_ord;

    public ParticlePositionEntry() {
        CollapsibleTable<?> table = new CollapsibleTable<>("PARTICLE POSITION");
        add(table).growX();
        table.addEntry("Abscissa", fie_abs = new NumericField());
        table.addEntry("Ordinate", fie_ord = new NumericField());
    }

    @Override
    public Vector2 getValue() {
        return new Vector2(fie_abs.getFloat(), fie_ord.getFloat());
    }
}
