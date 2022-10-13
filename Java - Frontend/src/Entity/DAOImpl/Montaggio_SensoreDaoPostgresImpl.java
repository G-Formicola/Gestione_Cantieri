package Entity.DAOImpl;

import Entity.DAO.Montaggio_SensoreDao;
import Entity.Montaggio_Sensore;

import java.sql.*;
import java.util.ArrayList;

public class Montaggio_SensoreDaoPostgresImpl implements Montaggio_SensoreDao {

    private Connection connection ;
    private PreparedStatement insertMontaggioSensoreStatement ;

    public Montaggio_SensoreDaoPostgresImpl(Connection connection) {
        this.connection = connection;
        try {
            insertMontaggioSensoreStatement = connection.prepareStatement("Insert into montaggio_sensore(id_montaggio, data_montaggio, id_sensore, id_area, id_operatore) VALUES (nextval('PK_Generator'),?,?,?,?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciMontaggioSensore(Montaggio_Sensore montaggio_sensore) throws SQLException {
        insertMontaggioSensoreStatement.setDate(1, Date.valueOf(montaggio_sensore.getDataMontaggio()));
        insertMontaggioSensoreStatement.setInt(2,montaggio_sensore.getId_Sensore());
        insertMontaggioSensoreStatement.setInt(3,montaggio_sensore.getId_Area());
        insertMontaggioSensoreStatement.setInt(4,montaggio_sensore.getId_Operatore());
        if (insertMontaggioSensoreStatement.executeUpdate()>0)
            return true ;
        else
            return false ;
    }

    @Override
    public ArrayList<Integer> getIdList(Integer id_areaCantiere) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select id_montaggio FROM montaggio_sensore WHERE id_area = ?");
            preparedStatement.setInt(1,id_areaCantiere);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_montaggio"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
