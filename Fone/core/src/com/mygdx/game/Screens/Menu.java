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
    private Main main;
    private Button btnKn, btnM;
    private TextureRegionDrawable knight, mage;
    private int GL_COLOR_BUFFER_BIT = 0x4000;
    private Stage stage;
    public static boolean skill;




    public Menu(Main main){this.main = main; }



    @Override
    public void show() {
        stage = new Stage();

        knight = new TextureRegionDrawable(Main.player);
        mage = new TextureRegionDrawable(Main.playerM);


        btnM = new Button(mage);
        btnM.setSize(400, 400);
        btnM.setPosition(600, 1000);

        btnKn = new Button(knight);
        btnKn.setSize(400,400);
        btnKn.setPosition(50, 1000);

        stage.addActor(btnKn);
        stage.addActor(btnM);

        btnM.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Main.WRITE(Main.skill, true);
                skill = Main.READ_BOOLEAN(Main.skill);
                main.setScreen(Main.gameSc);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btnKn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                main.setScreen(Main.gameSc);
                skill = Main.READ_BOOLEAN(Main.skill);
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
        btnKn.draw(Main.batch, 1);
        btnM.draw(Main.batch, 1);
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


