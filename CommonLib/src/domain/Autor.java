/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Phenom
 */
public class Autor implements IGeneralEntity{
    private int autorID;
    private String ime;
    private String prezime;

    public Autor() {
    }

    public Autor(int autorID, String ime, String prezime) {
        this.autorID = autorID;
        this.ime = ime;
        this.prezime = prezime;
    }
    
    
    
    /**
     * @return the autorID
     */
    public int getAutorID() {
        return autorID;
    }

    /**
     * @param autorID the autorID to set
     */
    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "autor";
    }

    @Override
    public String getColumnsInsert() {
        return "ime, prezime";
    }

    @Override
    public String getValuesInsert() {
        return "'" + ime + "','" + prezime + "'";
    }

    @Override
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception {
        List<IGeneralEntity> list = new LinkedList<>();
        while(resultSet.next()){
            int autorID = resultSet.getInt("autorID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            Autor autor = new Autor(autorID, ime, prezime);
            list.add(autor);
        }
        
        return list;
    }
    
    
    
    
}
