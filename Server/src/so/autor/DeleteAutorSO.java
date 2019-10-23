/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.autor;

import domain.Autor;
import domain.AutorDelo;
import so.AbstractGenericOperation;

/**
 *
 * @author Phenom
 */
public class DeleteAutorSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object object) throws Exception {
        if (!(object instanceof Autor))
            throw new Exception("Invalid parameter input");
    }

    @Override
    protected void execute(Object object) throws Exception {
        Autor autor = (Autor) object;
        String condition = "autorID = " + autor.getAutorID();
        databaseBroker.delete(new AutorDelo(), condition);
    }

    @Override
    protected void executeJoin(Object object, String table, String joinCriteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
