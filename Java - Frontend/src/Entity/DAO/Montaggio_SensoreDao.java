package Entity.DAO;

import Entity.Montaggio_Sensore;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Montaggio_SensoreDao {
    //Create
    boolean inserisciMontaggioSensore(Montaggio_Sensore montaggio_sensore) throws SQLException;
    //Read
    ArrayList<Integer> getIdList(Integer id_areaCantiere);
    //Update
    //Delete
}
