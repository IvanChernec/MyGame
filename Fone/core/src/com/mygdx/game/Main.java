package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
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
	public static Texture circle, stick, fire, fon, snowBullet, snow,
			fonGame, player, shop, mob1, NPC, playerM, map, fonAttack,
			fonJapan, fonJapanBattle, shopJapan, fonShop, attackTx,
			exitTx, healTx, shock, forestTx, japanTx, skillJKTx, mana, UI, healthPlayer;
	public static List<Texture> animal, japan, boss;
	public static int WIDTH, HEIGHT, a;
	public static GameSc gameSc;
	public static Menu menu;
	public static Battle battle;
	public static Shop shopSc;
	public static Music forestMs, japanMs, battleMs;
	public static FileHandle healthF, dmgF, moneyF, manaF, lvlF,
			lvlUpF, xpF, sh, sn, skK, skill, aF,
			d, jpn, nagr, hlth, bossHP, healP, manaP, dmgE, dmgEBoss, maxHealth;


	
	@Override
	public void create () {

		batch = new SpriteBatch();
		menu = new Menu(this);
		gameSc = new GameSc(this);
		battle = new Battle(this);
		shopSc = new Shop(this);

		forestMs = Gdx.audio.newMusic(Gdx.files.internal("Windless Slopes.mp3"));
		japanMs = Gdx.audio.newMusic(Gdx.files.internal("Celestial.mp3"));
		battleMs = Gdx.audio.newMusic(Gdx.files.internal("BATTLE.mp3"));

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();


		textures();
		file();
		aF = Gdx.files.local("aF.txt");
		if (!Gdx.files.local("aF.txt").exists()){WRITE(aF, 0);}
		a = READ_INT(aF);
		WRITE(aF, 1);
		if (a == 0){
			setScreen(menu);
		}else {
			setScreen(gameSc);
		}


	}

	public static void file(){
		healthF = Gdx.files.local("healthF.txt");
		dmgF = Gdx.files.local("dmgF.txt");
		moneyF = Gdx.files.local("moneyF.txt");
		manaF = Gdx.files.local("manaF.txt");
		lvlF = Gdx.files.local("lvlF.txt");
		lvlUpF = Gdx.files.local("lvlUpF.txt");
		xpF = Gdx.files.local("xpF.txt");
		sh = Gdx.files.local("sh.txt");
		sn = Gdx.files.local("sn.txt");
		skK = Gdx.files.local("skK.txt");
		skill = Gdx.files.local("skill.txt");
		d = Gdx.files.local("d.txt");
		jpn = Gdx.files.local("jpn.txt");
		nagr = Gdx.files.local("nagr.txt");
		hlth = Gdx.files.local("hlth.txt");
		bossHP = Gdx.files.local("bossHP.txt");
		healP = Gdx.files.local("healP.txt");
		manaP = Gdx.files.local("manaP.txt");
		dmgE = Gdx.files.local("dmgE.txt");
		dmgEBoss = Gdx.files.local("dmgEBoss.txt");
		maxHealth = Gdx.files.local("maxHealth.txt");

		if (!Gdx.files.local("lvlF.txt").exists()){WRITE(lvlF, 1);}
		if (!Gdx.files.local("maxHealth.txt").exists()){WRITE(maxHealth, 100);}
		if (!Gdx.files.local("dmgEBoss.txt").exists()){WRITE(dmgEBoss, 10);}
		if (!Gdx.files.local("dmgE.txt").exists()){WRITE(dmgE, 1);}
		if (!Gdx.files.local("healP.txt").exists()){WRITE(healP, 0);}
		if (!Gdx.files.local("manaP.txt").exists()){WRITE(manaP, 0);}
		if (!Gdx.files.local("lvlF.txt").exists()){WRITE(lvlF, 1);}
		if (!Gdx.files.local("bossHP.txt").exists()){WRITE(bossHP, 200);}
		if (!Gdx.files.local("hlth.txt").exists()){WRITE(hlth, 5);}
		if (!Gdx.files.local("nagr.txt").exists()){WRITE(nagr, 10);}
		if (!Gdx.files.local("jpn.txt").exists()){WRITE(jpn, false);}
		if (!Gdx.files.local("d.txt").exists()){WRITE(d, 1);}
		if (!Gdx.files.local("skill.txt").exists()){WRITE(skill, false);}
		if (!Gdx.files.local("sh.txt").exists()){WRITE(sh, false);}
		if (!Gdx.files.local("sn.txt").exists()){WRITE(sn, false);}
		if (!Gdx.files.local("skK.txt").exists()){WRITE(skK, false);}
		if (!Gdx.files.local("lvlUpF.txt").exists()){WRITE(lvlUpF, 10);}
		if (!Gdx.files.local("xpF.txt").exists()){WRITE(xpF, 0);}
		if (!Gdx.files.local("healthF.txt").exists()){Main.WRITE(healthF, 100);}
		if (!Gdx.files.local("dmgF.txt").exists()){Main.WRITE(dmgF, 1);}
		if (!Gdx.files.local("moneyF.txt").exists()){Main.WRITE(moneyF, 100);}
		if (!Gdx.files.local("manaF.txt").exists()){Main.WRITE(manaF, 100);}
	}

	public static void textures(){
		animal = new ArrayList<>();
		animal.add(new Texture("fox.png"));
		animal.add(new Texture("bear.png"));
		animal.add(new Texture("wolf.png"));

		boss = new ArrayList<>();
		boss.add(new Texture("boss1.png"));
		boss.add(new Texture("boss2.png"));
		boss.add(new Texture("boss3.png"));

		japan = new ArrayList<>();
		japan.add(new Texture("enemyJapan1.png"));
		japan.add(new Texture("enemyJapan2.png"));
		japan.add(new Texture("enemyJapan3.png"));
		japan.add(new Texture("enemyJapan4.png"));

		healthPlayer = new Texture("playerHealth.png");
		UI = new Texture("UI.png");
		map = new Texture("map.png");
		fonAttack = new Texture("fonAttack.jpg");
		NPC = new Texture("sensei.png");
		shop = new Texture("vendor.png");
		player = new Texture("playerJapan.png");
		playerM = new Texture("playerJapanMage.png");
		fonGame = new Texture("fonGame.jpg");
		circle = new Texture("Circle.png");
		stick = new Texture("Stick.png");
		fire = new Texture("fireball.png");
		fon = new Texture("fonMenu.jpg");
		snowBullet = new Texture("snowflake_christmas.png");
		snow = new Texture("snowflake.png");
		fonJapan = new Texture("fonJapan.jpg");
		fonJapanBattle = new Texture("fonJapanBattle.jpg");
		shopJapan = new Texture("shopJapan.png");
		attackTx = new Texture("Sword.png");
		exitTx = new Texture("back.png");
		fonShop = new Texture("shopFon.jpg");
		healTx = new Texture("heal.png");
		shock = new Texture("shock.png");
		forestTx = new Texture("forest.png");
		japanTx = new Texture("japan.png");
		skillJKTx = new Texture("skillJapanK.png");
		mana = new Texture("mana.png");
	}

	public static void WRITE(FileHandle file, String str){
		file.writeString(str, false);
	}

	public static void WRITE(FileHandle file, int n){
		file.writeString(String.valueOf(n), false);
	}

	public static void WRITE(FileHandle file, boolean b){
		file.writeString(String.valueOf(b), false);
	}


	public static int READ_INT(FileHandle file){
		return Integer.parseInt(file.readString());
	}

	public static String READ(FileHandle file){
		return file.readString();
	}

	public static boolean READ_BOOLEAN(FileHandle file){
		return Boolean.parseBoolean(file.readString());
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
