/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import storage.IDatabaseBroker;
import storage.database.DatabaseBroker;

/**
 *
 * @author Phenom
 */
public abstract class AbstractGenericOperation {
    
    protected IDatabaseBroker databaseBroker;

    public AbstractGenericOperation() {
        this.databaseBroker = new DatabaseBroker();
    }
    
    public final void templateExecute (Object object) throws Exception{
        try {
            loadDriver();
            openConnection();
            validate(object);
            execute(object);
            commitTransaction();
        } catch (Exception ex){
            rollbackTransaction();
            throw ex;
        } finally {
            closeConnection();
        }
    }

    private void loadDriver() throws Exception {
        databaseBroker.loadDriver();
    }

    private void openConnection() throws Exception {
        databaseBroker.openConnection();
    }

    protected abstract void validate(Object object) throws Exception;

    protected abstract void execute(Object object) throws Exception;
    
    protected abstract void executeJoin(Object object, String table, String joinCriteria) throws Exception;

    private void commitTransaction() throws Exception {
        databaseBroker.commitTransaction();
    }

    private void rollbackTransaction() throws Exception {
        databaseBroker.rollbackTransaction();
    }

    private void closeConnection() throws Exception {
        databaseBroker.closeConnection();
    }
    
    
}
