package com.mygdx.game.Resources;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Actor.Player;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Point2D;
import com.mygdx.game.Tools.Wave;

import java.util.ArrayList;

public class Res {
    public static Player player, shop, map;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Texture fon1, fon2;
    public static int n = 0, a = 0;
    public static boolean
            jpn = Main.READ_BOOLEAN(Main.jpn),
            shockSk = Main.READ_BOOLEAN(Main.sh),
            snowSk = Main.READ_BOOLEAN(Main.sn),
            skillKnight = Main.READ_BOOLEAN(Main.sn);


    public static void loadActor(){
       if (jpn && a != 0){
            Res.fon1 = Main.fonJapan;
            Res.fon2 = Main.fonJapanBattle;
            Res.shop.img = Main.shopJapan;
            Enemy.hlth = 30;
        }else if (!jpn && a != 0){
            Res.fon1 = Main.fonGame;
            Res.fon2 = Main.fonAttack;
            Res.shop.img = Main.shop;
            Enemy.hlth = 5;
        }
        if (n == 0) {
            enemies = new ArrayList<>();
           if (Main.READ_BOOLEAN(Main.skill)){
                player = new Player(Main.playerM, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 40);
            }else {
                player = new Player(Main.player, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 40);
            }
            shop = new Player(Main.shop, new Point2D(900, 2000), 0, Main.HEIGHT / 10);
            map = new Player(Main.map, new Point2D(100, 2000), 0, Main.HEIGHT/ 10);
            wave = new Wave(5, 1, 5);
            n++;
        }
    }
}
