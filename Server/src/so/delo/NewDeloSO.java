/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.delo;

import domain.Delo;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class NewDeloSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Delo))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String onDuplicate = "ON DUPLICATE KEY UPDATE "
            + "naslov = values(naslov), godina = values(godina), "
            + "opis = values(opis), "
            + "jezik = values(jezik), korisnikID = values(korisnikID)";

        databaseBroker.add((Delo) object, onDuplicate);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
