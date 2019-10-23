/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.Korisnik;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class DeleteUserSO extends AbstractGenericOperation {

    public DeleteUserSO() {
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Korisnik))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        Korisnik korisnik = (Korisnik) object;
        String condition = "korisnikID = " + korisnik.getKorisnikID();
        databaseBroker.delete(korisnik, condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
