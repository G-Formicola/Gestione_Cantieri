package Entity.DAOImpl;

import Entity.DAO.RuoloDao;
import Entity.Ruolo;

import java.sql.*;
import java.util.ArrayList;

public class RuoloDaoPostgresImpl implements RuoloDao {

    private Connection connection ;
    private PreparedStatement insertRuoloStatement ;
    private PreparedStatement getOperatoriStatement ;

    public RuoloDaoPostgresImpl(Connection connection) {
        this.connection = connection ;
        try {
            insertRuoloStatement = connection.prepareStatement("Insert into ruolo(id_ruolo, mansione, descrizione, data_inizio, id_lavoratore, id_cantierelavori) VALUES (nextval('PK_Generator'),?,?,?,?,?)");
            getOperatoriStatement = connection.prepareStatement("SELECT id_lavoratore FROM ruolo WHERE (id_cantierelavori = ? AND mansione LIKE 'Operatore')");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciRuolo(Ruolo ruolo) throws SQLException {
        insertRuoloStatement.setString(1,ruolo.getMansione());
        insertRuoloStatement.setString(2,ruolo.getDescrizione());
        insertRuoloStatement.setDate(3, Date.valueOf(ruolo.getDataInizio()));
        insertRuoloStatement.setInt(4,ruolo.getId_Lavoratore());
        insertRuoloStatement.setInt(5,ruolo.getId_CantiereLavori());

        if (insertRuoloStatement.executeUpdate()>0)
            return true ;
        else
            return false ;
    }

    @Override
    public ArrayList<Integer> getIdRuoliList(Integer id_cantiere) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * FROM ruolo WHERE id_cantierelavori = ?");
            preparedStatement.setInt(1,id_cantiere);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_lavoratore"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Integer> getIdRuoliList() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select id_ruolo FROM ruolo");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_ruolo"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Integer> getOperatoriList(Integer id_cantiere) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        ResultSet resultSet ;
        try {
            if (id_cantiere != null) {
                getOperatoriStatement.setInt(1, id_cantiere);
                resultSet = getOperatoriStatement.executeQuery();
                while (resultSet.next()) {
                    result.add(resultSet.getInt("id_lavoratore"));
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int getNumCarpentieri(Integer id_cant) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT count(*) FROM ruolo WHERE id_cantierelavori = ? AND mansione LIKE 'Carpentiere' ;");
            preparedStatement.setInt(1,id_cant);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                ret = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getNumOperatori(Integer id_cant) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT count(*) FROM ruolo WHERE id_cantierelavori = ? AND mansione LIKE 'Operatore' ;");
            preparedStatement.setInt(1,id_cant);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                ret = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getNumIng(Integer id_cant) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT count(*) FROM ruolo WHERE id_cantierelavori = ? AND mansione LIKE 'Ingegnere' ;");
            preparedStatement.setInt(1,id_cant);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                ret = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getNumElett(Integer id_cant) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT count(*) FROM ruolo WHERE id_cantierelavori = ? AND mansione LIKE 'Elettricista' ;");
            preparedStatement.setInt(1,id_cant);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                ret = resultSet.getInt(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public String getMansione(Integer operaioScelto, Integer cantiereLavori) {
        String ret = "" ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT mansione FROM ruolo WHERE id_cantierelavori = ? AND id_lavoratore = ? ;");
            preparedStatement.setInt(1,cantiereLavori);
            preparedStatement.setInt(2,operaioScelto);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                ret = resultSet.getString("mansione");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret ;
    }

    @Override
    public Integer getIdRuolo(Integer operaioScelto, Integer cantiereLavori) {
        Integer ret = 0 ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id_ruolo FROM ruolo WHERE id_cantierelavori = ? AND id_lavoratore = ? ;");
            preparedStatement.setInt(1,cantiereLavori);
            preparedStatement.setInt(2,operaioScelto);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                ret = resultSet.getInt("id_ruolo");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret ;
    }

    @Override
    public boolean modifyMansione(Integer idRuolo, String mansione) throws SQLException {
        boolean result ;
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("UPDATE ruolo SET mansione = ? WHERE id_ruolo = ?");
        preparedStatement.setString(1, mansione);
        preparedStatement.setInt(2, idRuolo);
        int changedRows = preparedStatement.executeUpdate();
        if ( changedRows > 0 && changedRows < 2)
            result = true ;
        else
            result = false ;
        return result;
    }
}
