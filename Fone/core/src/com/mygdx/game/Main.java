package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.Battle;
import com.mygdx.game.Screens.GameSc;
import com.mygdx.game.Screens.Menu;
import com.mygdx.game.Screens.Shop;

import java.util.ArrayList;
import java.util.List;

public class Main extends Game {
	public static SpriteBatch batch;
	public static Texture circle, stick, fire, fon, btnstart, btnstartup,
			snowBullet, snow, fonGame, player, shop, mob1, NPC,
			playerM, map, fonAttack, fonJapan, fonJapanBattle, shopJapan;
	public static List<Texture> animal, japan;
	public static int WIDTH, HEIGHT;
	public static GameSc gameSc;
	public static Menu menu;
	public static Battle battle;
	public static Shop shopSc;


	
	@Override
	public void create () {


		batch = new SpriteBatch();
		menu = new Menu(this);
		gameSc = new GameSc(this);
		battle = new Battle(this);
		shopSc = new Shop(this);

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		animal = new ArrayList<>();
		animal.add(new Texture("fox.png"));
		animal.add(new Texture("bear.png"));
		animal.add(new Texture("wolf.png"));

		japan = new ArrayList<>();
		japan.add(new Texture("enemyJapan1.png"));
		japan.add(new Texture("enemyJapan2.png"));
		japan.add(new Texture("enemyJapan3.png"));
		japan.add(new Texture("enemyJapan4.png"));

		map = new Texture("map.png");
		fonAttack = new Texture("fonAttack.jpg");
		NPC = new Texture("sensei.png");
		mob1 = new Texture("Monster.png");
		shop = new Texture("vendor.png");
		player = new Texture("playerJapan.png");
		playerM = new Texture("playerJapanMage.png");
		fonGame = new Texture("fonGame.jpg");
		circle = new Texture("Circle.png");
		stick = new Texture("Stick.png");
		fire = new Texture("fireball.png");
		fon = new Texture("fonMenu.jpg");
		btnstart = new Texture("cover_button_start_down.png");
		btnstartup = new Texture("cover_button_start_up.png");
		snowBullet = new Texture("snowflake_christmas.png");
		snow = new Texture("snowflake.png");
		fonJapan = new Texture("fonJapan.jpg");
		fonJapanBattle = new Texture("fonJapanBattle.jpg");
		shopJapan = new Texture("shopJapan.png");

		setScreen(menu);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
