package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;
import com.mygdx.game.Tools.Wave;

import static com.mygdx.game.Main.WRITE;
import static com.mygdx.game.Main.attackTx;
import static com.mygdx.game.Main.exitTx;
import static com.mygdx.game.Main.fonShop;
import static com.mygdx.game.Main.forestTx;
import static com.mygdx.game.Main.healTx;
import static com.mygdx.game.Main.japanTx;
import static com.mygdx.game.Main.shock;
import static com.mygdx.game.Main.skillJKTx;


public class Shop implements Screen {
    private Button attack, exit, heal, shockBtn, snow, forest, japan, skillJK, mana, maxHealth;
    private Stage stage;
    private Main main;
    private TextureRegionDrawable attackDr, exitDr, healDr, shockDr, snowDr, forestDr, japanDr, skillJKDr, manaDr, maxHealthDr;
    private int GL_COLOR_BUFFER_BIT = 0x4000;
    private BitmapFont help, balance, attackP, healBt, skillBt, mapBt, invH, invM, maxHealthBt;
    private Label helpL, balanceL, attackL, healL, skillL, mapL, invHL, invML, maxHealthL;
    private Label.LabelStyle helpSt, balanceSt, attackSt, healSt, skillSt, mapSt, invHSt, invMSt, maxHealthSt;


    public Shop(Main main){ this.main = main; }

    @Override
    public void show() {
        help = new BitmapFont();
        balance = new BitmapFont();
        attackP = new BitmapFont();
        healBt = new BitmapFont();
        skillBt = new BitmapFont();
        mapBt = new BitmapFont();
        invH = new BitmapFont();
        invM = new BitmapFont();
        maxHealthBt = new BitmapFont();

        maxHealthSt = new Label.LabelStyle(maxHealthBt, null);
        invHSt = new Label.LabelStyle(invH, null);
        invMSt = new Label.LabelStyle(invM, null);
        skillSt = new Label.LabelStyle(skillBt, null);
        healSt = new Label.LabelStyle(healBt, null);
        helpSt = new Label.LabelStyle(help, null);
        attackSt = new Label.LabelStyle(attackP, null);
        balanceSt = new Label.LabelStyle(balance, null);
        mapSt = new Label.LabelStyle(mapBt, null);

        maxHealthL = new Label("HP: " + Res.player.getMaxHealth(), maxHealthSt);
        invHL = new Label("Heal:" + Res.player.getHealP() + "/10", invHSt);
        invML = new Label("Mana:" + Res.player.getManaP() + "/10", invMSt);
        mapL = new Label("Maps: ", mapSt);
        skillL = new Label("Skills: \n 100$", skillSt);
        healL = new Label("Price: 10$", healSt);
        attackL = new Label("Attack: " + Res.player.getDmg(), attackSt);
        helpL = new Label("Upgrade 100$", helpSt);
        balanceL = new Label("Balance: " + Res.player.getMoney() + "$", balanceSt);

        maxHealthL.setPosition(800, 70);
        maxHealthL.setFontScale(2);

        invML.setPosition(320, 70);
        invML.setFontScale(2);

        invHL.setPosition(120, 70);
        invHL.setFontScale(2);

        mapL.setPosition(500, 1150);
        mapL.setFontScale(2);

        healL.setPosition(250, 300);
        healL.setFontScale(2);

        balanceL.setPosition(800, 2050);
        balanceL.setFontScale(2);

        skillL.setPosition(500, 1600);
        skillL.setFontScale(2);

        helpL.setPosition(700, 300);
        helpL.setFontScale(2);

        attackL.setPosition(600, 70);
        attackL.setFontScale(2);

        japanDr = new TextureRegionDrawable(japanTx);
        skillJKDr = new TextureRegionDrawable(skillJKTx);
        forestDr = new TextureRegionDrawable(forestTx);
        attackDr = new TextureRegionDrawable(attackTx);
        exitDr = new TextureRegionDrawable(exitTx);
        healDr = new TextureRegionDrawable(healTx);
        shockDr = new TextureRegionDrawable(shock);
        snowDr = new TextureRegionDrawable(Main.snow);
        manaDr = new TextureRegionDrawable(Main.mana);
        maxHealthDr = new TextureRegionDrawable(Main.healthPlayer);

        maxHealth = new Button(maxHealthDr);
        mana = new Button(manaDr);
        forest = new Button(forestDr);
        snow = new Button(snowDr);
        shockBtn = new Button(shockDr);
        heal = new Button(healDr);
        attack = new Button(attackDr);
        exit = new Button(exitDr);
        japan = new Button(japanDr);
        skillJK = new Button(skillJKDr);

        stage = new Stage();

        maxHealth.setSize(150, 150);
        maxHealth.setPosition(800, 100);
        maxHealth.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100){
                    Res.player.moneyShop(100);
                    Res.player.upgradeHealth(10);
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                    maxHealthL.setText("HP: " + Res.player.getMaxHealth());
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        mana.setSize(200, 180);
        mana.setPosition(300, 100);
        mana.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 10 && Res.player.getManaP() != 10){
                    Res.player.moneyShop(10);
                    Res.player.setManaP(1);
                    invML.setText("Mana:" + Res.player.getManaP() + "/10");
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        japan.setSize(200, 200);
        japan.setPosition(350, 850);
        japan.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Res.a++;
                Main.WRITE(Wave.waveN, 2);
                Main.WRITE(Main.jpn, true);
                Res.jpn = true;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        forest.setSize(200, 200);
        forest.setPosition(100, 850);
        forest.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Res.a++;
                Main.WRITE(Wave.waveN, 1);
                Main.WRITE(Main.jpn, false);
                Res.jpn = false;
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        /**для мага(начало)*/

        snow.setSize(200, 200);
        snow.setPosition(350, 1300);
        snow.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100 && !Res.snowSk){
                    Res.player.moneyShop(100);
                    WRITE(Main.sn, true);
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        shockBtn.setSize(200, 200);
        shockBtn.setPosition(100, 1300);
        shockBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100 && !Res.shockSk){
                    Res.player.moneyShop(100);
                    WRITE(Main.sh, true);
                    Res.shockSk = true;
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        //для мага(конец)

        /**для рыцаря(начало)*/
        skillJK.setSize(200, 200);
        skillJK.setPosition(100, 1300);
        skillJK.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100 && !Res.skillKnight){
                    Res.player.moneyShop(100);
                    Main.WRITE(Main.skK, true);
                    Res.skillKnight = true;
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        //для рыцаря(конец)

        heal.setPosition(100, 100);
        heal.setSize(200, 200);
        heal.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 10 && Res.player.getHealP() != 10){
                    Res.player.moneyShop(10);
                    Res.player.setHealP(1);
                    invHL.setText("Heal:" + Res.player.getHealP() + "/10");
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        attack.setPosition(600, 100);
        attack.setSize(200, 200);
        attack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Res.player.getMoney() >= 100){
                    Res.player.moneyShop(100);
                    Res.player.upgradeDmg(1);
                    balanceL.setText("Balance: " + Res.player.getMoney() + "$");
                    attackL.setText("attack: " + Res.player.getDmg());
                }
                return true;
            }
        });

        exit.setPosition(0, 2050);
        exit.setSize(200, 200);
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
        if (Main.READ_BOOLEAN(Main.skill)){
            stage.addActor(shockBtn);
            stage.addActor(snow);
        }else {
            stage.addActor(skillJK);
        }
        stage.addActor(mana);
        stage.addActor(maxHealth);

        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(fonShop, 0, 0, 1200, 2300);
        invHL.draw(Main.batch, 1);
        invML.draw(Main.batch, 1);
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
        mana.draw(Main.batch, 1);
        maxHealth.draw(Main.batch, 1);
        maxHealthL.draw(Main.batch, 1);
        /** Маг */if (Main.READ_BOOLEAN(Main.skill)){
            shockBtn.draw(Main.batch, 1);
            snow.draw(Main.batch, 1);
        }/** Рыцарь*/else {
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
