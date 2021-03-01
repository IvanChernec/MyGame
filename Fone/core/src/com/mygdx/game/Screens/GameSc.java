package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Actor.Bullet;
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Actor.Player;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.BulletGenerator;
import com.mygdx.game.Tools.Joystick;
import com.mygdx.game.Tools.Point2D;
import com.mygdx.game.Tools.Wave;

import java.util.ArrayList;


public class GameSc implements Screen {

    Joystick joy, joy2;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    BulletGenerator bulletGenerator;

    public static Integer n = 0;

    Main main;

    public GameSc(Main main){
        this.main = main;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor() {
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
        });

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
        bulletGenerator.update(joy2);
        for (int i = 0; i < bullets.size(); i++) {bullets.get(i).update();
            if (bullets.get(i).isOut){
                bullets.remove(i--);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {enemies.get(i).update();
        if (enemies.get(i).getHealth() < 1){enemies.remove(i--);}}
        colis();
        wave.update();
    }
    public void gameRender(SpriteBatch batch){
        player.draw(batch);
        joy.draw(batch);
        joy2.draw(batch);
        for (int i = 0; i < bullets.size(); i++) {bullets.get(i).draw(batch);}
        for (int i = 0; i < enemies.size(); i++) {enemies.get(i).draw(batch);}
    }

    public void loadActor(){
        joy = new Joystick(Main.circle, Main.stick, new Point2D(200, 200), Main.HEIGHT/3);
        joy2 = new Joystick(Main.circle, Main.stick, new Point2D(Main.WIDTH - ((Main.HEIGHT/3)/2 + (Main.HEIGHT/3)/4), (Main.HEIGHT/3)/2 + (Main.HEIGHT/3)/4), Main.HEIGHT / 3);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        bulletGenerator = new BulletGenerator();
        player = new Player(Main.stick, new Point2D(Main.WIDTH/ 2, Main.HEIGHT/2), 10, Main.HEIGHT/20, 100);
        wave = new Wave(5,1,5);
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer){
        for (int i = 0; i < 5; i++) {
            joy.update(x, y, isDownTouch, pointer);
            joy2.update(x, y, isDownTouch, pointer);
        }
    }

    public void colis(){
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).bounds.overLaps(enemies.get(j).bounds)){
                    enemies.get(j).hit();
                    bullets.remove(i);
                    break;
                }
            }
        }
    }
}
