/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Autor;
import domain.Delo;
import so.user.DeleteUserSO;
import so.user.SearchUsersSO;
import domain.IGeneralEntity;
import domain.Korisnik;
import domain.Kritika;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import so.AbstractGenericOperation;
import so.autor.DeleteAutorSO;
import so.autor.FindAutorsSO;
import so.autor.GetAllAutorsSO;
import so.autor.NewAutorSO;
import so.autor.SaveAutorDeloSO;
import so.delo.DeleteDeloSO;
import so.delo.GetAllDelaSO;
import so.delo.NewDeloSO;
import so.delo.UpdateDeloSO;
import so.kritika.ApproveKritikaSO;
import so.kritika.DeleteKritikaSO;
import so.kritika.GetKritikeSO;
import so.kritika.GetKritikeZaOdobrenjeSO;
import so.kritika.NewKritikaSO;
import so.user.LoginSO;
import so.user.RegisterSO;
import so.user.UpdateUserSO;

/**
 *
 * @author Phenom
 */
public class Controller {

    private static Controller instance;
    private List<Socket> clients;

    private Controller() {
        clients = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * @return the clients
     */
    public List<Socket> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Socket> clients) {
        this.clients = clients;
    }

    public void addClient(Socket socket) {
        clients.add(socket);
    }

    public void removeClient(Socket socket) {
        clients.remove(socket);
    }

    //Implementiraj sve sistemske operacije, idu preko AbstractGenericOperation, stj system operation paketa
    public IGeneralEntity login(Korisnik korisnik) throws Exception {
        AbstractGenericOperation so = new LoginSO();
        so.templateExecute(korisnik);
        return ((LoginSO) so).getEntity();
    }

    public void register(Korisnik korisnik) throws Exception {
        AbstractGenericOperation so = new RegisterSO();
        so.templateExecute(korisnik);
    }

    public void updateKorisnik(Korisnik izmenjeniKorisnik) throws Exception {
        AbstractGenericOperation so = new UpdateUserSO();
        so.templateExecute(izmenjeniKorisnik);

    }

    public Object getAllUsers(String search) throws Exception {
        AbstractGenericOperation so = new SearchUsersSO(search);
        so.templateExecute(new Korisnik());
        return ((SearchUsersSO) so).getList();
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation so = new DeleteUserSO();
        so.templateExecute(korisnik);
    }

    public List<IGeneralEntity> pronadjiAutore(Delo delo) throws Exception {
        AbstractGenericOperation so = new FindAutorsSO();
        so.templateExecute(delo);
        return ((FindAutorsSO) so).getList();
    }

    public Object getAutors(String search) throws Exception {
        AbstractGenericOperation so = new GetAllAutorsSO(search);
        so.templateExecute(new Autor());
        return ((GetAllAutorsSO) so).getList();
    }

    public void sacuvajAutora(Autor autor) throws Exception {
        AbstractGenericOperation so = new NewAutorSO();
        so.templateExecute(autor);
    }

    public void deleteAutor(Autor autor) throws Exception {
        AbstractGenericOperation so = new DeleteAutorSO();
        so.templateExecute(autor);
    }

    public void sacuvajAutorDelo(List<Autor> autori, Delo delo) throws Exception {
        AbstractGenericOperation so = new SaveAutorDeloSO(autori);
        so.templateExecute(delo);
    }

    public void sacuvajDelo(Delo delo) throws Exception {
        AbstractGenericOperation so = new NewDeloSO();
        so.templateExecute(delo);
    }

    public List<IGeneralEntity> getAllDela(String pretragaNaslov, String pretragaGodina) throws Exception {
        AbstractGenericOperation so = new GetAllDelaSO(pretragaNaslov, pretragaGodina);
        so.templateExecute(new Delo());
        return ((GetAllDelaSO)so).getList();
    }

    public void posaljiKritikuNaOdobrenje(Kritika kritika) throws Exception {
        AbstractGenericOperation so = new NewKritikaSO();
        so.templateExecute(kritika);
    }

    public void odobriKritiku(Kritika kritika) throws Exception {
        AbstractGenericOperation so = new ApproveKritikaSO();
        so.templateExecute(kritika);
    }

    public void deleteKritika(Kritika kritika) throws Exception {
        AbstractGenericOperation so = new DeleteKritikaSO();
        so.templateExecute(kritika);
    }

    public Object getKritike(Delo delo) throws Exception {
        AbstractGenericOperation so = new GetKritikeSO();
        so.templateExecute(delo);
        return ((GetKritikeSO)so).getList();
    }

    public Object getKritikeZaOdobrenje() throws Exception {
        AbstractGenericOperation so = new GetKritikeZaOdobrenjeSO();
        so.templateExecute(new Kritika());
        return ((GetKritikeZaOdobrenjeSO)so).getList();
    }

    public void deleteDelo(int deloID) throws Exception {
        AbstractGenericOperation so = new DeleteDeloSO();
        so.templateExecute(new Delo(deloID, null, 2000, null,null, null));
    }

    public void izmeniDelo(Delo delo) throws Exception {
        AbstractGenericOperation so = new UpdateDeloSO();
        so.templateExecute(delo);
    }

}
