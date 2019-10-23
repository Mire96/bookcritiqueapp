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
public class UpdateUserSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if(!(object instanceof Korisnik))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String onDuplicate = "ON DUPLICATE KEY UPDATE "
        +"ime = values(ime), prezime = values(prezime), "
        + "username = values(username), password = values(password), ulogaKorisnika = values(ulogaKorisnika)";
        databaseBroker.add((Korisnik) object, onDuplicate);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
    }
    
}
