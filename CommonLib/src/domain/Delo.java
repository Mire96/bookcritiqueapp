/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.role.UlogaKorisnika;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Phenom
 */
public class Delo implements IGeneralEntity{
    
    private int deloID;
    private String naslov;
    private int godina;
    private String opis;
    private String jezik;
    private Korisnik korisnik;
    private List<Autor> autori;

    public Delo() {
    }

    public Delo(int deloID, String naslov, int godina, String opis, String jezik, Korisnik korisnik, List<Autor> autori) {
        this.deloID = deloID;
        this.naslov = naslov;
        this.godina = godina;
        this.opis = opis;
        this.jezik = jezik;
        this.korisnik = korisnik;
        this.autori = autori;
    }

    public Delo(int deloID, String naslov, int godina, String opis, String jezik, Korisnik korisnik) {
        this.deloID = deloID;
        this.naslov = naslov;
        this.godina = godina;
        this.opis = opis;
        this.jezik = jezik;
        this.korisnik = korisnik;
        this.autori = new ArrayList<>();
    }
    
    
    
    
    /**
     * @return the deloID
     */
    public int getDeloID() {
        return deloID;
    }

    /**
     * @param deloID the deloID to set
     */
    public void setDeloID(int deloID) {
        this.deloID = deloID;
    }

    /**
     * @return the naslov
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * @param naslov the naslov to set
     */
    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    /**
     * @return the godina
     */
    public int getGodina() {
        return godina;
    }

    /**
     * @param godina the godina to set
     */
    public void setGodina(int godina) {
        this.godina = godina;
    }

    /**
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * @return the jezik
     */
    public String getJezik() {
        return jezik;
    }

    /**
     * @param jezik the jezik to set
     */
    public void setJezik(String jezik) {
        this.jezik = jezik;
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

    @Override
    public String toString() {
        return naslov;
    }
    
    
    
    
    @Override
    public String getTableName() {
        return "delo";
    }

    @Override
    public String getColumnsInsert() {
        return "deloID, naslov, godina, opis, jezik, korisnikID";
    }

    @Override
    public String getValuesInsert() {
        return "'"+ deloID+"','" + naslov + "','" + godina + "','" + opis + "','" + jezik + "','" + korisnik.getKorisnikID() + "'";
    }

    @Override
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception {
        List<IGeneralEntity> list = new LinkedList<>();
        while(resultSet.next()){
            int deloID = resultSet.getInt("deloID");
            String naslov = resultSet.getString("naslov");
            int godina = resultSet.getInt("godina");
            String opis = resultSet.getString("opis");
            String jezik = resultSet.getString("jezik");
            int korisnikID = resultSet.getInt("korisnikID");
            
            Delo delo = new Delo(deloID, naslov, godina, opis, jezik, new Korisnik(korisnikID, null, null, null, null, UlogaKorisnika.USER));
            list.add(delo);
        }
        
        return list;
    }

    /**
     * @return the autori
     */
    public List<Autor> getAutori() {
        return autori;
    }

    /**
     * @param autori the autori to set
     */
    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

}
