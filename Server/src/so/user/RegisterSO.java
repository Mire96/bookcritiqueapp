/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.IGeneralEntity;
import domain.Korisnik;
import domain.role.UlogaKorisnika;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class RegisterSO extends AbstractGenericOperation{
    
    List<IGeneralEntity> list;
    
    

    @Override
    protected void validate(Object object) throws Exception {
        if(!(object instanceof Korisnik))
            throw new Exception("Invalid parameters for registration");
        
        try {
            Korisnik korisnik = (Korisnik) object;
            String condition = "WHERE username = '" + korisnik.getUsername() + "'";
            Korisnik proba = (Korisnik) databaseBroker.findElement(korisnik, condition);
            throw new Exception();
        } catch (IndexOutOfBoundsException ex) {
            
        } catch (Exception e){
            throw new Exception("Username already exists");
        }
        
        
    }

    @Override
    protected void execute(Object object) throws Exception {
        /*String condition = "";*/
        Korisnik korisnik = (Korisnik) object;
        /*  list = databaseBroker.getAll(korisnik, condition);
        for (IGeneralEntity iGeneralEntity : list) {
        if(((Korisnik) iGeneralEntity).getUsername().equals(korisnik.getUsername())){
        throw new Exception("Sistem ne moze da kreira novog korisnika, jer u bazi vec postoji korisnik sa ovim username-om!");
        }
        }*/
        
        String onDuplicate = "ON DUPLICATE KEY UPDATE "
            + "ime = values(ime), prezime = values(prezime), "
            + "username = values(username), password = values(password), "
            + "ulogaKorisnika = values(ulogaKorisnika)";
        
        korisnik.setAdminRole(UlogaKorisnika.USER); 
        databaseBroker.add(korisnik, onDuplicate);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
