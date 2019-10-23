/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Delo;
import domain.Korisnik;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phenom
 */
public class Session {
    private static Session instance;
    private Delo delo;
    private List<Korisnik> loggedInUsers;

    private Session() {
        loggedInUsers = new ArrayList<>();
    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

    /**
     * @return the delo
     */
    public Delo getDelo() {
        return delo;
    }

    /**
     * @param delo the delo to set
     */
    public void setDelo(Delo delo) {
        this.delo = delo;
    }

    /**
     * @return the loggedInUsers
     */
    public List<Korisnik> getUsers() {
        return loggedInUsers;
    }

    /**
     * @param loggedInUsers the loggedInUsers to set
     */
    public void setUsers(List<Korisnik> loggedInUsers) {
        this.loggedInUsers = loggedInUsers;
    }
    
    public void addUser(Korisnik korisnik){
        loggedInUsers.add(korisnik);
    }
    
    public void removeUser(Korisnik korisnik){
        loggedInUsers.remove(korisnik);
    }
    
    
    
    
    
    
}
