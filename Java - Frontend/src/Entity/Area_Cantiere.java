package Entity;

public class Area_Cantiere {

    private String nomeZona ;
    private String descrizione ;
    private Integer id_Area_Cantiere ;
    private Integer id_Lavoratore ;
    private Integer id_CantiereLavori ;

    public Area_Cantiere(String nomeZona, String descrizione, Integer id_Lavoratore, Integer id_CantiereLavori) {
        this.nomeZona = nomeZona;
        if (descrizione == null)
            this.descrizione = "Non Specificato";
        else
            this.descrizione = descrizione;
        this.id_Lavoratore = id_Lavoratore;
        this.id_CantiereLavori = id_CantiereLavori;
    }

    public Area_Cantiere() {

    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setNomeZona(String nomeZona) {
        this.nomeZona = nomeZona;
    }

    public void setId_Area_Cantiere(Integer id_Area_Cantiere) {
        this.id_Area_Cantiere = id_Area_Cantiere;
    }

    public String getNomeZona() {
        return nomeZona;
    }

    public Integer getId_Lavoratore() {
        return id_Lavoratore;
    }

    public Integer getId_CantiereLavori() {
        return id_CantiereLavori;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return "Area Cantiere : " + nomeZona + " | ID : " + id_Area_Cantiere + " | Descrizione : " + descrizione ;
    }
}
