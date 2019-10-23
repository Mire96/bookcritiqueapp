/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.IGeneralEntity;
import domain.Korisnik;
import java.util.List;
import session.Session;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class LoginSO extends AbstractGenericOperation{
    
    private List<IGeneralEntity> list;
    private IGeneralEntity entity;

    public IGeneralEntity getEntity() {
        return entity;
    }
    
    

    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions.");
    }

    @Override
    protected void execute(Object object) throws Exception {
        boolean kraj = false;
        Korisnik requestKorisnik = (Korisnik) object;
        
        String condition = "";
        
        list = databaseBroker.getAll(requestKorisnik, condition);
        for (IGeneralEntity iGeneralEntity : list) {
            Korisnik korisnik = (Korisnik) iGeneralEntity;
            if(requestKorisnik.equals(korisnik)){
                if(!isLoggedIn(korisnik)){
                    this.entity = korisnik;
                    kraj = true;
                    Session.getInstance().addUser(korisnik);
                }else{
                    throw new Exception("Korisnik je vec ulogovan");
                }
            }
        }
        
        if(!kraj)
            throw new Exception("Ne postoji korisnik");
    }
    
    private boolean isLoggedIn(Korisnik korisnik) {
        for (Korisnik korisnik1 : Session.getInstance().getUsers()) {
            if (korisnik.equals(korisnik1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
    }
    
}
