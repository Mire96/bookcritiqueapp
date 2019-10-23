/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import domain.Autor;
import domain.Delo;
import domain.Korisnik;
import domain.Kritika;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Phenom
 */
public class Controller {
    private static Controller instance;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    //Operation LOG IN
    public Korisnik login(String username, String password) throws Exception {
        Request request = new Request();
        Korisnik k = new Korisnik();
        k.setUsername(username);
        k.setPassword(password);
        request.setOperation(Operation.OPERATION_LOGIN);
        request.setData(k);
        
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        
        if(response.getStatus() == ResponseStatus.OK){
            Korisnik korisnik = (Korisnik)response.getData();
            Session.getInstance().setKorisnik(korisnik);
            return korisnik;
        }
        throw new Exception(response.getError().toString());
    }
    
    //Operation REGISTER
    public void register(String username, String password, String ime, String prezime) throws IOException, ClassNotFoundException, Exception{
        Request request = new Request();
        Korisnik k = new Korisnik();
        k.setIme(ime);
        k.setPrezime(prezime);
        k.setUsername(username);
        k.setPassword(password);
        
        request.setOperation(Operation.OPERATION_REGISTER);
        request.setData(k);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        
        if(response.getStatus() == ResponseStatus.OK){
            //uloguj se, vrati se na FrmLogin
        }else
            throw new Exception(response.getError().toString());
    }

    public void logout(Korisnik korisnik) throws Exception {
        Request request = new Request();
        request.setOperation(Operation.OPERATION_LOGOUT);
        request.setData(korisnik);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.OK){
           //izloguj se
        }
        else 
            throw new Exception(response.getError().toString());
    }

    public void updateKorisnik(Korisnik novi) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OPERATION_EDIT_USER);
        request.setData(novi);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        
        if(response.getStatus() == ResponseStatus.OK){
            Session.getInstance().setKorisnik(novi);
        }else 
           throw new Exception(response.getError().toString());  
    }

    public List<Korisnik> getAllUsers(String search) throws IOException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OPERATION_SEARCH_USERS);
        request.setData(search);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        
        if(response.getStatus() == ResponseStatus.OK)
            return (List<Korisnik>) response.getData();
        
        throw new Exception(response.getError().toString());
    }

    public void deleteKorisnik(Korisnik korisnik) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OPERATION_DELETE_USER);
        request.setData(korisnik);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            // zatvori prozor
        } else
            throw new Exception(response.getError().toString());
    }

    public List<Autor> pronadjiAutore(Delo delo) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();        
        request.setOperation(Operation.OPERATION_FIND_AUTORS);
        request.setData(delo);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            return (List<Autor>) response.getData();
        }
        throw new Exception(response.getError().toString());
    }
    
    
    public List<Autor> getAutori(String search) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();        
        request.setOperation(Operation.OPERATION_SEARCH_AUTORS);
        request.setData(search);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            return (List<Autor>) response.getData();
        }
        throw new Exception(response.getError().toString());    
    }

    public void sacuvajAutore(Autor a) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setData(a);
        request.setOperation(Operation.OPERATION_NEW_AUTOR);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            // zatvori prozor za unos autora
        } else
            throw new Exception(response.getError().toString()); 
    }

    public void deleteAutor(Autor autor) throws IOException, Exception {
        Request request = new Request();
        request.setData(autor);
        request.setOperation(Operation.OPERATION_DELETE_AUTOR);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            // samo izbrisi iz baze
        } else
            throw new Exception(response.getError().toString());
    }

    /*public void sacuvajDelo(Delo delo, List<Autor> autori) throws IOException, ClassNotFoundException, Exception {
    List<Request> requests = new ArrayList<>();
    requests.add(new Request(Operation.OPERATION_SAVE_DELO, delo));
    requests.add(new Request(Operation.OPERATION_SAVE_AUTOR, autori));
    
    for(Request request : requests){
    Communication.getInstance().sendRequest(request);
    Response response = Communication.getInstance().readResponse();
    if(response.getStatus() == ResponseStatus.OK){
    
    }else
    throw new Exception(response.getError().toString());
    }
    
    }*/
    
    

    public List<Delo> getAllDela(String naslov, String godina) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        
        List<String> pretraga = new ArrayList<>();
        pretraga.add(naslov);
        pretraga.add(godina);
        
        request.setOperation(Operation.OPERATION_SEARCH_DELO);
        request.setData(pretraga);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.OK){
            return (List<Delo>) response.getData();
        }
        throw new Exception(response.getError().toString());
    }

    public void posaljiKritikuNaOdobrenje(Kritika kritika) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setData(kritika);
        request.setOperation(Operation.OPERATION_NEW_KRITIKA);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            
        } else
            throw new Exception(response.getError().toString()); 
    }

    public void odobriKritiku(Kritika kritika) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setData(kritika);
        request.setOperation(Operation.OPERATION_APPROVE_KRITIKA);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            
        } else
            throw new Exception(response.getError().toString()); 
    }

    public void deleteKritika(Kritika kritika) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setData(kritika);
        request.setOperation(Operation.OPERATION_DELETE_KRITIKA);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            
        } else
            throw new Exception(response.getError().toString()); 
    }

    public List<Kritika> getKritike(Delo delo) throws IOException, Exception {
        Request request = new Request();
        request.setData(delo);
        request.setOperation(Operation.OPERATION_GET_KRITIKE);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            return (List<Kritika>) response.getData();
        } else
            throw new Exception(response.getError().toString());
    }

    public List<Kritika> getKritikeZaOdobrenje() throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        request.setOperation(Operation.OPERATION_GET_KRITIKE_ZA_ODOBRENJE);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            return (List<Kritika>) response.getData();
        } else
            throw new Exception(response.getError().toString());
    }

    public void deleteDelo(int deloID) throws IOException, Exception, ClassNotFoundException {
        Request request = new Request();
        request.setData(deloID);
        request.setOperation(Operation.OPERATION_DELETE_DELO);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getStatus() == ResponseStatus.OK) {
            
        } else
            throw new Exception(response.getError().toString());
    }

    public void izmeniDelo(Delo delo, List<Autor> autori) throws IOException, ClassNotFoundException, Exception {
        Request request = new Request();
        delo.setAutori(autori);
        request.setOperation(Operation.OPERATION_UPDATE_DELO);
        request.setData(delo);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.OK){
        
        }else 
            throw new Exception(response.getError().toString());
    }
    
    public void sacuvajDelo(Delo delo, List<Autor> autori) throws IOException, Exception {
        Request request = new Request();
        delo.setAutori(autori);
        request.setOperation(Operation.OPERATION_SAVE_DELO);
        request.setData(delo);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.OK){
        
        }else 
            throw new Exception(response.getError().toString());
    }
    
    

    

    
}
