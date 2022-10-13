package Entity.DAO;

import Entity.Cantiere;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CantiereDao {


    //Create
    public boolean inserisciCantiere (Cantiere cantiere) throws SQLException;

    //Read
    ArrayList<Integer> getIdList();
    ArrayList<String> getNomiList();
    Cantiere getCantiereByTitolo(String titolo);
    Cantiere getCantiereByResp(Integer idCapoCantiere);
    Cantiere getCantiereById(Integer idCantiere);
    //Update
    boolean modifyGestione(Integer idCantiere, Integer nuovoResponsabile) throws SQLException;




    //Delete
}
