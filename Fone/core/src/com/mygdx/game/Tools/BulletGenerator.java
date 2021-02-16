package com.mygdx.game.Tools;

import com.badlogic.gdx.Game;
import com.mygdx.game.Actor.Bullet;
import com.mygdx.game.Main;
import com.mygdx.game.Screens.GameSc;

public class BulletGenerator {
    boolean isFire;

    public void update(Joystick joy){
        isFire = joy.getDirection().getX() != 0 && joy.getDirection().getY() != 0;

        if (isFire){ GameSc.bullets.add(new Bullet(Main.actor, GameSc.player.position, 25,GameSc.player.r/1.5f, joy.getDirection())); }

    }
}
