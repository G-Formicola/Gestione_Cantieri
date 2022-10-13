package Entity;

import java.time.LocalDate;

public class Cantiere {

    private String titolo ;
    private String descrizione ;
    private LocalDate startDate ;
    private LocalDate finishDate ;
    private String localita;
    private Integer idAdmin ;
    private Integer idCapoC ;
    private Integer idCantiere ;

    //Costruttori
    public Cantiere(String titolo, String descrizione, LocalDate startDate, LocalDate finishDate, String localita, Integer idAdmin, Integer idCapoC) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.localita = localita;
        this.idAdmin = idAdmin;
        this.idCapoC = idCapoC;
        this.idCantiere = null ;
    }

    public Cantiere() {
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getLocalita() {
        return localita;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public Integer getIdCapoC() {
        return idCapoC;
    }

    public Integer getIdCantiere() {
        return idCantiere;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setIdCapoC(Integer idCapoC) {
        this.idCapoC = idCapoC;
    }

    public void setIdCantiere(Integer idCantiere) {
        this.idCantiere = idCantiere;
    }
}
