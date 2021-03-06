package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.utilities.EasyParsers;
import com.kotcrab.vis.ui.widget.VisTextField;

public class NumericField extends VisTextField {

    public NumericField() {
        this(0);
    }

    public NumericField(float number) {
        super("" + number);
    }

    public float getFloat() {
        float parse = EasyParsers.parse(getText().replaceAll("[^\\d.,_]*", ""), 0f);
        setText("" + parse);
        return parse;
    }

    public int getInt() {
        int parse = EasyParsers.parse(getText().replaceAll("[^\\d,_]*", ""), 0);
        setText("" + parse);
        return parse;
    }
}
