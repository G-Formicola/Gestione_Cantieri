package Entity;

import java.time.LocalDate;

public class Amministratore {

    private String nome ;
    private String cognome ;
    private String sesso ;
    private LocalDate data_n ; //rs.getObject("datan", LocalDate.class)); per la lettura da rs  // insertImpiegato.setObject(4, nuovoImpiegato.getDataNascita()); con insertImpiegato prepared statement.
    private String codiceFiscale ;
    private String cellulare ;
    private String cellulare2 ;
    private String email ;
    private String pswrd ;
    private Integer id_amministratore ;

    public Amministratore (){
    }
    public Amministratore(String nome, String cognome, String sesso, LocalDate data_n, String codiceFiscale, String cellulare, String cellulare2, String email, String pswrd) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_n = data_n;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
        if (cellulare2==null)
            this.cellulare2 = "Non Specificato";
        else
            this.cellulare2 = cellulare2;
        this.email = email;
        this.pswrd = pswrd;
        this.id_amministratore = null;
    }
    /*public Amministratore(String nome, String cognome, String sesso, LocalDate data_n, String codiceFiscale, String cellulare, String cellulare2, String email, String pswrd, Integer id_amministratore) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_n = data_n;
        this.codiceFiscale = codiceFiscale;
        this.cellulare = cellulare;
        if (cellulare2==null)
            this.cellulare2 = "Non Specificato";
        else
            this.cellulare2 = cellulare2;
        this.email = email;
        this.pswrd = pswrd;
        this.id_amministratore = id_amministratore;
    }*/


    @Override
    public String toString() {
        return "Amministratore - " +cognome + " " + nome ;
    }

    // Getter
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

    public String getEmail() {
        return email;
    }

    public String getPswrd() {
        return pswrd;
    }

    public Integer getId_amministratore() {
        return id_amministratore;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public void setId_amministratore(Integer id_amministratore) {
        this.id_amministratore = id_amministratore;
    }
}

