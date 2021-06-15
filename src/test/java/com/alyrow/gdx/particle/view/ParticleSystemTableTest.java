package com.alyrow.gdx.particle.view;

import com.alyrow.gdx.particle.Plugin;
import com.alyrow.gdx.particle.utilities.Try;
import com.alyrow.gdx.particle.view.gen.DefaultConfiguration;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.VisUI;
import com.mcxiv.app.PluginTester;

class ParticleSystemTableTest {

    public static void main(String[] args) throws InterruptedException {
        PluginTester.launchTest(DefaultConfiguration.get(400, 800), () -> Try.defaults(ParticleSystemTableTest::TestStarter));
    }

    private static void TestStarter() throws NoSuchMethodException {
        Plugin.isDebugging = true;

        Plugin plugin = new Plugin();
        PluginTester.setPlugin(plugin);

        VisUI.getSkin().get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        simpleTest();
    }

    private static void simpleTest() throws NoSuchMethodException {
        ParticleSystemTable table = new ParticleSystemTable();
        PluginTester.setTable(table);
    }

}