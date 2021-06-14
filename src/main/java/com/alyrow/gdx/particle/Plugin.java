package com.alyrow.gdx.particle;

import games.rednblack.h2d.common.plugins.H2DPluginAdapter;
import net.mountainblade.modular.annotations.Implementation;

@Implementation(authors = "alyrow", version = "0.0.1")
public class Plugin extends H2DPluginAdapter {

    public static final String CLASS_NAME = Plugin.class.getCanonicalName();

    public static boolean isDebugging = false;

    public Plugin() {
        super(CLASS_NAME);
    }

    @Override
    public void initPlugin() {
        // TODO: add all mediators
//        facade.registerMediator(new YourMediator(this));


    }

}
