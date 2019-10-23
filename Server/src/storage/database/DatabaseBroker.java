/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Autor;
import domain.IGeneralEntity;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import storage.IDatabaseBroker;
import util.SettingsLoader;

/**
 *
 * @author Phenom
 */
public class DatabaseBroker implements IDatabaseBroker{
    
    private Connection con;

    @Override
    public void loadDriver() throws Exception {
        try {
            Class.forName(SettingsLoader.getInstance().getValue("driver"));
            System.out.println("Driver loaded");
        } catch (Exception ex) {
            throw new Exception("Error while loading driver" + ex.getMessage());
        } 
    }

    @Override
    public void openConnection() throws Exception {
        try {
        String url= SettingsLoader.getInstance().getValue("url");
        String username = SettingsLoader.getInstance().getValue("username");
        String password = SettingsLoader.getInstance().getValue("password");
        
        con = DriverManager.getConnection(url,username,password);
        con.setAutoCommit(false);
        } catch (Exception ex){
            throw new Exception("Error while opening connection" + ex.getMessage());
        }
    }

    @Override
    public void closeConnection() throws Exception {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new Exception("Error while closing connection" + ex.getMessage());
        }
    }

    @Override
    public void commitTransaction() throws Exception {
        try {
            con.commit();
        } catch (SQLException ex) {
            throw new Exception("Error while commiting transaction" + ex.getMessage());
        }
    }

    @Override
    public void rollbackTransaction() throws Exception {
        try {
            con.rollback();
        } catch (SQLException ex) {
            throw new Exception("Error while rolling back transaction" + ex.getMessage());
        }
    }

    @Override
    public List<IGeneralEntity> getAll(IGeneralEntity entity, String condition) throws Exception {
        try {
            List<IGeneralEntity> list = null;
            String query = "SELECT * FROM " + entity.getTableName() + " " + condition;
            System.out.println(query);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            list = entity.getAll(resultSet, null);
            
            resultSet.close();
            statement.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        
    }

    @Override
    public IGeneralEntity findElement(IGeneralEntity entity, String condition) throws Exception {
        try {
            IGeneralEntity foundEntity = null;
            String query = "SELECT * FROM " + entity.getTableName() + " " + condition;
            System.out.println(query);
            
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            foundEntity = entity.getAll(resultSet, null).get(0);
            resultSet.close();
            statement.close();
            return foundEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        
    }

    @Override
    public List<IGeneralEntity> returnJoinWithCondition(IGeneralEntity entity, String table1, String table2, String joinCriteria, String joinCriteria2, String condition) throws Exception {
        try {
            List<IGeneralEntity> list = new LinkedList<>();
            String query = "SELECT t1.*, t2.*, t3.* "
                    + "FROM " + entity.getTableName() + " t1 "
                    + "INNER JOIN " + table1 + " t2 ON ("+joinCriteria+") "
                    + "INNER JOIN " + table2 + " t3 ON ("+joinCriteria2+") " + condition;
            System.out.println(query);
            
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            switch(table2){
                case "autor":
                    return new Autor().getAll(resultSet, null);
                default:
                    throw new Exception("Table not found!");
            }
                
        } catch (Exception ex) {
            throw new Exception("Error while loading " + entity.getTableName() + " object!", ex);
        }
    }

    @Override
    public void add(IGeneralEntity entity, String onDuplicate) throws Exception {
        try {
            String query = "INSERT INTO " + entity.getTableName() + "(" + entity.getColumnsInsert() + ")"
                    + " VALUES (" + entity.getValuesInsert() + ")"
                    + " " + onDuplicate;
            System.out.println(query);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        } catch (SQLException ex) {
            throw new Exception ("Saving of " + entity.getTableName() + " was unsuccessful", ex);
        }
    }

    @Override
    public void update(IGeneralEntity entity, String valueSet, String condition) throws Exception {
        try {
            String query = "UPDATE " + entity.getTableName() + " SET " + valueSet + " " + condition;
            System.out.println(query);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
        } catch (SQLException ex) {
            throw new Exception("Saving of " + entity.getTableName() + " unsuccessful! \n" + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(IGeneralEntity entity, String condition) throws Exception {
        try {
            String query = "DELETE FROM " + entity.getTableName() + " WHERE " + condition;
            System.out.println(query);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("Error while deleting " + entity.getTableName(), ex);
        }
    }
    
}
