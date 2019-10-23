/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.kritika.model;

import domain.Kritika;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import session.Session;
import usecase.UseCase;

/**
 *
 * @author Phenom
 */
public class TableModelKritika extends AbstractTableModel {
    
    List<Kritika> lista = new ArrayList<>();
    String[] header = {"Korisnik", "Kritika", "Delo"};

    public TableModelKritika() {
    }
    
    public TableModelKritika(List<Kritika> lista){
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        if (Session.getInstance().getCurrentUseCase() == UseCase.UC_VIEW_KRITIKA) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kritika k = lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return k.getKorisnik().getUsername();
            case 1:
                return k.getSadrzaj();
            case 2:
                return k.getDelo().getNaslov();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    public Kritika get(int rowIndex) {
        return lista.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        lista.remove(rowIndex);
    }
    
}
