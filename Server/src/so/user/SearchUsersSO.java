/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.IGeneralEntity;
import domain.Korisnik;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class SearchUsersSO extends AbstractGenericOperation {
    
    private List<IGeneralEntity> list;
    private String searchQuery;

    public SearchUsersSO(String search) {
        searchQuery = search;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String condition = "WHERE ime LIKE '%" + searchQuery +"%' OR prezime LIKE '%" + searchQuery + "%' OR username LIKE '%" + searchQuery + "%'" + " ORDER BY ime";
        list = databaseBroker.getAll((Korisnik) object, condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        
    }

    public Object getList() {
        return list;
    }
    
}
