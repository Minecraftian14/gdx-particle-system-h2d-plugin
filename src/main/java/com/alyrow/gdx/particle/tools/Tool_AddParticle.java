package com.alyrow.gdx.particle.tools;

import com.alyrow.gdx.particle.Plugin;
import com.alyrow.gdx.particle.tools.dub.TapTool;
import games.rednblack.h2d.common.factory.IFactory;

import javax.swing.text.html.parser.Entity;

public class Tool_AddParticle implements TapTool {

    private final Plugin plugin;

    public Tool_AddParticle(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void at(float x, float y) {

    }
}
