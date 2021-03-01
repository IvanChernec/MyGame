package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Point2D;

public class Player extends Actor1092 {
    private int score;
    private float health;

    public Player(Texture img, Point2D position, float speed, float r, float health) {
        super(img, position, speed, r);
        this.health = health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.ORANGE);
        batch.draw(img, position.getX() - r, position.getY() - r, r*2, r*2);
        batch.setColor(Color.WHITE);

    }

    @Override
    public void update() {
        if (position.getX() + r > Main.WIDTH){
            position.setX(Main.WIDTH - r);
        }
        if (position.getX() - r < 0){
            position.setX(r);
        }
        if(position.getY() + r > Main.HEIGHT){
            position.setY(Main.HEIGHT - r);
        }
        if (position.getY() - r < 0){
            position.setY(r);
        }
        position.add(direction.getX()*speed, direction.getY()*speed);
        bounds.position.setP(position);


    }
}
