package Entity;

import java.sql.Time;
import java.time.LocalDate;

public class Report_Sensore {
    private LocalDate dataRilievo ;
    private Time oreRilievo ;
    private Integer valoreRilevato ;
    private Integer id_Report_Sensore ;
    private Integer id_Sensore ;
    private Integer id_Area_Rilievo ;

    public Report_Sensore(LocalDate dataRilievo, Time oreRilievo, Integer valoreRilevato, Integer id_Sensore, Integer id_Area_Rilievo) {
        this.dataRilievo = dataRilievo;
        this.oreRilievo = oreRilievo;
        this.valoreRilevato = valoreRilevato;
        this.id_Sensore = id_Sensore;
        this.id_Area_Rilievo = id_Area_Rilievo;
    }

    public LocalDate getDataRilievo() {
        return dataRilievo;
    }

    public Time getOreRilievo() {
        return oreRilievo;
    }

    public Integer getValoreRilevato() {
        return valoreRilevato;
    }

    public Integer getId_Sensore() {
        return id_Sensore;
    }

    public Integer getId_Area_Rilievo() {
        return id_Area_Rilievo;
    }
}
