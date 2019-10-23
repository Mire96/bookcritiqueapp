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
public class GetKritikeZaOdobrenjeSO extends AbstractGenericOperation{
    
    List<IGeneralEntity> list;
    
    @Override
    protected void validate(Object object) throws Exception {
        System.out.println("No preconditions");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String condition = "WHERE statusKritike = '3'";
        list = databaseBroker.getAll((Kritika) object, condition);
        for (IGeneralEntity iGeneralEntity : list) {
            Kritika kritika = (Kritika) iGeneralEntity;
            
            String condition1 = "WHERE korisnikID = " + kritika.getKorisnik().getKorisnikID();
            Korisnik korisnik = (Korisnik) databaseBroker.findElement(kritika.getKorisnik(), condition1);
            kritika.setKorisnik(korisnik);
            
            String condition2 = "WHERE deloID = '" + kritika.getDelo().getDeloID()+ "'";
            Delo delo = (Delo) databaseBroker.findElement(kritika.getDelo(), condition2);
            kritika.setDelo(delo);
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
