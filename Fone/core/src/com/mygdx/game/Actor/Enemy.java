package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Circle;
import com.mygdx.game.Tools.Point2D;

public class Enemy extends Actor1092{
    private int health, score, rank;

    public Enemy(Texture img, Point2D position,int rank) {
        super(img, position);

        switch (rank){
            case 1: r = Main.WIDTH/250; speed = 25; score = health = 10; break;
            case 2: r = Main.WIDTH/150; speed = 15; score = health = 20; break;
            case 3: r = Main.WIDTH/100; speed = 10; score = health = 30; break;
        }
        bounds = new Circle(r, position);
        direction.setX((float) Math.sin(Math.toRadians(Math.random() * 360)));
        direction.setY((float) Math.cos(Math.toRadians(Math.random() * 360)));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX() - r, position.getY() - r, r*2, r*2);

    }

    @Override
    public void update() {
        if (position.getX() + r > Main.WIDTH){
            direction.setX(-direction.getX());
        }
        if (position.getX() - r < 0){
            direction.setX(-direction.getX());
        }
        if(position.getY() + r > Main.HEIGHT){
            direction.setY(-direction.getY());
        }
        if (position.getY() - r < 0){
            direction.setY(-direction.getY());
        }
        position.add(direction.getX()*speed, direction.getY()*speed);
        bounds.position.setP(position);
    }

    public void hit(){
        health--;
    }

    public int getHealth() {
        return health;
    }
}
