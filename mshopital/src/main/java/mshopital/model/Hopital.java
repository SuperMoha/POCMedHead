package mshopital.model;

import java.util.ArrayList;

public class Hopital {
    private int id;
    private String name;

    private String adresse;

    private ArrayList<String> specialite;

    private int lits;

    private float longitude;

    private float latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<String> getSpecialite() {
        return specialite;
    }

    public void setSpecialite(ArrayList<String> specialite) {
        this.specialite = specialite;
    }

    public int getLits() {
        return lits;
    }

    public void setLits(int lits) {
        this.lits = lits;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Hopital(int id, String name, String adresse, ArrayList<String> specialite, int lits, float longitude, float latitude) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.specialite = specialite;
        this.lits = lits;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Hopital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", specialite=" + specialite +
                ", lits=" + lits +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
