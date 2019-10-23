/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Autor;
import domain.Korisnik;
import domain.Kritika;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Phenom
 */
public class Session {
    private static Session instance;
    private Korisnik korisnik;
    private Kritika kritika;
    private Autor autor;
    private Socket socket;
    private int currentUseCase;
    private Map<String, Object> useCaseParams;

    private Session() {
        useCaseParams = new HashMap<>();
    }
    
    

    public static Session getInstance() {
        if(instance == null)
            instance = new Session();
        return instance;
    }

    /**
     * @return the korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * @return the kritika
     */
    public Kritika getKritika() {
        return kritika;
    }

    /**
     * @param kritika the kritika to set
     */
    public void setKritika(Kritika kritika) {
        this.kritika = kritika;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return the currentUseCase
     */
    public int getCurrentUseCase() {
        return currentUseCase;
    }

    /**
     * @param currentUseCase the currentUseCase to set
     */
    public void setCurrentUseCase(int currentUseCase) {
        this.currentUseCase = currentUseCase;
    }

    /**
     * @return the useCaseParams
     */
    public Map<String, Object> getUseCaseParams() {
        return useCaseParams;
    }

    /**
     * @param useCaseParams the useCaseParams to set
     */
    

    
    
    
}
