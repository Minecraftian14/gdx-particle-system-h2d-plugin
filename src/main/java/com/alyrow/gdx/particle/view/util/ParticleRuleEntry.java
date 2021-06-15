package com.alyrow.gdx.particle.view.util;

import com.alyrow.gdx.particle.ParticleRules;
import com.alyrow.gdx.particle.rules.*;
import com.alyrow.gdx.particle.utilities.EasyParsers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;

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
    private final ParticleEmissionModeEntry fie_mode;
    private final VisTextField fie_seconds;
    private final VisTextField fie_min;
    private final VisTextField fie_max;


    public ParticleRuleEntry() {

        // ParticleEmissionLight
        add(new VisLabel(title("EMISSION LIGHT"))).space(5).colspan(2).growX().row();

        add(new VisLabel(tag("Number of Rays"))).space(5).growX();
        add(fie_rays = new VisTextField()).space(5).growX().row();

        add(new VisLabel(tag("Color"))).space(5).growX();
        add(fie_color = new VisTextButton("Choose")).space(5).growX().row();

        add(new VisLabel(tag("Distance"))).space(5).growX();
        add(fie_distance = new VisTextField()).space(5).growX().row();


        // ParticleLife
        add(new VisLabel(title("PARTICLE LIFE"))).padTop(15).space(5).colspan(2).growX().row();

        add(new VisLabel(tag("Life"))).space(5).growX();
        add(fie_life = new VisTextField()).space(5).growX().row();

        add(new VisLabel(tag("is Outer"))).space(5).growX();
        add(fie_outer = new VisCheckBox(null)).space(5).growX().row();

        // ParticleEmissionDuration
        add(new VisLabel(title("EMISSION DURATION"))).padTop(15).space(5).colspan(2).growX().row();

        add(new VisLabel(tag("Duration"))).space(5).growX();
        add(fie_duration = new VisTextField()).space(5).growX().row();

        // ParticleEmissionNumber
        add(new VisLabel(title("EMISSION NUMBER"))).padTop(15).space(5).colspan(2).growX().row();

        add(new VisLabel(tag("is Random"))).space(5).growX();
        add(fie_isRandom = new VisCheckBox(null)).space(5).growX().row();

        add(fie_mode = new ParticleEmissionModeEntry()).colspan(2).space(5).growX().row();

        VisLabel lbl_seconds = new VisLabel(tag("Seconds"));
        add(lbl_seconds).space(5).growX();
        add(fie_seconds = new VisTextField()).space(5).growX().row();

        VisLabel lbl_min = new VisLabel(tag("Minimum"));
        add(lbl_min).space(5).growX();
        add(fie_min = new VisTextField()).space(5).growX().row();
        fie_min.setDisabled(true);

        VisLabel lbl_max = new VisLabel(tag("Maximum"));
        add(lbl_max).space(5).growX();
        add(fie_max = new VisTextField()).space(5).growX().row();
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

    private String title(String name) {
        return String.format("[#FFFFFF]%s[]", name);
    }

    private String tag(String name) {
        return String.format("[#BBBBBB]%s:[]", name);
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
