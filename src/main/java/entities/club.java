package entities;

public class club {
        private int idclub;
    private String nomc;
    private String catc;
    private String nbrc;
    private String Cotisation;

    public club(int idclub, String nomc, String catc, String nbrc, String Cotisation){
        this.idclub=idclub;
        this.nomc =nomc;
        this.catc =catc;
        this.nbrc=nbrc;
        this.Cotisation=Cotisation;
    }

    public int getIdclub() {
        return idclub ;
    }

    public String getNomc() {
        return nomc;
    }

    public String getCatc() {
        return catc;
    }

    public String getNbrc() {
        return nbrc;
    }
    public String getCotisation() {
        return Cotisation;
    }

    public void setIdclub(int idclub ) {
        this.idclub=idclub;
    }
    public void setNomc(String nomc ) {
        this.nomc=nomc;
    }
    public void setCatc(String catc ) {
        this.catc=catc;
    }

    public void setNbrc(String nbrc ) {
        this.nbrc=nbrc;
    }

    public void setCotisation(String Cotisation ) {
        this.Cotisation=Cotisation;
    }

    @Override
    public String toString() {
        return "Les inforamtion concernant  notre Club : " + " \nom du club : "+"  "+nomc +  "\nCategorie du club : "+ "  " +catc + " \n Nombre totale des memebres: " +  "  "+ nbrc + "Cotisation par Membre:"+Cotisation;
    }

}