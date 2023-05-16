package entities;

import entities.User;

public class membreClub extends User {
    private String Image;

    public membreClub(int CIN, String nom, String prenom, String daten, String tel, String email) {
        super(CIN ,nom,prenom,daten,tel,email);
    }
    public membreClub(int CIN, String nom, String prenom, String daten, String tel, String email, String Image){
        super(CIN ,nom,prenom,daten,tel,email);

        this.Image=Image;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "Les inforamtion concernant le membre du notre Club : " + super.toString()+" "+"\n Image path:"+" "+Image;
    }

}
