package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.GameSc;

public class Main extends Game {
	public static SpriteBatch batch;
	public static Texture circle, stick, actor;
	public static int WIDTH, HEIGHT;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle = new Texture("Circle.png");
		stick = new Texture("Stick.png");
		actor = new Texture("fireball.png");
		setScreen(new GameSc(this));
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
