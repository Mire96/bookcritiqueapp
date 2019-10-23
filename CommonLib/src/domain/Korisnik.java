/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.role.UlogaKorisnika;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Phenom
 */
public class Korisnik implements IGeneralEntity{
    private int korisnikID;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private int adminRole;

    public Korisnik() {
    }

    public Korisnik(int korisnikID, String ime, String prezime, String username, String password, int adminRole) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.adminRole = adminRole;
    }
    
    
    
    /**
     * @return the korisnikID
     */
    public int getKorisnikID() {
        return korisnikID;
    }

    /**
     * @param korisnikID the korisnikID to set
     */
    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    /**
     * @return the adminRole
     */
    public int getAdminRole() {
        return adminRole;
    }

    /**
     * @param adminRole the adminRole to set
     */
    public void setAdminRole(int adminRole) {
        this.adminRole = adminRole;
    }
    

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        if(this.getAdminRole() == UlogaKorisnika.ADMIN)
            return this.ime + " " + this.prezime + ", admin";
        else
            return this.ime + " " + this.prezime + ", korisnik";
    }
    
    
    
    
    
    
     @Override
    public String getTableName() {
        return "korisnik";
    }

    @Override
    public String getColumnsInsert() {
        return "korisnikID, ime, prezime, username, password, ulogaKorisnika";
    }

    @Override
    public String getValuesInsert() {
        return "'" + korisnikID + "','" + ime + "','" + prezime + "','" + username + "','" + password + "','" + getAdminRole() + "'";
    }

    @Override
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception {
        List<IGeneralEntity> list = new LinkedList<>();
        while(resultSet.next()){
            int korisnikID = resultSet.getInt("korisnikID");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            int ulogaKorisnika = resultSet.getInt("ulogaKorisnika");
            
            
            Korisnik korisnik = new Korisnik(korisnikID, ime, prezime, username, password, ulogaKorisnika);
            list.add(korisnik);
        }
        
        return list;
    }

    

    
}
