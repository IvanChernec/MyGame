package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Actor.Player;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Joystick;
import com.mygdx.game.Tools.Point2D;
import com.mygdx.game.Tools.Wave;

import java.util.ArrayList;


public class GameSc implements Screen {

    Joystick joy;
    public static Player player;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    private int GL_COLOR_BUFFER_BIT = 0x4000;
    InputProcessor inputProcessor;

    public static Integer n = 0;

    Main main;

    public GameSc(Main main){
        this.main = main;
    }
    @Override
    public void show() {

        inputProcessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int) screenY, true, pointer);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int) screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int)screenX, (int) screenY, true, pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        };

        Gdx.input.setInputProcessor(inputProcessor);
        loadActor();

    }

    @Override
    public void render(float delta) {
        gameUpdate();
        Main.batch.begin();
        gameRender(Main.batch);
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

    public void gameUpdate(){
        player.setDirection(joy.getDirection());
        player.update();
        for (int i = 0; i < enemies.size(); i++) {enemies.get(i).update();
        if (enemies.get(i).getHealth() < 1){enemies.remove(i--);}}
        colis();
        wave.update();
    }
    public void gameRender(SpriteBatch batch){
        batch.draw(Main.fonGame, 0, 0, 1100, 2300);
        player.draw(batch);
        joy.draw(batch);
        for (int i = 0; i < enemies.size(); i++) {enemies.get(i).draw(batch);}
    }

    public void loadActor(){
        joy = new Joystick(Main.circle, Main.stick, new Point2D(500, 200), Main.HEIGHT/6);
        enemies = new ArrayList<>();
        player = new Player(Main.stick, new Point2D(Main.WIDTH/ 2, Main.HEIGHT/2), 10, Main.HEIGHT/40, 100);
        wave = new Wave(5,1,5);
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer){
        for (int i = 0; i < 5; i++) {
            joy.update(x, y, isDownTouch, pointer);
        }
    }

    public void colis(){
        for (int j = 0; j < enemies.size(); j++) {
            if (player.bounds.overLaps(enemies.get(j).bounds)){
                main.setScreen(Main.battle);
                break;
            }
        }
    }
}

