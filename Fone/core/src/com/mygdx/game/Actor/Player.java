package com.mygdx.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.Tools.Point2D;

import static com.mygdx.game.Main.*;

public class Player extends Actor1092 {



    private int health = Main.READ_INT(healthF),
            dmg = Main.READ_INT(dmgF),
            money = Main.READ_INT(moneyF),
            mana = Main.READ_INT(manaF);
    public static int lvl = Main.READ_INT(lvlF),
            lvlUp = Main.READ_INT(lvlUpF),
            xp = Main.READ_INT(xpF);



    public Player(Texture img, Point2D position, float speed, float r) {
        super(img, position, speed, r);
    }

    public int getMana() {
        return mana;
    }
    public void shopMana(){Main.WRITE(manaF, 100);
        mana = Main.READ_INT(manaF);}

    public void manaSkill(int n){
        Main.WRITE(manaF, mana - n);
        mana = Main.READ_INT(manaF);
    }

    public void moneyMob(int a){
        Main.WRITE(moneyF, money + a);
        money = Main.READ_INT(moneyF);
    }
    public void moneyShop(int a){
        Main.WRITE(moneyF, money - a);
        money = Main.READ_INT(moneyF);
    }

    public int getMoney() {
        return money;
    }

    public void upgradeDmg(int a){
        Main.WRITE(dmgF, dmg + a);
        dmg = Main.READ_INT(dmgF);
    }

    public int getDmg() {
        return dmg;
    }

    public void setImg(Texture img){
        this.img = img;
    }

    public void shopHeal(){
        Main.WRITE(healthF, 100);
        health = Main.READ_INT(healthF);
    }

    public void hit(int a){
        Main.WRITE(healthF, health - a);
        health = Main.READ_INT(healthF);
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
