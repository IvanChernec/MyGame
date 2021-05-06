package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    private Texture fon, attackPng;
    private TextureRegionDrawable drawable;
    private Button attack;
    private Main main;
    private Skin skin;
    private BitmapFont enemy;
    private BitmapFont player;
    private Label enemyL, playerL;
    private Label.LabelStyle enemySt, playerSt;
    private int GL_COLOR_BUFFER_BIT = 0x4000;



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
        enemy = new BitmapFont();
        player = new BitmapFont();
        enemySt = new Label.LabelStyle(enemy, null);
        playerSt = new Label.LabelStyle(player, null);
        playerL = new Label(String.valueOf(Res.player.getHealth()), playerSt);
        playerL.setFontScale(3);
        playerL.setPosition(100, 1800);
        enemyL = new Label(String.valueOf(enemies.get(GameSc.n).getHealth()), enemySt);
        enemyL.setPosition(700, 1800);
        enemyL.setFontScale(3);
        

        attack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    enemies.get(GameSc.n).hit();
                    Res.player.hit();
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0){
                    Res.player.moneyMob(10);
                    main.setScreen(Main.gameSc);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                }else {
                    main.setScreen(Main.gameSc);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                }
                return true;
            }
        });
        stage.addActor(attack);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(fon, 0, 0, 1100, 2300);
        attack.draw(Main.batch, 1);
        Main.batch.draw(Main.player, 100, 1500, 200, 200);
        Main.batch.draw(enemies.get(GameSc.n).img, 700, 1500, 200, 200);
        enemyL.draw(Main.batch, 1);
        playerL.draw(Main.batch, 1);
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
