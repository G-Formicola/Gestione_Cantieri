package Entity.DAOImpl;

import Entity.DAO.SensoreDao;
import Entity.Sensore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SensoreDaoPostgresImpl implements SensoreDao {

    private Connection connection ;
    private PreparedStatement insertSensoreStatement ;

    public SensoreDaoPostgresImpl(Connection connection){
        this.connection = connection ;
        try {
            insertSensoreStatement = connection.prepareStatement("Insert into sensore(id_sensore, tipo, soglia) VALUES (nextval('PK_Generator'),?,?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciSensore(Sensore sensore) throws SQLException {
        insertSensoreStatement.setString(1,sensore.getSensType());
        insertSensoreStatement.setInt(2,sensore.getSoglia());
        if (insertSensoreStatement.executeUpdate()>0)
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
            preparedStatement = connection.prepareStatement("Select id_sensore FROM sensore");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_sensore"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean modifySoglia(Integer idSensore, Integer newSoglia) throws SQLException {
        PreparedStatement preparedStatement = null;
        boolean result ;
        preparedStatement = connection.prepareStatement("UPDATE sensore SET soglia = ? WHERE id_sensore = ?");
        preparedStatement.setInt(1, newSoglia);
        preparedStatement.setInt(2, idSensore);
        int changedRows = preparedStatement.executeUpdate();
        if ( changedRows > 0 && changedRows < 2)
            result = true ;
        else
            result = false ;
        return result;
    }

    @Override
    public Integer getNumSensori(Integer id_cant) {
        Integer ret = 0 ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM montaggio_sensore JOIN area_cantiere ON montaggio_sensore.id_area = area_cantiere.id_area_cantiere JOIN cantiere ON area_cantiere.id_cantiereappartenenza = cantiere.id_cantiere WHERE cantiere.id_cantiere = ? ;");
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

}
