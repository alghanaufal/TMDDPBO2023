/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import model.Obstacle;
import model.Player;
import model.Table;
/**
 *
 * @author naufal
 */
public class PointHandler {
    int Rpoint;         // score sekarang
    int score = 0;      // var score
    int standing = 0;   // var standing
    
    public PointHandler() {
    }
    
    public void render(Graphics g){
        // menampilkan point di layar
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
        g.drawString("Score: " + score, 1200/2, 30);
        g.drawString("Standing: " + standing, 1180/2, 70);
    }
    
    public void countPoint(Player player, ArrayList<Obstacle> obstacles, boolean isFall){
        if(isFall){
            // score saat jatuh
            Rpoint = 0;
        }else{
            // bila tidak
            Rpoint = 0;
            for(Obstacle ob :obstacles){
                if(ob.getX()+ ob.getWidth() >= ob.getX() && player.getY() + player.getHeight() == ob.getY()){
                    if(!ob.isScoreCount()){
                        Rpoint = ob.getPoint(); // ambil score
                        score += Rpoint;        // jumlahkan dengan score saat ini
                        standing++;             // tambahkan jumlah standing
                        ob.setScoreCount(true); 
                    }
                }
            }
        }
    }
    
    public void uploadPoint(String username) throws Exception{
        // upload point ke database
        Table tp = new Table();
        tp.insertUpdateScore(username, score, standing);
    }
    // getter score dan standing
    public int getScore() {
        return score;
    }

    public int getStanding() {
        return score;
    }
}
