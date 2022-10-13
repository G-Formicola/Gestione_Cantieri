package Entity;

public class Sensore {

    private String sensType ;
    private Integer soglia ;
    private Integer id_sensore ;

    public Sensore(String sensType, Integer soglia) {
        this.sensType = sensType ;
        this.soglia = soglia ;
        id_sensore = null ;
    }

    public String getSensType() {
        return sensType;
    }

    public Integer getSoglia() {
        return soglia;
    }
}
