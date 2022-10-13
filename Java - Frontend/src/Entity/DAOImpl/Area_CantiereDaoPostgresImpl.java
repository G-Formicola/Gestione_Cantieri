package Entity.DAOImpl;

import Entity.Area_Cantiere;
import Entity.DAO.Area_CantiereDao;
import Entity.ViewsEntities.AreeDBView;
import java.sql.*;
import java.util.ArrayList;

public class Area_CantiereDaoPostgresImpl implements Area_CantiereDao {

    private Connection connection ;
    private PreparedStatement insertAreaCantiereStatement ;
    private PreparedStatement getListaOperatoriStatement ;

    public Area_CantiereDaoPostgresImpl(Connection connection) {
        this.connection = connection;
        try {
            insertAreaCantiereStatement = connection.prepareStatement("Insert into area_cantiere(id_area_cantiere, nome_zona, descrizione, id_gestore, id_cantiereappartenenza) VALUES (nextval('PK_Generator'),?,?,?,?);");
            getListaOperatoriStatement = connection.prepareStatement("SELECT id_cantiereappartenenza FROM area_cantiere WHERE id_area_cantiere = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciAreaCantiere(Area_Cantiere area_cantiere) throws SQLException {
        insertAreaCantiereStatement.setString(1,area_cantiere.getNomeZona());
        insertAreaCantiereStatement.setString(2,area_cantiere.getDescrizione());
        insertAreaCantiereStatement.setInt(3,area_cantiere.getId_Lavoratore());
        insertAreaCantiereStatement.setInt(4,area_cantiere.getId_CantiereLavori());
        if (insertAreaCantiereStatement.executeUpdate()>0)
            return true ;
        else
            return false ;
    }

    @Override
    public ArrayList<Integer> getIdList() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select id_area_cantiere FROM area_cantiere");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_area_cantiere"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer getIdCantiere(Integer id_areaCantiere) throws SQLException {
        Integer result = null;
        getListaOperatoriStatement.setInt(1,id_areaCantiere);
        ResultSet resultSet ;
        resultSet = getListaOperatoriStatement.executeQuery();
        while (resultSet.next())
        {
            result = resultSet.getInt("id_cantiereappartenenza");
        }
        resultSet.close();

        return result;

    }

    @Override
    public ArrayList<AreeDBView> getDatiAree(Integer idCantiereGestito) {
        ArrayList<AreeDBView> ret = new ArrayList<AreeDBView>();
        PreparedStatement areeReportStatement ;

        ResultSet resultSet ;
        try {
            areeReportStatement = connection.prepareStatement("SELECT id_area_cantiere, nome_zona, descrizione, id_gestore FROM area_cantiere WHERE id_cantiereappartenenza = ?;");
            areeReportStatement.setInt(1,idCantiereGestito);
            resultSet = areeReportStatement.executeQuery();
            while (resultSet.next())
            {
                AreeDBView areeDBViewRow = new AreeDBView();

                areeDBViewRow.setIdArea(resultSet.getInt("id_area_cantiere"));
                areeDBViewRow.setNomeZona(resultSet.getString("nome_zona"));
                areeDBViewRow.setIdResponsabile(resultSet.getInt("id_gestore"));
                String descrizione = resultSet.getString("descrizione");
                if (descrizione != null && descrizione.length()>0)
                    areeDBViewRow.setDescrizione(descrizione);
                else
                    areeDBViewRow.setDescrizione("Nessuna Descrizione Inserita");

                ret.add(areeDBViewRow);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public ArrayList<Area_Cantiere> getAreeGestite(Integer operaioScelto, Integer cantiereLavori) {
        ArrayList<Area_Cantiere> result = new ArrayList<Area_Cantiere>() ;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement("SELECT nome_zona, descrizione, id_area_cantiere FROM area_cantiere WHERE id_cantiereappartenenza = ? AND id_gestore = ?");
            preparedStatement.setInt(1,cantiereLavori);
            preparedStatement.setInt(2,operaioScelto);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Area_Cantiere area_cantiere = new Area_Cantiere();
                area_cantiere.setId_Area_Cantiere(resultSet.getInt("id_area_cantiere"));
                area_cantiere.setDescrizione(resultSet.getString("descrizione"));
                area_cantiere.setNomeZona(resultSet.getString("nome_zona"));

                result.add(area_cantiere);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean modifyResponsabile(Integer areaCantiere, Integer idResponsabile) throws SQLException {
        boolean result ;
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("UPDATE area_cantiere SET id_gestore = ? WHERE id_area_cantiere = ?");
        preparedStatement.setInt(1, idResponsabile);
        preparedStatement.setInt(2, areaCantiere);
        int changedRows = preparedStatement.executeUpdate();
        if ( changedRows > 0 && changedRows < 2)
            result = true ;
        else
            result = false ;
        return result;
    }
}

