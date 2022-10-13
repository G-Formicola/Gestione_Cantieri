package Entity.ViewsEntities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AreeDBView {

    private SimpleIntegerProperty idArea ;
    public void setIdArea(Integer value) { idAreaProperty().set(value); }
    public Integer getIdArea() { return idAreaProperty().get(); }
    public IntegerProperty idAreaProperty() {
        if (idArea == null) idArea = new SimpleIntegerProperty(this, "idArea");
        return idArea;
    }

    private SimpleStringProperty nomeZona ;
    public void setNomeZona(String tipo) { nomeZonaProperty().set(tipo); }
    public String getNomeZona() { return nomeZonaProperty().get(); }
    public StringProperty nomeZonaProperty() {
        if (nomeZona == null) nomeZona = new SimpleStringProperty(this, "nomeZona");
        return nomeZona;
    }

    private SimpleStringProperty descrizione ;
    public void setDescrizione(String tipo) { descrizioneProperty().set(tipo); }
    public String getDescrizione() { return descrizioneProperty().get(); }
    public StringProperty descrizioneProperty() {
        if (descrizione == null) descrizione = new SimpleStringProperty(this, "descrizione");
        return descrizione;
    }

    private SimpleIntegerProperty idResponsabile ;
    public void setIdResponsabile(Integer value) { idResponsabileProperty().set(value); }
    public Integer getIdResponsabile() { return idResponsabileProperty().get(); }
    public IntegerProperty idResponsabileProperty() {
        if (idResponsabile == null) idResponsabile = new SimpleIntegerProperty(this, "idResponsabile");
        return idResponsabile;
    }


}
