package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Point2D;

public class Player extends Actor1092 {

    private int health;
    private int dmg = 1, money = 100, skill = 10, mana = 100;
    public static int lvl, lvlUp, xp;

    public int getSkill() {
        return skill;
    }

    public int getMana() {
        return mana;
    }
    public void manaSkill(){
        mana -= 10;
    }

    public void moneyMob(int a){
        money += a;
    }
    public void moneyShop(int a){
        money -= a;
    }

    public int getMoney() {
        return money;
    }

    public void upgradeDmg(int a){
        dmg += a;
    }

    public int getDmg() {
        return dmg;
    }

    public Player(Texture img, Point2D position, float speed, float r, int health) {
        super(img, position, speed, r);
        this.health = health;
    }

    public void setImg(Texture img){
        this.img = img;
    }

    public void shopHeal(){
        health = 100;
    }

    public void hit(){
        health--;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX() - r, position.getY() - r, r*2, r*2);
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
