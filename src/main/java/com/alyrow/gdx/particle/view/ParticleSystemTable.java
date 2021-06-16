package com.alyrow.gdx.particle.view;

import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.view.util.*;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTable;

public class ParticleSystemTable extends VisTable {

    Array<Entry> entries = new Array<>();

    public ParticleSystemTable() {

        pad(10);
        add(new ParticlePositionEntry()).growX().row();
        add(new ParticleTypeEntry()).growX().row();
        add(new ParticleRuleEntry()).growX().row();



    }

    public ParticleSystem getParticleSystem() {
        ParticleSystem sys = new ParticleSystem(0, null, null);
//        sys.setRules(new ParticleRules());
//        sys.setParticlesPosition(0, 0);
        return sys;
    }

    private void addEntry(Class<Integer> integerClass, String name, String value) {
    }


}
