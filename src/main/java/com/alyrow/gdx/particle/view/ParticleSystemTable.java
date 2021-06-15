package com.alyrow.gdx.particle.view;

import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.view.util.Entry;
import com.alyrow.gdx.particle.view.util.ParticleRuleEntry;
import com.alyrow.gdx.particle.view.util.ParticleTypeEntry;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisTable;

public class ParticleSystemTable extends VisTable {

    Array<Entry> entries = new Array<>();

    public ParticleSystemTable() {

        pad(10);
        add(new ParticleTypeEntry()).growX().row();
        add(new ParticleRuleEntry()).growX().row();

//        ParticleSystem sys = new ParticleSystem(0, null, null);
//        sys.setRules(new ParticleRules());


    }

    private void addEntry(Class<Integer> integerClass, String name, String value) {
    }


}
