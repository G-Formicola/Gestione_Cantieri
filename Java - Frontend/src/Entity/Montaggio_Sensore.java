package Entity;

import java.time.LocalDate;

public class Montaggio_Sensore {
    private Integer id_Montaggio_Sensore;
    private LocalDate dataMontaggio ;
    private Integer id_Sensore ;
    private Integer id_Area ;
    private Integer id_Operatore ;

    public Montaggio_Sensore( LocalDate dataMontaggio, Integer id_Sensore, Integer id_Area, Integer id_Operatore) {
        this.dataMontaggio = dataMontaggio;
        this.id_Sensore = id_Sensore;
        this.id_Area = id_Area;
        this.id_Operatore = id_Operatore;
    }


    public LocalDate getDataMontaggio() {
        return dataMontaggio;
    }

    public Integer getId_Sensore() {
        return id_Sensore;
    }

    public Integer getId_Area() {
        return id_Area;
    }

    public Integer getId_Operatore() {
        return id_Operatore;
    }
}
