package com.alyrow.gdx.particle.view.util;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField;

public abstract class Entry<ValueType> extends VisTable {

    public abstract ValueType getValue();

}
