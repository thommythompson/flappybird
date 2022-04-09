package com.han.flappybird.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {
    public static final int SIZE_BIG = 25;
    public static final int SIZE_SMALL = 15;

    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public Font() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.color = Color.WHITE;
        parameter.size = 15;
    }

    public void setColor(Color color) {
        parameter.color = color;
    }

    public void setSize(int size) {
        parameter.size = size;
    }

    public BitmapFont getBitmap() {
        return generator.generateFont(parameter);
    }
}
