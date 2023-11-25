/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naufal
 */
public class Table extends DB{
    public Table() throws Exception, SQLException{
        super();
    }

    public void getAll(){
        // mengeksekusi query untuk mengambil semua data tabel (tampilkan secara descendant)
        String query = "SELECT * from player order by score DESC";
        try {
            createQuery(query);
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUpdateScore(String username, int score, int standing){
        // method mengubdate score pada table atau menambah score baru
        try {
            String query = "UPDATE player SET score = score + " + score + ", standing = standing + " + standing + " WHERE username = '" + username + "'";
            // eksekusi query untuk update data
            if(createUpdate(query) == 0){
                // jika tidak ada update, buat data baru
                query = "INSERT INTO player VALUES('" + username + "', " + Integer.toString(score) + ", " + Integer.toString(standing) + ")";
                createUpdate(query);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }    
}
