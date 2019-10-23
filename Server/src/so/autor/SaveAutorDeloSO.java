/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.autor;

import domain.Autor;
import domain.AutorDelo;
import domain.Delo;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class SaveAutorDeloSO extends AbstractGenericOperation{
    
    List<Autor> autori;
    
    
    public SaveAutorDeloSO(List<Autor> autori) {
        this.autori = autori;
    }

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Delo))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        String naslov = ((Delo) object).getNaslov().replace("'", "''");
        String condition = "WHERE naslov = '" + naslov + "'";
        Delo delo = (Delo) databaseBroker.findElement(new Delo(), condition);
        
        String onDuplicate = "ON DUPLICATE KEY UPDATE autorID = values(autorID)";
        for (Autor autor : autori) {
            AutorDelo autorDela = new AutorDelo(delo.getDeloID(), autor.getAutorID());
            databaseBroker.add(autorDela, onDuplicate);
        }
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
