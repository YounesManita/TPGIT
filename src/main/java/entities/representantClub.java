package entities;

public class representantClub extends User {

    private String mdp_user;
    private String etatCotisation;
public representantClub(int CIN ,String nom,String prenom ,String daten,String tel,String email,String mdp_user,String etatCotisation){
        super(CIN,nom,prenom,daten,tel,email);
        this.mdp_user =mdp_user;
        this.etatCotisation=etatCotisation;
    }

    public String getMDP() {
        return mdp_user;
    }
    public String getetatCotisation() {
        return etatCotisation;
    }

    public void setMDP(String mdp_user) {
        this.mdp_user=mdp_user;
    }

    public void setEtatCotisation(String etatCotisation) {
        this.etatCotisation = etatCotisation;
    }

    @Override
    public String toString() {
        return "Les inforamtion concernant le representant du Club : " + super.toString()+" "+"\n etat Cotisation du reprsentant:"+" "+etatCotisation;
    }

}
