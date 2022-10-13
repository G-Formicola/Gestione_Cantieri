package Entity;

import java.time.LocalDate;

public class Ruolo {

    private String mansione ;
    private LocalDate dataInizio ;
    private Integer id_Lavoratore ;
    private Integer id_CantiereLavori ;
    private String descrizione ;
    private Integer id_Ruolo ;

    public Ruolo(){

    }

    public Ruolo(String mansione, LocalDate dataInizio, Integer id_Lavoratore, Integer id_CantiereLavori, String descrizione) {
        this.mansione = mansione ;
        this.dataInizio = dataInizio ;
        this.id_Lavoratore = id_Lavoratore ;
        this.id_CantiereLavori = id_CantiereLavori ;
        if (descrizione.equals(""))
            this.descrizione = "Non specificato";
        else
            this.descrizione = descrizione ;
    }

    public String getMansione() {
        return mansione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
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

    public Integer getId_Ruolo() {
        return id_Ruolo;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setId_Lavoratore(Integer id_Lavoratore) {
        this.id_Lavoratore = id_Lavoratore;
    }

    public void setId_CantiereLavori(Integer id_CantiereLavori) {
        this.id_CantiereLavori = id_CantiereLavori;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId_Ruolo(Integer id_Ruolo) {
        this.id_Ruolo = id_Ruolo;
    }
}
