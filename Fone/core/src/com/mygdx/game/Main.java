package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.Battle;
import com.mygdx.game.Screens.GameSc;
import com.mygdx.game.Screens.Menu;

public class Main extends Game {
	public static SpriteBatch batch;
	public static Texture circle, stick, fire, fon, btnstart, btnstartup, snowBullet, snow, fonGame;
	public static int WIDTH, HEIGHT;
	public static GameSc gameSc;
	public static Menu menu;
	public static Battle battle;


	
	@Override
	public void create () {


		batch = new SpriteBatch();
		menu = new Menu(this);
		gameSc = new GameSc(this);
		battle = new Battle(this);
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		fonGame = new Texture("fonGame.jpg");
		circle = new Texture("Circle.png");
		stick = new Texture("Stick.png");
		fire = new Texture("fireball.png");
		fon = new Texture("fonMenu.jpg");
		btnstart = new Texture("cover_button_start_down.png");
		btnstartup = new Texture("cover_button_start_up.png");
		snowBullet = new Texture("snowflake_christmas.png");
		snow = new Texture("snowflake.png");
		setScreen(menu);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
