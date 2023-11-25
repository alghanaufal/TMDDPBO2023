/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author naufal
 */

public class Obstacle extends GameObject{
    // boolean point dari obstacle
    private boolean scoreCount = false;
    // point yang didapat di obstacle ini
    private final int point;
    // random warna
    Random random = new Random();
    // warna obstacle
    Color color;
    
    public Obstacle(float x, float y, int width, int height, int point) {
        super(x, y, width, height);
        this.point = point;
        // set warna obstacle
        setColorObstacle();
    }
    
    private void setColorObstacle(){
        // membuat warna di obstacle, warnanya di random
        int r = random.nextInt(0, 255);
        int g = random.nextInt(0, 155);
        int b = random.nextInt(0, 255);
        color = new Color(r, g, b);
    }

    public boolean isScoreCount() {
        // mengembalikan boolean apakah obstacle sudah dihitung pointnya
        return scoreCount;
    }
    // set score count
    public void setScoreCount(boolean scoreCount) {
        this.scoreCount = scoreCount;
    }

    public int getPoint() {
        // mengembalikan point obstacle
        return point;
    }
    
    public Color getColor(){
        // mengembalikan warna obstacle
        return this.color;
    }
}
