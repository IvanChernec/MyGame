package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;

import static com.mygdx.game.Resources.Res.enemies;


public class Wave {

    private int delay, waveNumber, minEnemy;
    private long startTimer;
    public static FileHandle waveN;
    public static Integer n = 1;

    public Wave(int delay, int waveNumber, int minEnemy) {
        this.delay = delay;
        this.waveNumber = waveNumber;
        this.minEnemy = minEnemy;
        waveN = Gdx.files.local("waveN.txt");
        if (!Gdx.files.local("waveN.txt").exists()){Main.WRITE(waveN, n);}
    }

    public void update(){
        if (enemies.size() == 0 && startTimer == 0){startTimer = System.currentTimeMillis();}
        int seconds = 0;
        if (startTimer > 0){seconds = (int) (System.currentTimeMillis() - startTimer)/1000;}
        if (seconds >= delay){setWave(); startTimer = 0; waveNumber++; seconds = 0;}
    }

    public void setWave(){
        int enemies = minEnemy + waveNumber*2;
        int maxRank = 1;

        n = Main.READ_INT(waveN);

        if (waveNumber > 5){ maxRank = 2; }
        if (waveNumber > 10){ maxRank = 3; }

        for (int i = 0; i < enemies; i++) {
            if (n == 1) {
                Res.enemies.add(new Enemy(Main.animal.get((int) (Math.random() * 3)),
                        new Point2D(Main.WIDTH / 2,
                                Main.HEIGHT / 4),
                        (int) Math.random() * maxRank));
            }else {
                Res.enemies.add(new Enemy(Main.japan.get((int) (Math.random() * 4)),
                        new Point2D(Main.WIDTH / 2,
                                Main.HEIGHT / 4),
                        (int) Math.random() * maxRank));
            }}
    }
}
