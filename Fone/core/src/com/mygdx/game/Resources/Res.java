package com.mygdx.game.Resources;

import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Actor.Player;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Point2D;
import com.mygdx.game.Tools.Wave;

import java.util.ArrayList;

public class Res {
    public static Player player, shop;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    static int n = 0;

    public static void loadActor(){
        if (n == 0) {
            enemies = new ArrayList<>();
            player = new Player(Main.player, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 40, 100);
            shop = new Player(Main.shop, new Point2D(900, 2000), 0, Main.HEIGHT / 10, 1);
            wave = new Wave(5, 1, 5);
            n++;
        }
    }
}