package Entity.DAO;

import Entity.Operaio;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OperaioDao {
    //Create
    boolean inserisciOperaio(Operaio operaio) throws SQLException;

    //Read
    ArrayList<Integer> getIdList(); // Tutti gli id

    Operaio getOperaioById(Integer operaioScelto);

    //Update
    //Delete
}
