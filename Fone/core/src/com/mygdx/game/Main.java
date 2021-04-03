package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Screens.GameSc;
import com.mygdx.game.Screens.Menu;

public class Main extends Game {
	public static SpriteBatch batch;
	public static Texture circle, stick, actor, fon, btnstart, btnstartup;
	public static TextureAtlas atlas;
	public static int WIDTH, HEIGHT;
	public static GameSc gameSc;
	public static Menu menu;


	
	@Override
	public void create () {


		batch = new SpriteBatch();
		menu = new Menu(this);
		gameSc = new GameSc(this);
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("Circle.png");
		stick = new Texture("Stick.png");
		actor = new Texture("fireball.png");
		fon = new Texture("Fon.jpg");
		btnstart = new Texture("cover_button_start_down.png");
		atlas = new TextureAtlas("cover_button_start_down.png");
		btnstartup = new Texture("cover_button_start_up.png");
		setScreen(menu);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
