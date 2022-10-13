package Entity.DAO;

import Entity.Ruolo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RuoloDao {

    //Create
    boolean inserisciRuolo(Ruolo ruolo) throws SQLException;

    //Read
    ArrayList<Integer> getIdRuoliList(); // Tutti gli id dei ruoli assegnati
    ArrayList<Integer> getIdRuoliList(Integer id_cantiere); // Id operai che hanno un ruolo nel cantiere in Input
    ArrayList<Integer> getOperatoriList(Integer id_cantiere);
    int getNumCarpentieri(Integer id_cant);
    int getNumOperatori(Integer id_cant);
    int getNumIng(Integer id_cant);
    int getNumElett(Integer id_cant);
    String getMansione(Integer operaioScelto, Integer cantiereLavori);
    Integer getIdRuolo(Integer operaioScelto, Integer cantiereLavori);


    //Update
    boolean modifyMansione(Integer idRuolo, String mansione) throws SQLException;



    //Delete
}
