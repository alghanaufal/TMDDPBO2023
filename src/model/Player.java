/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *
 * @author naufal
 */
public class Player extends GameObject
{   
    // buatkan skin player
    public static Image skin;
    
    // var pergerakan player
    private boolean left;
    private boolean right;
    private boolean up;
    
    // player sedang terjatuh
    private boolean inFall = true;
    
    // buat player
    public Player(int x, int y)
    {
        // buat posisi dan ukuran player
        super(x, y,50, 50);
        // tambahkan skin
        skin = Toolkit.getDefaultToolkit().getImage("resources/image/ball.png");
    }
    
    /* 
    bound adalah hitbox untuk mendeteksi collision yang memiliki 4 sisi
    */
    // hitbox bawah
    public Rectangle getBoundBottom(){
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y+(height/2)), width/2, height/2);
    }
    // hitbox atas
    public Rectangle getBoundTop(){
        return new Rectangle((int) (x+(width/2)-(width/4)), (int) (y), width/2, height/2);
    }
    // hitbox kanan
    public Rectangle getBoundRight(){
        return new Rectangle((int) x+width-5, (int)y + 5, 5, height-10);
    }
    // hitbox kiri
    public Rectangle getBoundLeft(){
        return new Rectangle((int) x, (int)y + 5, 5, height-10);
    }
    
    // getter dan setter pergerakan
    public boolean isMoveLeft() {
        return left;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.left = moveLeft;
    }


    public boolean isMoveRight() {
        return right;
    }

    public void setMoveRight(boolean moveRight) {
        this.right = moveRight;
    }

    public boolean isMoveUp() {
        return up;
    }

    public void setMoveUp(boolean moveUp) {
        this.up = moveUp;
    }

    // setter getter player jatuh
    public boolean isFall(){
        return inFall;
    }
    
    public void setInFall(boolean inFall){
        this.inFall = inFall;
    }
}
