/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.delo;

import domain.AutorDelo;
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
public class DeleteDeloSO extends AbstractGenericOperation {

    List<IGeneralEntity> listaKritika;
    List<IGeneralEntity> listaAutorDela;

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Delo)) {
            throw new Exception("Invalid parameter input");
        }
    }

    @Override
    protected void execute(Object object) throws Exception {
        Delo delo = (Delo) object;
        Kritika kritika = new Kritika();
        AutorDelo autorDelo = new AutorDelo();
        String condition = "deloID = '" + delo.getDeloID() + "'";
        
        databaseBroker.delete(kritika, condition);
        databaseBroker.delete(autorDelo, condition);
        databaseBroker.delete(delo, condition);
        /*String condition = "WHERE deloID = '" + delo.getDeloID() + "'";
        listaKritika = databaseBroker.getAll(new Kritika(), condition);
        listaAutorDela = databaseBroker.getAll(new AutorDelo(), condition);
        for (IGeneralEntity iGeneralEntity : listaKritika) {
        Kritika kritika = (Kritika) iGeneralEntity;
        String condition1 = "kritikaID = '" + kritika.getKritikaID() + "'";
        databaseBroker.delete(kritika, condition1);
        }
        
        for (IGeneralEntity iGeneralEntity : listaAutorDela) {
        AutorDelo autorDelo = (AutorDelo) iGeneralEntity;
        String condition1 = "deloID = '" + autorDelo.getDeloID() + "'";
        databaseBroker.delete(autorDelo, condition1);
        }
        */

        
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
