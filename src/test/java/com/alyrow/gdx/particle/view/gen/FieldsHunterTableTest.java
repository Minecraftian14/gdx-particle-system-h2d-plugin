package com.alyrow.gdx.particle.view.gen;

import com.alyrow.gdx.particle.Plugin;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.utilities.Try;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mcxiv.app.PluginTester;

import java.lang.reflect.Array;

class FieldsHunterTableTest {

    public static void main(String[] args) throws InterruptedException {
        PluginTester.launchTest(DefaultConfiguration.get(400, 350), () -> Try.defaults(FieldsHunterTableTest::TestStarter));
    }

    private static void TestStarter() throws NoSuchMethodException {
        Plugin.isDebugging = true;

        Plugin plugin = new Plugin();
        PluginTester.setPlugin(plugin);

//        simpleTest();
        simpleTestWithMethodParam();
    }

    private static void simpleTest() throws NoSuchMethodException {
        FieldsHunterTable<TestForce> table = new FieldsHunterTable<>(TestForce.class);
        table.addConstructor(TestForce.class.getConstructor(int.class, float.class, String.class),
                "Constructable Field A", "Constructable Field B", "Constructable Field C");
        addInstantiateButton(table);
        PluginTester.setTable(table);
    }


    private static void simpleTestWithMethodParam() throws NoSuchMethodException {
        FieldsHunterTable<TestForce> table = new FieldsHunterTable<>(TestForce.class);
        table.addConstructor(TestForce.class.getConstructor(int.class, float.class),
                "Constructable Field A", "Constructable Field B");
        table.addMethod(TestForce.class.getMethod("setModifFieldA", int.class), "Modifiable Field A");
        table.addMethod(TestForce.class.getMethod("setModifFieldB", float.class), "Modifiable Field B");
        table.addMethod(TestForce.class.getMethod("setModifFieldC", String.class), "Modifiable Field C");
        addInstantiateButton(table);
        PluginTester.setTable(table);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////// UITLITIES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static <T> void addInstantiateButton(FieldsHunterTable<T> tab) {
        tab.add(new VisTextButton("Instantiate") {{
            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println(Try.defaults(tab::getInstance, null));
                }
            });
        }});
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////// UTILITIES CLASSES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static class TestForce extends PhysicForce {
        int ConsFieldA;
        float ConsFieldB;
        String ConsFieldC;
        int modifFieldA;
        float modifFieldB;
        String modifFieldC;

        private float privField;

        public TestForce(Array consFieldA) {
        }

        public TestForce(int consFieldA) {
            ConsFieldA = consFieldA;
        }


        public TestForce(int consFieldA, float consFieldB, String consFieldC) {
            ConsFieldA = consFieldA;
            ConsFieldB = consFieldB;
            ConsFieldC = consFieldC;
        }

        public TestForce() {
        }

        public TestForce(int consFieldA, float consFieldB) {
            ConsFieldA = consFieldA;
            ConsFieldB = consFieldB;
        }

        public void setConsFieldA(int consFieldA) {
            ConsFieldA = consFieldA;
        }

        public void setConsFieldB(float consFieldB) {
            ConsFieldB = consFieldB;
        }

        public void setConsFieldC(String consFieldC) {
            ConsFieldC = consFieldC;
        }

        public void setModifFieldA(int modifFieldA) {
            this.modifFieldA = modifFieldA;
        }

        public void setModifFieldB(float modifFieldB) {
            this.modifFieldB = modifFieldB;
        }

        public void setModifFieldC(String modifFieldC) {
            this.modifFieldC = modifFieldC;
        }

        public void setPrivField(float privField) {
            this.privField = privField;
        }

        @Override
        public String toString() {
            return "TestForce{" +
                    "ConsFieldA=" + ConsFieldA +
                    ", ConsFieldB=" + ConsFieldB +
                    ", ConsFieldC='" + ConsFieldC + '\'' +
                    ", modifFieldA=" + modifFieldA +
                    ", modifFieldB=" + modifFieldB +
                    ", modifFieldC='" + modifFieldC + '\'' +
                    ", privField=" + privField +
                    '}';
        }
    }

}