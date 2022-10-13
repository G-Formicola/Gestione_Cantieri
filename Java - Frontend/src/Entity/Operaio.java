package Entity;

import java.time.LocalDate;

public class Operaio {

    private String nome ;
    private String cognome ;
    private String sesso ;
    private LocalDate data_n ; //rs.getObject("datan", LocalDate.class)); per la lettura da rs  // insertImpiegato.setObject(4, nuovoImpiegato.getDataNascita()); con insertImpiegato prepared statement.
    private String codiceFiscale ;
    private String cellulare ;
    private String cellulare2 ;
    private Integer id_Operaio ;

    public Operaio (){}
    public Operaio(String nome, String cognome, String sesso, LocalDate data_n, String codiceFiscale, String cellulare, String cellulare2) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_n = data_n;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
        if (cellulare2 == null)
            this.cellulare2 = "Non Specificato";
        else
            this.cellulare2 = cellulare2;
        this.id_Operaio = null;
    }
    /*public Operaio(String nome, String cognome, String sesso, LocalDate data_n, String codiceFiscale, String cellulare, String cellulare2, Integer id_Operaio) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_n = data_n;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
        if (cellulare2.isEmpty())
            this.cellulare2 = "Non Specificato";
        else
            this.cellulare2 = cellulare2;
        this.id_Operaio = id_Operaio;
    }*/

    @Override
    public String toString() {
        return "Operaio - " + cognome + " " + nome ;
    }

    //Getter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public LocalDate getData_n() {
        return data_n;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getCellulare() {
        return cellulare;
    }

    public String getCellulare2() {
        return cellulare2;
    }

    public Integer getId_Operaio() {
        return id_Operaio;
    }

    // Setter
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setData_n(LocalDate data_n) {
        this.data_n = data_n;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public void setCellulare2(String cellulare2) {
        this.cellulare2 = cellulare2;
    }

    public void setId_Operaio(Integer id_Operaio) {
        this.id_Operaio = id_Operaio;
    }
}