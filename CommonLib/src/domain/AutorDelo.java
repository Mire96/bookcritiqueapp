/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Phenom
 */
public class AutorDelo implements IGeneralEntity{
    private int deloID;
    private int autorID;

    public AutorDelo() {
    }

    public AutorDelo(int deloID, int autorID) {
        this.deloID = deloID;
        this.autorID = autorID;
    }

    /**
     * @return the deloID
     */
    public int getDeloID() {
        return deloID;
    }

    /**
     * @param deloID the deloID to set
     */
    public void setDeloID(int deloID) {
        this.deloID = deloID;
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

    @Override
    public String getTableName() {
        return "autorDelo";
    }

    @Override
    public String getColumnsInsert() {
        return "deloID, autorID";
    }

    @Override
    public String getValuesInsert() {
        return "'" + deloID + "','" + autorID + "'";
    }

    @Override
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception {
        List<IGeneralEntity> list = new LinkedList<>();
        while(resultSet.next()){
            int deloID = resultSet.getInt("deloID");
            int autorID = resultSet.getInt("autorID");
            
            AutorDelo autorDelo = new AutorDelo(deloID, autorID);
        }
        
        return list;
    }
    
    
}
