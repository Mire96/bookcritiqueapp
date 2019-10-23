/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Phenom
 */
public interface IGeneralEntity extends Serializable{
    public String getTableName();
    public String getColumnsInsert();
    public String getValuesInsert();
    
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception;
}
