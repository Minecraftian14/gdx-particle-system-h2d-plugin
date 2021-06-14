package com.alyrow.gdx.particle.view.gen;

import com.alyrow.gdx.particle.Plugin;
import com.alyrow.gdx.particle.physics.PhysicForce;
import com.mcxiv.app.PluginTester;

class NAME_ME_PLSTest {

    public static void main(String[] args) throws InterruptedException {
        PluginTester.launchTest(DefaultConfiguration.get(200, 150), NAME_ME_PLSTest::TestStarter);
    }

    private static void TestStarter() {
        Plugin.isDebugging = true;

        Plugin plugin = new Plugin();
        PluginTester.setPlugin(plugin);

        PluginTester.setTable(new FieldExplorer(TestForce.class));
    }

    public static class TestForce extends PhysicForce {
        int ConsFieldA;
        float ConsFieldB;
        String ConsFieldC;
        int modifFieldA;
        float modifFieldB;
        String modifFieldC;

        private float privField;

        public TestForce(int consFieldA, float consFieldB, String consFieldC) {
            ConsFieldA = consFieldA;
            ConsFieldB = consFieldB;
            ConsFieldC = consFieldC;
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
    }

}