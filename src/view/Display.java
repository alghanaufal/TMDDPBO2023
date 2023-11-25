/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JFrame;

import viewmodel.Game;
import viewmodel.Controller;
import viewmodel.Sound;

/**
 *
 * @author naufal
 */
public class Display extends JFrame{
    Game game;
    Home menu;
    Sound music;
    public Display(String username, Home menu){
        super("Jan Jatoh");
        this.menu = menu;
        // membuat panel yang menampilkan game
        game = new Game(username, this);
        addKeyListener(new Controller(game));
        add(game);
        
        // mengatur ukuran dan lokasi frame game saat berjalan
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // menjalankan musik GAME saat game dimulai
        music = new Sound(Sound.GAME);
        music.play();
    }
    
    public void destroy(){
        // menghapus frame game dan menampilkan kembali home
        menu.setVisible(true);
        menu.updateTable();
        menu.playMusic();
        this.dispose();
    }
    
    public void stopMusic(){
        // menghentikan musik pada frame game
        music.stop();
    }
}
