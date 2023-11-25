/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.Display;
/**
 *
 * @author naufal
 */
public class Game extends JPanel implements Runnable
{
    // properties
    private Thread gameThread;
    private String username;
    
    // menentukan perjalanan game
    private boolean running = false;
    private boolean started = false;
    private boolean gameOver = false;
    
    // variables memanggil class handler
    private final ObstacleHandler obs_handler;
    private final PlayerHandler player_handler;
    private final PointHandler point_handler;
    
    private Display window;
    private Image backgroundImage;
    
    public Game(String username, Display window){
        this.window = window;
        this.username = username;
        // instansiasi handler player
        this.player_handler = new PlayerHandler(this);
        // instansiasi handler Obstacle
        this.obs_handler = new ObstacleHandler();
        // instansiasi handler score dan standing
        this.point_handler = new PointHandler();
        // instansiasi background
        try {
            backgroundImage = ImageIO.read(new File("resources/image/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void StartGame(){
        // Menjalankan Thread Game
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public synchronized void stopGame(){
        // Menghentikan Game
        try {
            window.stopMusic();
            running = false;
            // upload Score
            point_handler.uploadPoint(username);
            JOptionPane.showMessageDialog(null, "Username: " + this.username + "\n" + "score: " + this.point_handler.score + "\n" + "standing: " + this.point_handler.standing, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
            // menghancurkan window game
            window.destroy();
            // menunggu dulu sampai thread dari game ini mati
            gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g){
        // fungsi untuk menampilkan graphics ke Game
        super.paint(g); // hapus component yang telah di gambar sebelumnya
        
        // Draw background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        if(!running && !started){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
            g.drawString("Press Any Key To Play Game", 1280/2 - 120, 720/2 - 20);
        }
        // menampilkan objek player
        player_handler.renderPlayer(g);
        // menampilkan objek obstacle
        obs_handler.renderObstacle(g);
        // menapilkan score dan standing
        point_handler.render(g);
    }
    
    public void updateGame(){
        // mengupdate objek-objek pada game
        obs_handler.spawnObstacle();    // objek obstacle
        player_handler.updatePlayer(obs_handler.getObstacles());    // objek player
        obs_handler.updateObstacle();  
    }
    
    
    @Override
    public void run() {
        // looping selama game berjalan
        while(running){
            try {
                // update game dan tampilkan lagi ke layar
                updateGame();
                repaint();
                // sleep agar game looping tidak begitu cepat
                Thread.sleep(1000L/60L); 
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // getter player
    public PlayerHandler getPlayerHandler(){
        return this.player_handler;
    }
    // getter score
    public PointHandler getPointHandler() {
        return point_handler;
    }
    
    // berjalannya game
    public boolean isStarted(){
        return this.started;
    }
    
    public void setStarted(boolean started){
        this.started = started;
    }
    
    public boolean isRunning(){
        return this.running;
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean isGameOver() {
        return gameOver;
    } 
}
