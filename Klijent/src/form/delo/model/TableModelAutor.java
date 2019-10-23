/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.delo.model;

import domain.Autor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import session.Session;

/**
 *
 * @author Phenom
 */
public class TableModelAutor extends AbstractTableModel{
    
    private List<Autor> lista = new ArrayList<>();

    public TableModelAutor() {
    }
    
    public TableModelAutor(List<Autor> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return getLista().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getLista().get(rowIndex).toString();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }
    
    @Override
    public String getColumnName(int column) {
        return "Autor";
    }

    /**
     * @return the lista
     */
    public List<Autor> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Autor> lista) {
        this.lista = lista;
    }
    
    public void addAutor(Autor a){
        lista.add(a);
        fireTableDataChanged();
    }
    
    public Autor get(int row){
        return lista.get(row);
    }

    public void selectAutor(int rowIndex) {
        Session.getInstance().setAutor(lista.get(rowIndex));
    }

    public void saveAutor(Autor a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void obrisiAutora(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }
    
    
}
