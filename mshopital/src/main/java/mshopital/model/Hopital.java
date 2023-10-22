    package mshopital.model;

    import jakarta.persistence.*;

    import java.util.ArrayList;
    import java.util.List;

    @Entity

    public class Hopital {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nom;

        private String adresse;

        private ArrayList<String> specialite;

        private int lits;

        private float longitude;

        private float latitude;

        public Hopital() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
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

        public Hopital(int id, String nom, String adresse, ArrayList<String> specialite, int lits, float longitude, float latitude) {
            this.id = id;
            this.nom = nom;
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
                    ", nom='" + nom + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", specialite=" + specialite +
                    ", lits=" + lits +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    '}';
        }



    }
