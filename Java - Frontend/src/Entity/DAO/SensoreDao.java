package Entity.DAO;

import Entity.Sensore;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SensoreDao {
    //Create
    boolean inserisciSensore(Sensore sensore) throws SQLException;
    //Read
    ArrayList<Integer> getIdList();
    //Update
    boolean modifySoglia(Integer idSensore, Integer newSoglia) throws SQLException;

    Integer getNumSensori(Integer id_cant);
    //Delete
}
