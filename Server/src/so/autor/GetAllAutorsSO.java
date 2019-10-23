/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.autor;

import domain.IGeneralEntity;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class GetAllAutorsSO extends AbstractGenericOperation{
    
    private List<IGeneralEntity> list;
    private String search;
    
    public List<IGeneralEntity> getList() {
        return list;
    }

    public GetAllAutorsSO( String search) {
        this.search = search;
    }
    
    
    
    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions.");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String condition = "WHERE ime LIKE '%" + search + "%' OR prezime LIKE '%" + search + "%'" + " ORDER BY ime";
        list = databaseBroker.getAll((IGeneralEntity) object, condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
