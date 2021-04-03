package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Main;




public class Menu implements Screen{
    Main main;
    private int width, height;
    private Button btnstart;
    private Skin skin;
    private BitmapFont font;
    int GL_COLOR_BUFFER_BIT = 0x4000;
    public OrthographicCamera cam;
    public float ppuX;
    public float ppuY;
    float CAMERA_WIDTH = 800F;
    float CAMERA_HEIGHT = 480F;
    boolean downBtn;
    Stage stage;


    public Menu(Main main){this.main = main;}



    @Override
    public void show() {



        
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        skin = new Skin();
        downBtn = false;

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);


        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        btnstart = new Button();

        btnstart.setSize(100, 200);
        btnstart.setPosition(10, 10);
        stage.addActor(btnstart);





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
}


