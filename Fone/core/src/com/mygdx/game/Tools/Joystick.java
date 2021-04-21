package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Joystick extends Actor {
    Texture circleImg, stickImg;
    Circle circleBounds, stickBounds;
    float rCircle, rStick;
    public Point2D direction;
    private int pointer = -1;


    public Joystick(Texture cImg, Texture sImg, Point2D p, float size ) {

        circleImg = cImg;
        stickImg = sImg;
        rCircle = size/2;
        rStick = rCircle/2;
        circleBounds = new Circle(rCircle, p);
        stickBounds = new Circle(rStick, p);
        direction = new Point2D(0,0);
    }

    public void draw(SpriteBatch batch){
        batch.draw(circleImg,
                circleBounds.position.getX() - rCircle,
                circleBounds.position.getY()-rCircle,
                rCircle * 2,
                rCircle * 2);
        batch.draw(stickImg,
                stickBounds.position.getX() - rStick,
                stickBounds.position.getY() - rStick,
                rStick * 2,
                rStick * 2);
    }
    public void update(float x, float y, boolean isDownTouch, int pointer){
        Point2D touch = new Point2D(x, y);
        if (circleBounds.isContains(touch) && isDownTouch && this.pointer == -1){
            this.pointer = pointer;
        }
        if (circleBounds.overLaps(stickBounds) && isDownTouch && pointer == this.pointer){
            atControl(new Point2D(x, y));
        }
        if ((!isDownTouch && pointer == this.pointer) || (isDownTouch && pointer == this.pointer && !circleBounds.overLaps(stickBounds))){
            returnStick();
        }

    }

    public void atControl(Point2D p){
        stickBounds.position.setP(p);
        float dx = circleBounds.position.getX() - stickBounds.position.getX();
        float dy = circleBounds.position.getY() - stickBounds.position.getY();
        float dist = (float) Math.sqrt(dx*dx + dy*dy);
        direction.setP(-(dx / dist), -(dy / dist));
    }

    public void returnStick(){
        stickBounds.position.setP(circleBounds.position);
        direction.setP(0,0);
        pointer = -1;
    }

    public Point2D getDirection() {
        return direction;
    }
}

