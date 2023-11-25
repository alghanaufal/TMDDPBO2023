/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.Obstacle;

/**
 *
 * @author naufal
 */
public class ObstacleHandler {
    private final Random random = new Random();
    
    private int totalobs;           // jumlah obstacles
    private float randomY;          // value koordinat y
    private int randomWidth;        // random panjang obstacle
    private int setScore;           // set score
    private float prevObstacleWidth;// lebar obstacle sebelumnya
    // tentukan gap
    private float gapMultipler; 
    private float gap;
    // buat array untuk obstacle
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    
    public void renderObstacle(Graphics g){
        // menampilkan semua obstacle
        for (Obstacle ob : obstacles) {
            render(g, ob);
        }
    }
    
    private void render(Graphics g, Obstacle o) {
        // menampilkan obstacle
        g.setColor(o.getColor());
        g.fillRect((int)o.getX(), (int)o.getY(), o.getWidth(), o.getHeight());
        
        String Textscore = Integer.toString(o.getPoint());

        // Menampilkan teks di sebelah kanan obstacle
        int x1 = (int) (o.getX() + o.getWidth());
        int y1 = (int) (o.getY() + o.getHeight());
        g.setFont(new Font("TimesRoman", Font.BOLD, 50)); // Setel font sebelum menggambar teks
        g.drawString(Textscore, x1, y1);
    }
    
    public void spawnObstacle(){
        randomWidth = 0;
        setScore = 0;
        // tambahkan obstacle
        if(totalobs < 9){
            randomY = 0;
            
            if(totalobs > 1){
                // tantukan gap
                prevObstacleWidth = obstacles.get(obstacles.size() - 1).getHeight();
                gapMultipler = getRandomObsUsingNextInt(2, 4);
                gap = prevObstacleWidth * gapMultipler;
                // tentukan posisi y
                randomY = obstacles.get(obstacles.size() - 1).getY() + gap;
            }
            // tentukan ukuran obstacle
            randomWidth = (int)getRandomObsUsingNextInt(100, 800);
            // tentukan score sesuai ukuran obstacle
            if(randomWidth >= 100 && randomWidth <= 150){
                setScore = 50;
            }else if(randomWidth > 150 && randomWidth <= 350){
                setScore = 40;
            }else if(randomWidth > 350 && randomWidth <= 400){
                setScore = 30;
            }else if(randomWidth > 400 && randomWidth <= 600){
                setScore = 20;
            }else{
                setScore = 10;
            }
            // tambahkan obstacle dengan value scorenya
            Obstacle obstacle = new Obstacle(0, randomY, randomWidth, 50, setScore);
            obstacles.add(obstacle);
            totalobs++;
        }
    }
    
    public void updateObstacle(){
        // update data obstacle
        Iterator<Obstacle> i = obstacles.iterator();
        while (i.hasNext()){
            Obstacle ob = i.next();
            if(ob.getY() + ob.getHeight() < 0){
                // hapus obstacle yang sudah melewati layar
                i.remove();
                totalobs--;
            }else {
                // update posisi obstacle
                updatePos(ob);
                ob.updateHitbox();
            }
        }
    }
    
    private void updatePos(Obstacle o){
        // mengupdate posisi obstacle dan akan bergerak ke atas secepat
        if(o.getPoint() > 0 || o.getPoint() == 0 || (o.getPoint() < 0 && o.getY() < 0))
            o.setY(o.getY() - 2.0f);
    }
    
    private float getRandomObsUsingNextInt(int min, int max) {
        // random ukuran
        return random.nextInt(max - min) + min;
    }

    // array obstacle
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
