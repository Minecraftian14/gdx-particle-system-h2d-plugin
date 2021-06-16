package com.alyrow.gdx.particle.view;

import com.alyrow.gdx.particle.ParticleSystem;
import com.alyrow.gdx.particle.texture.ParticleTexture;
import com.alyrow.gdx.particle.view.subs.ParticlePositionEntry;
import com.alyrow.gdx.particle.view.subs.ParticleRuleEntry;
import com.alyrow.gdx.particle.view.subs.ParticleTypeEntry;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kotcrab.vis.ui.widget.VisTable;

public class ParticleSystemTable extends VisTable {

    private final ParticlePositionEntry ent_position;
    private final ParticleTypeEntry ent_type;
    private final ParticleRuleEntry ent_rule;

    private final World world;
    private final Camera camera;

    public ParticleSystemTable(World world, Camera camera) {
        this.world = world;
        this.camera = camera;

        pad(10);
        add(ent_position = new ParticlePositionEntry()).growX().row();
        add(ent_type = new ParticleTypeEntry()).growX().row();
        add(ent_rule = new ParticleRuleEntry()).growX().row();


    }

    public ParticleSystem getParticleSystem() {
        ParticleSystem sys = new ParticleSystem(ent_type.getValue(), world, camera);

        sys.setRules(ent_rule.getValue());

        Vector2 position = ent_position.getValue();
        sys.setParticlesPosition(position.x, position.y);

//        sys.setBlendFunctionSeparate();

        sys.setTexture(new ParticleTexture());
//        sys.setPhysicManager();

        return sys;
    }

    private void addEntry(Class<Integer> integerClass, String name, String value) {
    }


}
