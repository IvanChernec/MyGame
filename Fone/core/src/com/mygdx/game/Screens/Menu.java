package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;




public class Menu implements Screen, InputProcessor {
    Main main;
    private int width, height;
    private BitmapFont font;
    private Button btn;
    private TextureRegionDrawable drawable;
    int GL_COLOR_BUFFER_BIT = 0x4000;
    public OrthographicCamera cam;
    public float ppuX;
    public float ppuY;
    float CAMERA_WIDTH = 800F;
    float CAMERA_HEIGHT = 480F;
    boolean downBtn;


    public Menu(Main main){this.main = main;}



    @Override
    public void show() {



        
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        downBtn = false;



        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);

        drawable = new TextureRegionDrawable(Main.btnstart);
        btn = new Button(drawable);
        btn.setSize(200,200);
        btn.setPosition(20, 30);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                downBtn = true;
            }
        });


        Gdx.input.setInputProcessor(this);








    }



    public void setCam(float x, float y) {
        this.cam.position.set(x, y,0);
        this.cam.update();
    }


    public void showBG(){
        Main.batch.draw(Main.fon,0, -32, 1024 , 512);

    }

    @Override
    public void render(float delta) {

        setCam(CAMERA_WIDTH/2, CAMERA_HEIGHT / 2f);
        Main.batch.setProjectionMatrix(this.cam.combined);
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);



        Main.batch.begin();
        showBG();
        btn.draw(Main.batch, 1);
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }

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
        if (downBtn){
            main.setScreen(Main.gameSc);
            return true;
        }else return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
}


