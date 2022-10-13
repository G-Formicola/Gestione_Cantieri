package Entity.DAOImpl;

import Entity.DAO.OperaioDao;
import Entity.Operaio;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OperaioDaoPostgresImpl implements OperaioDao {

    private Connection connection ;
    private PreparedStatement insertOperaioStatement ;

    public OperaioDaoPostgresImpl(Connection connection){
        this.connection = connection ;
        try {
            insertOperaioStatement = connection.prepareStatement("Insert into operaio(nome, cognome, sesso, data_n, codicefiscale, cellulare, cellulare2, id_operaio) VALUES (?,?,?,?,?,?,?,nextval('PK_Generator'));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciOperaio(Operaio operaio) throws SQLException {
        insertOperaioStatement.setString(1, operaio.getNome());
        insertOperaioStatement.setString(2, operaio.getCognome());
        insertOperaioStatement.setString(3,operaio.getSesso());
        insertOperaioStatement.setDate(4, Date.valueOf(operaio.getData_n()));
        insertOperaioStatement.setString(5, operaio.getCodiceFiscale());
        insertOperaioStatement.setString(6,operaio.getCellulare());
        if (operaio.getCellulare2()==null)
            insertOperaioStatement.setString(7,"Non Specificato");
        else
            insertOperaioStatement.setString(7, operaio.getCellulare2());
        if (insertOperaioStatement.executeUpdate()>0)
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
            preparedStatement = connection.prepareStatement("Select id_operaio FROM operaio");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_operaio"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Operaio getOperaioById(Integer operaioScelto) {
        Operaio result = new Operaio() ;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement("Select * FROM operaio WHERE id_operaio = ?");
            preparedStatement.setInt(1,operaioScelto);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                result.setId_Operaio(resultSet.getInt("id_operaio"));
                result.setNome(resultSet.getString("nome"));
                result.setCognome(resultSet.getString("cognome"));
                result.setSesso(resultSet.getString("sesso"));
                Date data_n = resultSet.getDate("data_n");
                result.setData_n(LocalDate.of(data_n.getYear()+1900, data_n.getMonth()+1, data_n.getDate()));
                result.setCodiceFiscale(resultSet.getString("codicefiscale"));
                result.setCellulare(resultSet.getString("cellulare"));
                String cellulare2 = resultSet.getString("cellulare2");
                if(cellulare2!=null) {
                    if (cellulare2.length() > 0)
                        result.setCellulare2(cellulare2);
                    else
                        result.setCellulare2("Non Specificato");
                }
                else
                {
                    result.setCellulare2("Non Specificato");
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
