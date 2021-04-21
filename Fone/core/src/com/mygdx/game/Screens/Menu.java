package com.mygdx.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Main;




public class Menu implements Screen{
    Main main;
    private Button btn;
    private TextureRegionDrawable drawable;
    int GL_COLOR_BUFFER_BIT = 0x4000;
    boolean downBtn;
    Stage stage;


    public Menu(Main main){this.main = main;}



    @Override
    public void show() {




        downBtn = false;
        stage = new Stage();





        drawable = new TextureRegionDrawable(Main.btnstart);
        btn = new Button(drawable);
        btn.setSize(550,450);
        btn.setPosition(450, 1700);

        stage.addActor(btn);

        btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(Main.gameSc);
                return super.touchDown(event, x, y, pointer, button);
            }
        });



        Gdx.input.setInputProcessor(stage);

    }





    public void showBG(){
        Main.batch.draw(Main.fon,0, 0, 1024, 2300);

    }

    @Override
    public void render(float delta) {

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
    public void pause() { Gdx.input.setInputProcessor(null);}

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() { Gdx.input.setInputProcessor(null); }

}


