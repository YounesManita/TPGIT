package entities;

public class administrateur extends User {

    private String mdp_user;
    public administrateur(){}
    administrateur(int CIN ,String nom,String prenom ,String daten,String tel,String email,String mdp_user){
        super(CIN ,nom,prenom ,daten,tel,email);
        this.mdp_user =mdp_user;
    }

    public String getMDP() {
        return mdp_user;
    }

    public void setMDP(String mdp_user) {
        this.mdp_user=mdp_user;
    }

    @Override
    public String toString() {
        return "Les inforamtion concernant l'administrateur : " + super.toString();
    }

}
