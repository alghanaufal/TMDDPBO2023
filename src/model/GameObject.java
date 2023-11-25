/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Rectangle;

/**
 *
 * @author naufal
 */
public abstract class GameObject{
    /**
     * Attribute declaration.
     */
    // koordinat object
    protected float x;
    protected float y;
    // luas objek
    protected int width;
    protected int height;
    
    // hitbox berbentuk persegi sebagai pendeteksi collision
    protected Rectangle hitbox;
    
    public GameObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // buat hitbox dari koordinat dan luas objectnya
        hitbox = new Rectangle((int)x, (int)y, width, height);
    }
    
    public void updateHitbox(){
        // collision x dan y
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    // setter
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // getter
    public Rectangle getHitbox() {
        return hitbox;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
