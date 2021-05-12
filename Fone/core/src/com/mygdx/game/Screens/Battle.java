package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;

import static com.mygdx.game.Resources.Res.enemies;


public class Battle implements Screen {
    private Stage stage;
    private Texture attackPng;
    private TextureRegionDrawable drawable, skillDr;
    private Button attack, skill;
    private Main main;
    private Skin skin;
    private BitmapFont enemy, player, mana;
    private Label enemyL, playerL, manaL;
    private Label.LabelStyle enemySt, playerSt, manaSt;
    private int GL_COLOR_BUFFER_BIT = 0x4000;



    public Battle(Main main){this.main = main;}

    @Override
    public void show() {
        skin = new Skin();

        attackPng = new Texture("attack.png");
        stage = new Stage();
        skillDr = new TextureRegionDrawable(Main.fire);
        drawable = new TextureRegionDrawable(attackPng);
        skill = new Button(skillDr);
        attack = new Button(drawable);

        skill.setPosition(300, 150);
        skill.setSize(300, 300);
        skill.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    enemies.get(GameSc.n).hitSkill();
                    Res.player.hit();
                    if (Res.player.getMana() > 10) {
                        Res.player.manaSkill();
                    }
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0){
                    Res.player.moneyMob(10);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    main.setScreen(Main.gameSc);
                }else {
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.gameSc);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        attack.setPosition(0,150);
        attack.setSize(300, 300);
        enemy = new BitmapFont();
        player = new BitmapFont();
        mana = new BitmapFont();
        manaSt = new Label.LabelStyle(mana, Color.SKY);
        enemySt = new Label.LabelStyle(enemy, Color.RED);
        playerSt = new Label.LabelStyle(player, Color.RED);
        manaL = new Label(String.valueOf("Mana: " + Res.player.getMana()), manaSt);
        manaL.setFontScale(3);
        manaL.setPosition(100, 1900);
        playerL = new Label(String.valueOf("Hp: " + Res.player.getHealth()), playerSt);
        playerL.setFontScale(3);
        playerL.setPosition(100, 1800);
        enemyL = new Label(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()), enemySt);
        enemyL.setPosition(700, 1800);
        enemyL.setFontScale(3);
        

        attack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    enemies.get(GameSc.n).hit();
                    Res.player.hit();
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0){
                    Res.player.moneyMob(10);
                    main.setScreen(Main.gameSc);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                }else {
                    main.setScreen(Main.gameSc);
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                }
                return true;
            }
        });

        stage.addActor(skill);
        stage.addActor(attack);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(Res.fon2, 0, 0, 1100, 2300);
        if (Menu.skill){skill.draw(Main.batch, 1);}
        attack.draw(Main.batch, 1);
        Main.batch.draw(Main.player, 100, 1500, 200, 200);
        Main.batch.draw(enemies.get(GameSc.n).img, 700, 1500, 200, 200);
        enemyL.draw(Main.batch, 1);
        playerL.draw(Main.batch, 1);
        manaL.draw(Main.batch, 1);
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
