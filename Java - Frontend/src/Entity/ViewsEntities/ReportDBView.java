package Entity.ViewsEntities;

import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class ReportDBView {

    private SimpleIntegerProperty idSensore ;
    public void setIdSensore(Integer value) { idSensoreProperty().set(value); }
    public Integer getIdSensore() { return idSensoreProperty().get(); }
    public IntegerProperty idSensoreProperty() {
        if (idSensore == null) idSensore = new SimpleIntegerProperty(this, "idSensore");
        return idSensore;
    }

    private SimpleStringProperty tipo ;
    public void setTipo(String tipo) { tipoProperty().set(tipo); }
    public String getTipo() { return tipoProperty().get(); }
    public StringProperty tipoProperty() {
        if (tipo == null) tipo = new SimpleStringProperty(this, "tipo");
        return tipo;
    }

    private SimpleIntegerProperty soglia ;
    public void setSoglia(Integer value) { sogliaProperty().set(value); }
    public Integer getsoglia() { return sogliaProperty().get(); }
    public IntegerProperty sogliaProperty() {
        if (soglia == null) soglia = new SimpleIntegerProperty(this, "soglia");
        return soglia;
    }

    private SimpleStringProperty nomeZona ;
    public void setNomeZona(String tipo) { nomeZonaProperty().set(tipo); }
    public String getNomeZona() { return nomeZonaProperty().get(); }
    public StringProperty nomeZonaProperty() {
        if (nomeZona == null) nomeZona = new SimpleStringProperty(this, "nomeZona");
        return nomeZona;
    }

    private SimpleIntegerProperty idZona ;
    public void setIdZona(Integer value) { idZonaProperty().set(value); }
    public Integer getIdZona() { return idZonaProperty().get(); }
    public IntegerProperty idZonaProperty() {
        if (idZona == null) idZona = new SimpleIntegerProperty(this, "idZona");
        return idZona;
    }


    private SimpleObjectProperty<LocalDate> dataRilievo ;
    public void setDataRilievo(LocalDate value){ dataRilievoProperty().set(value); }
    public LocalDate getDataRilievo(){ return dataRilievoProperty().get(); }
    public ObjectProperty<LocalDate> dataRilievoProperty(){
        if (dataRilievo == null) dataRilievo = new SimpleObjectProperty<LocalDate>(this,"dataRilievo");
        return dataRilievo;
    }

    private SimpleObjectProperty<Time> oreRilievo ;
    public void setOreRilievo(Time value){ oreRilievoProperty().set(value); }
    public Time getOreRilievo(){ return oreRilievoProperty().get(); }
    public ObjectProperty<Time> oreRilievoProperty(){
        if (oreRilievo == null) oreRilievo = new SimpleObjectProperty<Time>(this,"oreRilievo");
        return oreRilievo;
    }

    private SimpleIntegerProperty rilievo;
    public void setRilievo(Integer value) { rilievoProperty().set(value); }
    public Integer getRilievo() { return rilievoProperty().get(); }
    public IntegerProperty rilievoProperty() {
        if (rilievo == null) rilievo = new SimpleIntegerProperty(this, "rilievo");
        return rilievo;
    }

    private SimpleIntegerProperty idReport;
    public void setIdReport(Integer value){ idReportProperty().set(value); }
    public Integer getIdReport() { return idReportProperty().get(); }
    public IntegerProperty idReportProperty(){
        if (idReport == null) idReport = new SimpleIntegerProperty(this, "idReport");
        return idReport;
    }



}
