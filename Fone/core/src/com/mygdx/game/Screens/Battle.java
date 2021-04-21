package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;

public class Battle implements Screen {
    private Stage stage;
    private Texture fon, attackPng;
    private TextureRegionDrawable drawable;
    private Button attack;
    private Main main;
    private Skin skin;

    public Battle(Main main){this.main = main;}

    @Override
    public void show() {
        skin = new Skin();
        attackPng = new Texture("attack.png");
        fon = new Texture("fonAttack.jpg");
        stage = new Stage();
        drawable = new TextureRegionDrawable(attackPng);
        attack = new Button(drawable);

        attack.setPosition(300,200);
        attack.setSize(500, 500);



    }

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(fon, 0, 0, 1100, 2300);
        attack.draw(Main.batch, 1);
        Main.batch.draw(Main.stick, 100, 1500, 200, 200);
        Main.batch.draw(Main.stick, 700, 1500, 200, 200);
        Main.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
