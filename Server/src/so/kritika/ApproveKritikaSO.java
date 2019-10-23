/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kritika;

import domain.Kritika;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class ApproveKritikaSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Kritika))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        Kritika kritika = (Kritika) object;
        String valueSet = "statusKritike = '1'";
        String condition = "WHERE kritikaID = '" + kritika.getKritikaID() + "' AND statusKritike = '3'";
        databaseBroker.update(kritika, valueSet, condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
