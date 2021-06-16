package com.alyrow.gdx.particle.view.subs;

import com.alyrow.gdx.particle.view.util.Entry;
import com.alyrow.gdx.particle.view.util.NumericField;
import com.badlogic.gdx.math.Vector2;

public class ParticlePositionEntry extends Entry<Vector2> {

    private NumericField fie_abs;
    private NumericField fie_ord;

    public ParticlePositionEntry() {
        super("PARTICLE POSITION");

        addEntry("Abscissa", fie_abs = new NumericField());
        addEntry("Ordinate", fie_ord = new NumericField());
    }

    @Override
    public Vector2 getValue() {
        return new Vector2(fie_abs.getFloat(), fie_ord.getFloat());
    }
}
