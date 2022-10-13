package Entity.DAO;

import Entity.Area_Cantiere;
import Entity.ViewsEntities.AreeDBView;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Area_CantiereDao {
    //Create
    boolean inserisciAreaCantiere(Area_Cantiere area_cantiere) throws SQLException; // Insert a row into table Area_Cantiere
    //Read
    ArrayList<Integer> getIdList(); // Return a list with all the primary keys of the table Area_Cantiere
    Integer getIdCantiere(Integer id_areaCantiere) throws SQLException; // Given an Area_Cantiere primary key return a Cantiere primary key of belonging
    ArrayList<AreeDBView> getDatiAree(Integer idCantiereGestito);
    ArrayList<Area_Cantiere> getAreeGestite(Integer operaioScelto, Integer cantiereLavori);

    //Update
    boolean modifyResponsabile(Integer areaCantiere, Integer idResponsabile) throws SQLException;

    //Delete
}
