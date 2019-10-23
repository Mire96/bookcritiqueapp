/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.korisnik.model;

import domain.Korisnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Phenom
 */
public class TableModelKorisnik extends AbstractTableModel{
    List<Korisnik> lista = new ArrayList<>();
    String[] header = {"Korisnik", "Username"};

    public TableModelKorisnik() {
    }
    
    public TableModelKorisnik(List<Korisnik> lista){
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
        Korisnik k = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return k.toString();
            case 1:
                return k.getUsername();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    
    public Korisnik get (int rowIndex){
        return lista.get(rowIndex);
    }
    
    public void remove(int rowIndex){
        lista.remove(rowIndex);
        fireTableDataChanged();
    }
}
