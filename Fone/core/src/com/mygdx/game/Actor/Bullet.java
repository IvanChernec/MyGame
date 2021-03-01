package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.Screens.GameSc;
import com.mygdx.game.Tools.Point2D;

public class Bullet extends Actor1092 {

    public boolean isOut;

    public Bullet(Texture img, Point2D position, float speed, float r, Point2D direction) {
        super(img, position, speed, r);
        this.direction = new Point2D(direction);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX()-r, position.getY() - r, r*2, r*2);
    }

    @Override
    public void update() {
        isOut = (position.getX() + r > Main.WIDTH) ||
                (position.getX() - r < 0) ||
                (position.getY() + r > Main.HEIGHT) ||
                (position.getY() - r < 0);


        position.add(direction.getX() *  speed, direction.getY() * speed);
        bounds.position.setP(position);
    }
}
