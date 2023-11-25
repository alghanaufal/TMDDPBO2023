/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import viewmodel.Game;

/**
 *
 * @author naufal
 */
public class Controller implements KeyListener{
    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // menekan Tombol
        if(!game.isStarted()) {
            // memulai game jika sebelumnya game belum mulai
            game.setStarted(true);
            game.setRunning(true);
            game.StartGame();
        }else{
            switch (e.getKeyCode()) {
                // mengupdate player movement
                case KeyEvent.VK_W -> game.getPlayerHandler().setPlayerMoveUp(true);
                case KeyEvent.VK_A -> game.getPlayerHandler().setPlayerMoveLeft(true); 
                case KeyEvent.VK_D -> game.getPlayerHandler().setPlayerMoveRight(true);
                // menyelesaikan game
                case KeyEvent.VK_SPACE -> game.stopGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // melepaskan tombol
        switch (e.getKeyCode()) {
            // mengupdate player movement menjadi false
            case KeyEvent.VK_W -> game.getPlayerHandler().setPlayerMoveUp(false);
            case KeyEvent.VK_A -> game.getPlayerHandler().setPlayerMoveLeft(false);
            case KeyEvent.VK_D -> game.getPlayerHandler().setPlayerMoveRight(false);
        }
    }
}
