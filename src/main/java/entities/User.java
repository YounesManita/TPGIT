package entities;


public abstract class User {
    private int CIN = 0;
    private String nom, prenom;
    private String daten;
    private String tel;
    private String email;

    public User(int CIN ,String nom,String prenom ,String daten,String tel,String email) {
        this.CIN = CIN;
        this.nom = nom ;
        this.prenom = prenom ;
        this.daten =daten;
        this.tel=tel;
        this.email=email;

    }
    public User() {}

    public int getCIN() {
        return CIN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDaten() {
        return daten;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDaten(String daten) {
        this.daten = daten;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return nom + "  " + prenom + " \n CIN : " + CIN + "\nDate de naissance : " + daten + "\n Contact : " + tel + "   " + email;
    }
}
