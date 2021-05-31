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
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Actor.Player;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;
import com.mygdx.game.Screens.GameSc;
import com.mygdx.game.Screens.Menu;

import static com.mygdx.game.Resources.Res.enemies;

public class Battle implements Screen {
    private Stage stage;
    private Texture attackPng;
    private TextureRegionDrawable drawable, skillDr, skillShockDr, skillSnowDr, skillKnightDr, manaPoDr, healPoDr;
    private Button attack, skillFire, skillShock, skillSnow, skillKnight, healP, manaP;
    private Main main;
    private BitmapFont enemy, player, mana, healPBt, manaPBt;
    private Label enemyL, playerL, manaL, healPL, manaPL;
    private Label.LabelStyle enemySt, playerSt, manaSt, healPSt, manaPSt;
    private int GL_COLOR_BUFFER_BIT = 0x4000;



    public Battle(Main main){this.main = main;}

    @Override
    public void show() {
        Main.battleMs.play();
        attackPng = new Texture("attack.png");

        stage = new Stage();

        enemy = new BitmapFont();
        player = new BitmapFont();
        mana = new BitmapFont();
        healPBt = new BitmapFont();
        manaPBt = new BitmapFont();

        manaPSt = new Label.LabelStyle(manaPBt, null);
        healPSt = new Label.LabelStyle(healPBt, null);
        manaSt = new Label.LabelStyle(mana, Color.SKY);
        enemySt = new Label.LabelStyle(enemy, Color.RED);
        playerSt = new Label.LabelStyle(player, Color.RED);

        healPL = new Label("Heal:" + Res.player.getHealP() + "/10", healPSt);
        healPL.setFontScale(2);
        healPL.setPosition(70, 450);

        manaPL = new Label("Mana: " + Res.player.getManaP() + "/10", manaPSt);
        manaPL.setFontScale(2);
        manaPL.setPosition(270, 450);

        manaL = new Label(String.valueOf("Mana: " + Res.player.getMana()), manaSt);
        manaL.setFontScale(3);
        manaL.setPosition(100, 1900);

        playerL = new Label(String.valueOf("Hp: " + Res.player.getHealth()), playerSt);
        playerL.setFontScale(3);
        playerL.setPosition(100, 1800);

        enemyL = new Label(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()), enemySt);
        enemyL.setPosition(700, 1800);
        enemyL.setFontScale(3);

        skillDr = new TextureRegionDrawable(Main.fire);
        skillShockDr = new TextureRegionDrawable(new Texture("shock.png"));
        skillSnowDr = new TextureRegionDrawable(Main.snow);
        drawable = new TextureRegionDrawable(attackPng);
        skillKnightDr = new TextureRegionDrawable(Main.skillJKTx);
        manaPoDr = new TextureRegionDrawable(Main.mana);
        healPoDr = new TextureRegionDrawable(Main.healTx);


        skillKnight = new Button(skillKnightDr);
        skillFire = new Button(skillDr);
        skillShock = new Button(skillShockDr);
        skillSnow = new Button(skillSnowDr);
        attack = new Button(drawable);
        healP = new Button(healPoDr);
        manaP = new Button(manaPoDr);

        manaP.setPosition(250, 500);
        manaP.setSize(200, 200);
        manaP.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getManaP() > 0){
                    Res.player.setManaP(-1);
                    Res.player.shopMana();
                    manaPL.setText("Mana: " + Res.player.getManaP() + "/10");
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        healP.setPosition(20, 500);
        healP.setSize(200, 200);
        healP.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getHealP() > 0){
                    Res.player.setHealP(-1);
                    Res.player.shopHeal();
                    healPL.setText("Heal:" + Res.player.getHealP() + "/10");
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        skillKnight.setPosition(250, 150);
        skillKnight.setSize(200, 200);
        skillKnight.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    if (Res.player.getMana() >= 10) {
                        Res.player.manaSkill(10);
                        enemies.get(GameSc.n).hitSkill(30);
                        Res.player.hit(Enemy.dmgE);
                    }
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0 && !Res.boss){
                    Res.player.moneyMob(Res.nagr);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 100;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    main.setScreen(Main.gameSc);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                }else if (Res.player.getHealth() <= 0){
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.shopSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }else if (Res.boss && Res.player.getHealth() > 0){
                    Res.player.moneyMob(1000);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 10000;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    Res.bossHP += 1000;
                    Main.WRITE(Main.bossHP, Res.bossHP);
                    Enemy.rad = Main.WIDTH/20;
                    Enemy.hlth = Main.READ_INT(Main.hlth);
                    Enemy.dmgE = Main.READ_INT(Main.dmgE);
                    Player.lvlUpM();
                    main.setScreen(Main.gameSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        skillShock.setPosition(700, 150);
        skillShock.setSize(200, 200);
        skillShock.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    if (Res.player.getMana() >= 20) {
                        Res.player.manaSkill(20);
                        enemies.get(GameSc.n).hitSkill(20);
                        Res.player.hit(Enemy.dmgE);
                    }
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0 && !Res.boss){
                    Res.player.moneyMob(Res.nagr);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 100;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    main.setScreen(Main.gameSc);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                }else if (Res.player.getHealth() <= 0){
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.shopSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }else if (Res.boss && Res.player.getHealth() > 0){
                    Res.player.moneyMob(1000);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 10000;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    Res.bossHP += 1000;
                    Main.WRITE(Main.bossHP, Res.bossHP);
                    Enemy.dmgE = Main.READ_INT(Main.dmgE);
                    Enemy.rad = Main.WIDTH/20;
                    Enemy.hlth = Main.READ_INT(Main.hlth);
                    Player.lvlUpM();
                    main.setScreen(Main.gameSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        skillSnow.setPosition(450, 150);
        skillSnow.setSize(200, 200);
        skillSnow.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){

                    if (Res.player.getMana() >= 5) {
                        Res.player.manaSkill(5);
                        enemies.get(GameSc.n).hitSkill(5);
                        Res.player.hit(Enemy.dmgE);
                    }
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0 && !Res.boss){
                    Res.player.moneyMob(Res.nagr);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 100;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    main.setScreen(Main.gameSc);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                }else if (Res.player.getHealth() <= 0){
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.shopSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }else if (Res.boss && Res.player.getHealth() > 0){
                    Res.player.moneyMob(1000);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 10000;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    Res.bossHP += 1000;
                    Main.WRITE(Main.bossHP, Res.bossHP);
                    Enemy.dmgE = Main.READ_INT(Main.dmgE);
                    Enemy.rad = Main.WIDTH/20;
                    Enemy.hlth = Main.READ_INT(Main.hlth);
                    Player.lvlUpM();
                    main.setScreen(Main.gameSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        skillFire.setPosition(250, 150);
        skillFire.setSize(200, 200);
        skillFire.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){

                    if (Res.player.getMana() >= 10) {
                        Res.player.manaSkill(10);
                        enemies.get(GameSc.n).hitSkill(10);
                        Res.player.hit(Enemy.dmgE);
                    }
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0 && !Res.boss){
                    Res.player.moneyMob(Res.nagr);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 100;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    main.setScreen(Main.gameSc);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                }else if (Res.player.getHealth() <= 0){
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.shopSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }else if (Res.boss && Res.player.getHealth() > 0){
                    Res.player.moneyMob(1000);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 10000;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    Res.bossHP += 1000;
                    Main.WRITE(Main.bossHP, Res.bossHP);
                    Enemy.dmgE = Main.READ_INT(Main.dmgE);
                    Enemy.rad = Main.WIDTH/20;
                    Enemy.hlth = Main.READ_INT(Main.hlth);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                    Res.boss = false;
                    main.setScreen(Main.gameSc);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        attack.setPosition(0,100);
        attack.setSize(300, 300);
        attack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (enemies.get(GameSc.n).getHealth() > 0 && Res.player.getHealth() > 0){
                    enemies.get(GameSc.n).hit();
                    Res.player.hit(Enemy.dmgE);
                    playerL.setText(String.valueOf("Hp: " + Res.player.getHealth()));
                    manaL.setText(String.valueOf("Mana: " + Res.player.getMana()));
                    enemyL.setText(String.valueOf("Hp: " + enemies.get(GameSc.n).getHealth()));
                }else if (Res.player.getHealth() > 0 && !Res.boss){
                    Res.player.moneyMob(Res.nagr);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 100;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    main.setScreen(Main.gameSc);
                    Player.lvlUpM();
                    Main.battleMs.stop();
                }else if (Res.player.getHealth() <= 0){
                    playerL.setText(String.valueOf(0));
                    enemyL.setText(String.valueOf(enemies.get(GameSc.n).getHealth()));
                    main.setScreen(Main.shopSc);
                    Res.boss = false;
                    Main.battleMs.stop();
                }else if (Res.boss && Res.player.getHealth() > 0){
                    Res.player.moneyMob(1000);
                    playerL.setText(String.valueOf(Res.player.getHealth()));
                    enemyL.setText(String.valueOf(0));
                    Res.player.xp += 10000;
                    Main.WRITE(Main.xpF, Res.player.xp);
                    Enemy.dmgE = Main.READ_INT(Main.dmgE);
                    Res.bossHP += 1000;
                    Main.WRITE(Main.bossHP, Res.bossHP);
                    Enemy.rad = Main.WIDTH/20;
                    Enemy.hlth = Main.READ_INT(Main.hlth);
                    Player.lvlUpM();
                    Res.boss = false;
                    main.setScreen(Main.gameSc);
                    Main.battleMs.stop();
                }
                return true;
            }
        });



        stage.addActor(attack);
        if (Main.READ_BOOLEAN(Main.skill)){ stage.addActor(skillFire); }
        if (Main.READ_BOOLEAN(Main.skill) && Res.snowSk){ stage.addActor(skillSnow); }
        if (Main.READ_BOOLEAN(Main.skill) && Res.shockSk) { stage.addActor(skillShock); }
        if (!Main.READ_BOOLEAN(Main.skill) && Res.skillKnight){ stage.addActor(skillKnight); }
        stage.addActor(healP);
        stage.addActor(manaP);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(Res.fon2, 0, 0, 1100, 2300);
        manaPL.draw(Main.batch, 1);
        healP.draw(Main.batch, 1);
        healPL.draw(Main.batch, 1);
        manaP.draw(Main.batch, 1);

        if (Main.READ_BOOLEAN(Main.skill)) {
            skillFire.draw(Main.batch, 1);
        }
        if (Main.READ_BOOLEAN(Main.skill) && Res.snowSk){
            skillSnow.draw(Main.batch, 1);
        }
        if (Main.READ_BOOLEAN(Main.skill) && Menu.skill) {
            skillShock.draw(Main.batch, 1);
        }
        if (!Main.READ_BOOLEAN(Main.skill) && Res.skillKnight){
            skillKnight.draw(Main.batch, 1);
        }
        attack.draw(Main.batch, 1);
        Main.batch.draw(Res.player.img, 100, 1500, 200, 200);
        if (Res.boss){
            enemyL.setPosition(700, 2100);
            Main.batch.draw(enemies.get(GameSc.n).img, 500, 1500, 500, 500);
        }else {
            Main.batch.draw(enemies.get(GameSc.n).img, 700, 1500, 200, 200);
        }
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
