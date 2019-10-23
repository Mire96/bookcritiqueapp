/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.autor;

import domain.Delo;
import domain.IGeneralEntity;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class FindAutorsSO extends AbstractGenericOperation{
    
    private List<IGeneralEntity> list;
    
    
    
    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions");
    }

    @Override
    protected void execute(Object object) throws Exception {
        executeJoin(object, "autorDelo", "t1.deloID = t2.deloID");
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        String condition = "WHERE t1.deloID = " + ((Delo) object).getDeloID();
        list = databaseBroker.returnJoinWithCondition(new Delo(), table, "autor", joinCriteria, "t2.autorID = t3.autorID", condition);
    }

    /**
     * @return the list
     */
    public List<IGeneralEntity> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<IGeneralEntity> list) {
        this.list = list;
    }
    
}
