package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Tools.Circle;
import com.mygdx.game.GraphicsObl.GraphicsObj;
import com.mygdx.game.Tools.Point2D;

public abstract class Actor1092 extends GraphicsObj {
    public Point2D position;
    public float speed, r;
    public Circle bounds;

    public Point2D direction;

    public Actor1092(Texture img, Point2D position, float speed, float r) {
        super(img);
        this.position = new Point2D(position);
        this.speed = speed;
        this.r = r;
        bounds = new Circle(r, position);
        direction = new Point2D(0,0);
    }
    public Actor1092(Texture img, Point2D position) {
        super(img);
        this.position = new Point2D(position);
        direction = new Point2D(0,0);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setDirection(Point2D dir){
        direction = dir;
    }
}
