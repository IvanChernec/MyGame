package com.mygdx.game.Tools;

import com.mygdx.game.Tools.Point2D;

public class Circle {
    float r;
    public Point2D position;


    public Circle(float r, Point2D position) {
        this.r = r;
        this.position = new Point2D(position);
    }
    public boolean isContains(Point2D point){
        float dx = position.getX() - point.getX();
        float dy = position.getY() - point.getY();
        return dx*dx + dy*dy <= r * r;
    }
    public boolean overLaps(Circle c){
        float dx = position.getX() - c.position.getX();
        float dy = position.getY() - c.position.getY();
        float dist = dx*dx + dy*dy;
        float sumR = c.r + r;
        return dist < sumR*sumR;
    }
}
