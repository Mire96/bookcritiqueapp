/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.IGeneralEntity;
import java.util.List;

/**
 *
 * @author Phenom
 */
public interface IDatabaseBroker {
    void loadDriver() throws Exception;
    void openConnection() throws Exception;
    void closeConnection() throws Exception;
    void commitTransaction() throws Exception;
    void rollbackTransaction() throws Exception;
    
    List<IGeneralEntity> getAll(IGeneralEntity entity, String condition) throws Exception;

    IGeneralEntity findElement(IGeneralEntity entity, String condition) throws Exception;
    
    List<IGeneralEntity> returnJoinWithCondition(IGeneralEntity entity, String table1, String table2, String joinCriteria, String joinCriteria2, String condition) throws Exception;
    
    void add(IGeneralEntity entity, String onDuplicate) throws Exception;
    
    void update(IGeneralEntity entity, String valueSet, String condition) throws Exception;
    
    void delete(IGeneralEntity entity, String condition) throws Exception;
}
