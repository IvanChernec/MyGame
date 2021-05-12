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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;
import com.mygdx.game.Tools.Wave;


public class Shop implements Screen {
    private Button attack, exit, heal, shockBtn, snow, forest, japan, skillJK;
    private Stage stage;
    private Texture fon, attackTx, exitTx, healTx, shock, forestTx, japanTx, skillJKTx;
    private Main main;
    private TextureRegionDrawable attackDr, exitDr, healDr, shockDr, snowDr, forestDr, japanDr, skillJKDr;
    private int GL_COLOR_BUFFER_BIT = 0x4000;
    private BitmapFont help, balance, attackP, healBt, skillBt, mapBt;
    private Label helpL, balanceL, attackL, healL, skillL, mapL;
    private Label.LabelStyle helpSt, balanceSt, attackSt, healSt, skillSt, mapSt;

    public static boolean japMap;

    public Shop(Main main){ this.main = main; }

    @Override
    public void show() {
        help = new BitmapFont();
        balance = new BitmapFont();
        attackP = new BitmapFont();
        healBt = new BitmapFont();
        skillBt = new BitmapFont();
        mapBt = new BitmapFont();

        skillSt = new Label.LabelStyle(skillBt, null);
        healSt = new Label.LabelStyle(healBt, null);
        helpSt = new Label.LabelStyle(help, null);
        attackSt = new Label.LabelStyle(attackP, null);
        balanceSt = new Label.LabelStyle(balance, null);
        mapSt = new Label.LabelStyle(mapBt, null);

        mapL = new Label("Maps: ", mapSt);
        skillL = new Label("Skills: ", skillSt);
        healL = new Label("10 money", healSt);
        attackL = new Label("attack: " + Res.player.getDmg(), attackSt);
        helpL = new Label("upgrade 100 money", helpSt);
        balanceL = new Label("Balance: " + Res.player.getMoney(), balanceSt);

        mapL.setPosition(500, 1150);
        mapL.setFontScale(2);

        healL.setPosition(200, 300);
        healL.setFontScale(2);

        helpL.setPosition(500, 300);
        helpL.setFontScale(2);

        balanceL.setPosition(800, 2050);
        balanceL.setFontScale(2);

        attackL.setPosition(500, 50);
        attackL.setFontScale(2);

        skillL.setPosition(500, 1600);
        skillL.setFontScale(2);

        attackTx = new Texture("Sword.png");
        exitTx = new Texture("back.png");
        fon = new Texture("shopFon.jpg");
        healTx = new Texture("heal.png");
        shock = new Texture("shock.png");
        forestTx = new Texture("forest.png");
        japanTx = new Texture("japan.png");
        skillJKTx = new Texture("skillJapanK.png");

        japanDr = new TextureRegionDrawable(japanTx);
        skillJKDr = new TextureRegionDrawable(skillJKTx);
        forestDr = new TextureRegionDrawable(forestTx);
        attackDr = new TextureRegionDrawable(attackTx);
        exitDr = new TextureRegionDrawable(exitTx);
        healDr = new TextureRegionDrawable(healTx);
        shockDr = new TextureRegionDrawable(shock);
        snowDr = new TextureRegionDrawable(Main.snow);

        forest = new Button(forestDr);
        snow = new Button(snowDr);
        shockBtn = new Button(shockDr);
        heal = new Button(healDr);
        attack = new Button(attackDr);
        exit = new Button(exitDr);
        japan = new Button(japanDr);
        skillJK = new Button(skillJKDr);
        stage = new Stage();

        //Написать слушателей для кнопок
        japan.setSize(200, 200);
        japan.setPosition(350, 850);
        japan.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Wave.n = 2;
                Res.fon1 = Main.fonJapan;
                Res.fon2 = Main.fonJapanBattle;
                Res.shop.img = Main.shop;
                Enemy.hlth = 30;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        forest.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Wave.n = 1;
                Res.fon1 = Main.fonGame;
                Res.fon2 = Main.fonAttack;
                Res.shop.img = Main.shop;
                Enemy.hlth = 5;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        forest.setSize(200, 200);
        forest.setPosition(100, 850);

        snow.setSize(200, 200);
        snow.setPosition(350, 1300);

        shockBtn.setSize(200, 200);
        shockBtn.setPosition(100, 1300);

        skillJK.setSize(200, 200);
        skillJK.setPosition(100, 1300);

        heal.setPosition(200, 100);
        heal.setSize(200, 200);
        heal.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 10){
                    Res.player.moneyShop(10);
                    Res.player.shopHeal();
                    balanceL.setText("Balance: " + Res.player.getMoney());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        attack.setPosition(500, 100);
        exit.setPosition(0, 2050);
        attack.setSize(200, 200);
        exit.setSize(200, 200);
        attack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100){
                    Res.player.moneyShop(100);
                    Res.player.upgradeDmg(1);
                    balanceL.setText("Balance: " + Res.player.getMoney());
                    attackL.setText("attack: " + Res.player.getDmg());
                }
                return true;
            }
        });

        exit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Res.player.position.setP(Res.player.position.getX(), 1700);
                main.setScreen(Main.gameSc);
                return true;
            }
        });

        stage.addActor(attack);
        stage.addActor(exit);
        stage.addActor(heal);
        stage.addActor(japan);
        stage.addActor(forest);

        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(fon, 0, 0, 1200, 2300);
        attack.draw(Main.batch, 1);
        exit.draw(Main.batch, 1);
        heal.draw(Main.batch, 1);
        helpL.draw(Main.batch, 1);
        balanceL.draw(Main.batch, 1);
        attackL.draw(Main.batch, 1);
        healL.draw(Main.batch, 1);
        skillL.draw(Main.batch, 1);
        mapL.draw(Main.batch, 1);
        forest.draw(Main.batch, 1);
        if (Menu.skill){
            shockBtn.draw(Main.batch, 1);
            snow.draw(Main.batch, 1);
        }else {
            skillJK.draw(Main.batch, 1);
        }
        japan.draw(Main.batch, 1);
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
