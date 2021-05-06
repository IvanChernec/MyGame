package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;


public class Shop implements Screen {
    private Button attack, exit;
    private Stage stage;
    private Texture fon, attackTx, exitTx;
    private Main main;
    private TextureRegionDrawable attackDr, exitDr;

    public Shop(Main main){ this.main = main; }

    @Override
    public void show() {
        attackTx = new Texture("Sword.png");
        exitTx = new Texture("back.png");
        fon = new Texture("shopFon.jpg");

        attackDr = new TextureRegionDrawable(attackTx);
        exitDr = new TextureRegionDrawable(exitTx);

        attack = new Button(attackDr);
        exit = new Button(exitDr);
        stage = new Stage();

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

        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(fon, 0, 0, 1024, 2300);
        attack.draw(Main.batch, 1);
        exit.draw(Main.batch, 1);
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
