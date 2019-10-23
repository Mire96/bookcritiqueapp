/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.delo;

import domain.Delo;
import domain.IGeneralEntity;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class GetAllDelaSO extends AbstractGenericOperation{
    private List<IGeneralEntity> list;
    private String pretragaNaslov;
    private String pretragaGodina;

    public GetAllDelaSO(String pretragaNaslov, String pretragaGodina) {
        this.pretragaNaslov = pretragaNaslov;
        this.pretragaGodina = pretragaGodina;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String condition = "";
        if (!pretragaNaslov.isEmpty() && !pretragaGodina.isEmpty()) {
            condition = "WHERE naslov LIKE '%" + pretragaNaslov + "%' OR godina = '" + pretragaGodina + "'" + " ORDER BY naslov";
        } else if (pretragaNaslov.isEmpty() && !pretragaGodina.isEmpty()) {
            condition = "WHERE godina = '" + pretragaGodina + "'" + " ORDER BY naslov";
        } else if (!pretragaNaslov.isEmpty() && pretragaGodina.isEmpty()) {
            condition = "WHERE naslov LIKE '%" + pretragaNaslov + "%'" + " ORDER BY naslov";
        } else {
            condition = "ORDER BY naslov";
        }
        list = databaseBroker.getAll((Delo) object, condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the list
     */
    public List<IGeneralEntity> getList() {
        return list;
    }
    
}
