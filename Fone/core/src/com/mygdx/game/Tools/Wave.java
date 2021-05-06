package com.mygdx.game.Tools;

import com.mygdx.game.Actor.Enemy;
import com.mygdx.game.Main;
import com.mygdx.game.Resources.Res;

import static com.mygdx.game.Resources.Res.enemies;


public class Wave {
    private int delay, waveNumber, minEnemy;
    private long startTimer;
    private String str = "Wave - ";

    public Wave(int delay, int waveNumber, int minEnemy) {
        this.delay = delay;
        this.waveNumber = waveNumber;
        this.minEnemy = minEnemy;
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

        if (waveNumber > 5){ maxRank = 2; }
        if (waveNumber > 10){ maxRank = 3; }

        for (int i = 0; i < enemies; i++) { Res.enemies.add(new Enemy(Main.animal.get((int) (Math.random() * 3)),
                new Point2D(Main.WIDTH/2,
                        Main.HEIGHT/4) ,
                (int)Math.random()*maxRank)); }
    }
}
