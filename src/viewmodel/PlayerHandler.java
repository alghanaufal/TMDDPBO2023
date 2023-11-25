/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import model.Obstacle;
import model.Player;

/**
 *
 * @author naufal
 */
public class PlayerHandler {
    private Player player;
    private Game game;
    // var pergerakan player
    private float xSpeed = 0;
    private float ySpeed = 0;
    private float Jump = 1;
    
    public PlayerHandler(Game game) {
        // membuat objek player nya dan lokasi spawn nya
        this.player = new Player(850, 0);
        this.game = game;
    }
    
    public void updatePlayer(ArrayList<Obstacle> AOb){
        // mengupdate player
        updatePos(AOb);
        // mengecek apakah player masih ada di layar
        checkPlayerPosStillInGame();
        player.updateHitbox();
    }
    
    public void renderPlayer(Graphics g){
        // manmpilkan player ke layar
        g.drawImage(Player.skin, (int)player.getX(), (int)player.getY(), player.getWidth(), player.getHeight(), null);
    }
    
    private void updatePos(ArrayList<Obstacle> AOb)
    {   
        // atur movement player x
        if(player.isMoveLeft()){
            xSpeed = -7.0f;
        } else if(player.isMoveRight()){
            xSpeed = 7.0f;
        }
        // atur movement player jump
        if(player.isMoveUp() && Jump >= 0){
            ySpeed = -12.f;
            Jump--;
        }
        // check collision
        if(!player.isFall() && !isOnObstacle(AOb)){
            player.setInFall(true);
        }
        // buat player jatuh
        if (player.isFall()){
            ySpeed += 0.5f;
        }else{
            ySpeed = 0;
        }
        
        // cek collision player untuk setiap obstacle
        for(Obstacle ob : AOb){
            // bottom collision
            if(player.getBoundBottom().intersects(ob.getHitbox())){
                if(ySpeed >= 0){
//                    player.setInFall(false);
                    Jump = 1;
                    ySpeed = 0;
                    player.setY(ob.getHitbox().y - player.getHeight());
                }
                if(ob.getPoint()< 0){
                    // update score saat menyentuh collision
                    this.game.getPointHandler().countPoint(player, AOb, true);
                }else{
                    this.game.getPointHandler().countPoint(player, AOb, false);
                }
            }
            else if(player.getBoundTop().intersects(ob.getHitbox())){
                // top collision
                player.setY(ob.getHitbox().y + ob.getHitbox().height);
                ySpeed = 0;
            }
            else if(player.getBoundRight().intersects(ob.getHitbox())){
                // right collision
                xSpeed = 2.0f;
                // update posisi player ke tepat di sebelah kiri objek yang collided
                player.setX(ob.getHitbox().x - player.getWidth() - 1);
            }
            else if(player.getBoundLeft().intersects(ob.getHitbox())){
                // left collision
                xSpeed = 2.0f;
                // update posisi player ke tepat di sebelah kanan objek yang collided
                player.setX(ob.getHitbox().x + ob.getHitbox().width + 1);
            }
            if(player.getBoundLeft().x < 0){
                // mencegah player keluar dari layar sebelah kiri
                player.setX(0);
            }
            else if(player.getBoundRight().x + player.getBoundRight().width > 1280){
                // mencegah player keluar dari layar sebelah kanan
                player.setX(1280 - player.getWidth());
            }
        }
        
        // update posisi
        player.setX(player.getX() + xSpeed);
        player.setY(player.getY() + ySpeed);
        // reset speed
        xSpeed = 0;
    }
    
    private boolean isOnObstacle(ArrayList<Obstacle> AOb){
        // mengecek apakah player berada di obstacle
        Rectangle rect;
        for(Obstacle ob : AOb){
            rect = player.getBoundBottom();
            rect.y += 1;
            if(rect.intersects(ob.getHitbox())){
                return true;
            }
        }
        return false;
    }
    
    private void checkPlayerPosStillInGame(){
        // mengecek apakah player masih berada di layar
        if(player.getY() > 720 || player.getY() < -50){
            this.game.stopGame();
        }
    }
    
    public void setPlayerMoveUp(boolean moveUp){
        player.setMoveUp(moveUp);
    }
    public void setPlayerMoveLeft(boolean moveLeft){
        player.setMoveLeft(moveLeft);
    }
    public void setPlayerMoveRight(boolean moveRight){
        player.setMoveRight(moveRight);
    }
}
