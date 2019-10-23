/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.role.UlogaKorisnika;
import domain.status.StatusKritike;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Phenom
 */
public class Kritika implements IGeneralEntity{
    private int kritikaID;
    private Delo delo;
    private String sadrzaj;
    private int statusKritike;
    private Korisnik korisnik;

    public Kritika() {
    }

    public Kritika(int kritikaID, Delo delo, String sadrzaj, int statusKritike, Korisnik korisnik) {
        this.kritikaID = kritikaID;
        this.delo = delo;
        this.sadrzaj = sadrzaj;
        this.statusKritike = statusKritike;
        this.korisnik = korisnik;
    }

    /**
     * @return the kritikaID
     */
    public int getKritikaID() {
        return kritikaID;
    }

    /**
     * @param kritikaID the kritikaID to set
     */
    public void setKritikaID(int kritikaID) {
        this.kritikaID = kritikaID;
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
     * @return the sadrzaj
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * @param sadrzaj the sadrzaj to set
     */
    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public int getStatusKritike() {
        return statusKritike;
    }

    public void setStatusKritike(int statusKritike) {
        this.statusKritike = statusKritike;
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
    public String getTableName() {
        return "kritika";
    }

    @Override
    public String getColumnsInsert() {
        return "deloID, sadrzaj, statusKritike, korisnikID";
    }

    @Override
    public String getValuesInsert() {
        return "'" + delo.getDeloID() + "','" + sadrzaj + "','" + statusKritike + "','" + korisnik.getKorisnikID() + "'";
    }

    @Override
    public List<IGeneralEntity> getAll(ResultSet resultSet, Object obj) throws Exception {
        List<IGeneralEntity> list = new LinkedList<>();
        while(resultSet.next()){
            int kritikaID = resultSet.getInt("kritikaID");
            int deloID = resultSet.getInt("deloID");
            String sadrzaj = resultSet.getString("sadrzaj");
            int statusKritike = resultSet.getInt("statusKritike");
            int korisnikID = resultSet.getInt("korisnikID");
            
            Delo delo = new Delo(deloID, null, 0, null, null,null );
            Korisnik korisnik = new Korisnik(korisnikID, null, null, null, null, UlogaKorisnika.USER);
            Kritika kritika = new Kritika(kritikaID, delo, sadrzaj, statusKritike, korisnik);
        
            list.add(kritika);
        }
        
        return list;
    }
    
    
}
