/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kritika;

import domain.Delo;
import domain.IGeneralEntity;
import domain.Korisnik;
import domain.Kritika;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class GetKritikeSO extends AbstractGenericOperation{
    
    List<IGeneralEntity> list;
    
    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions");
    }

    @Override
    protected void execute(Object object) throws Exception {
        Delo delo = (Delo) object;
        String condition = "WHERE deloID = '" + delo.getDeloID() + "' AND statusKritike = '1'";
        list = databaseBroker.getAll(new Kritika(), condition);
        for (IGeneralEntity iGeneralEntity : list) {
            Kritika kritika = (Kritika) iGeneralEntity;
            
            String condition1 = "WHERE korisnikID = " + kritika.getKorisnik().getKorisnikID();
            Korisnik korisnik = (Korisnik) databaseBroker.findElement(kritika.getKorisnik(), condition1);
            kritika.setKorisnik(korisnik);
        }
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getList() {
        return list;
    }
    
}
