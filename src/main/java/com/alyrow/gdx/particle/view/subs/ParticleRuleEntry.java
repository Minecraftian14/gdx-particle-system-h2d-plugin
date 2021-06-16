package com.alyrow.gdx.particle.view.subs;

import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.rules.*;
import com.alyrow.gdx.particle.utilities.EasyParsers;
import com.alyrow.gdx.particle.view.util.CollapsibleTable;
import com.alyrow.gdx.particle.view.util.Entry;
import com.alyrow.gdx.particle.view.util.StaticFieldListView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;

import static com.alyrow.gdx.particle.utilities.CUD.title;


public class ParticleRuleEntry extends Entry<ParticleRules> {

    // ParticleEmissionLight
    private final VisTextField fie_rays;
    private final VisTextButton fie_color;
    private ColorPicker picker = null;
    private Color color;
    private final VisTextField fie_distance;


    // ParticleLife
    private final VisTextField fie_life;
    private final VisCheckBox fie_outer;

    // ParticleEmissionDuration
    private final VisTextField fie_duration;

    // ParticleEmissionNumber
    private final VisCheckBox fie_isRandom;
    private final StaticFieldListView fie_mode;
    private final VisTextField fie_seconds;
    private final VisTextField fie_min;
    private final VisTextField fie_max;


    public ParticleRuleEntry() {

        CollapsibleTable table;

        // ParticleEmissionLight
        add(table = new CollapsibleTable(title("EMISSION LIGHT")))/*.expandX().fillX()*/.growX().row();

        table.addEntry("Number of Rays", fie_rays = new VisTextField());
        table.addEntry("Color", fie_color = new VisTextButton("Choose"));
        table.addEntry("Distance", fie_distance = new VisTextField());

        // ParticleLife
        add(table = new CollapsibleTable(title("PARTICLE LIFE"))).padTop(15).growX().row();

        table.addEntry("Life", fie_life = new VisTextField());
        table.addEntry("is Outer", fie_outer = new VisCheckBox(null));

        // ParticleEmissionDuration
        add(table = new CollapsibleTable(title("EMISSION DURATION"))).padTop(15).growX().row();

        table.addEntry("Duration", fie_duration = new VisTextField());

        // ParticleEmissionNumber
        add(table = new CollapsibleTable(title("EMISSION NUMBER"))).padTop(15).growX().row();

        table.addEntry("is Random", fie_isRandom = new VisCheckBox(null));
        table.addEntry("Particle Emission", (fie_mode = new StaticFieldListView(ParticleEmissionNumber.class)).getMainTable());
        table.addEntry("Seconds", fie_seconds = new VisTextField());
        table.addEntry("Minimum", fie_min = new VisTextField());
        table.addEntry("Maximum", fie_max = new VisTextField());

        fie_min.setDisabled(true);
        fie_max.setDisabled(true);

        Gdx.app.postRunnable(() -> picker = new ColorPicker(new ColorPickerAdapter() {
            @Override
            public void finished(Color newColor) {
                color = newColor;
                fie_color.setColor(newColor);
            }
        }));

        fie_color.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ParticleRuleEntry.this.addActor(picker.fadeIn());
            }
        });

        fie_isRandom.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean checked = fie_isRandom.isChecked();
                fie_seconds.setDisabled(checked);
                fie_max.setDisabled(!checked);
                fie_min.setDisabled(!checked);
            }
        });

    }

    @Override
    public ParticleRules getValue() {
        ParticleRules rules = new ParticleRules();

        rules.setLight(new ParticleEmissionLight(color) {{
            rays = EasyParsers.parse(fie_rays.getText(), 5);
            distance = EasyParsers.parse(fie_distance.getText(), 5f);
        }});

        rules.setLife(new ParticleLife(EasyParsers.parse(fie_life.getText(), 10f), fie_outer.isChecked()));

        try {
            rules.setDuration(new ParticleEmissionDuration(Float.parseFloat(fie_duration.getText())));
        } catch (NumberFormatException e) {
            String txt = fie_duration.getText().strip().toLowerCase();
            rules.setDuration(new ParticleEmissionDuration(txt.equals("true") || txt.equals("infinite") || txt.equals("infinity")));
        }

        if (fie_isRandom.isChecked())
            rules.setNumber(new ParticleEmissionNumberRandom(fie_mode.getValue(), EasyParsers.parse(fie_min.getText(), 0), EasyParsers.parse(fie_max.getText(), 0)));
        else
            rules.setNumber(new ParticleEmissionNumber(fie_mode.getValue(), EasyParsers.parse(fie_seconds.getText(), 1)));

        return rules;
    }
}
