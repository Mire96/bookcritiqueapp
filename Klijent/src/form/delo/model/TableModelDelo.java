/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.delo.model;

import domain.Delo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Phenom
 */
public class TableModelDelo extends AbstractTableModel{
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    List<Delo> lista = new ArrayList<>();
    String[] header = {"Naslov", "Godina", "Opis"};

    public TableModelDelo() {
    }
    
    public TableModelDelo (List<Delo> lista){
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Delo d = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return d.getNaslov();
            case 1:
                return d.getGodina();
            case 2:
                return d.getOpis();
            default: return "N/A";
        }
    }
    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    public Delo get(int selectedRow){
        return lista.get(selectedRow);
    }
    
}
