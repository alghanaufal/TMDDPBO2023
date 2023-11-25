/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import model.Table;

/**
 *
 * @author naufal
 */
public class TablePoint {
    Table tableP;

    public TablePoint() {
        try {
            this.tableP = new Table();
        } catch (Exception ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DefaultTableModel getPlayerTable(){
        // mengambil semua data dan buat sebagai DefaultTableModel
        DefaultTableModel dataTabel = null;
        try{
            Object[] column = {"Username", "Score", "Standing"};
            dataTabel = new DefaultTableModel(null, column);
            // munculkan data
            this.tableP.getAll();
            while(this.tableP.getResult().next()){
                Object[] row = new Object[3];
                row[0] = this.tableP.getResult().getString(1);
                row[1] = this.tableP.getResult().getString(2);
                row[2] = this.tableP.getResult().getString(3);
                dataTabel.addRow(row);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return dataTabel;
    }
}
