package Entity.DAOImpl;

import Entity.CapoCantiere;
import Entity.DAO.CapoCantiereDao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CapoCantiereDaoPostgresImpl implements CapoCantiereDao {

    private Connection connection ;
    private PreparedStatement getCCStatement ;
    private PreparedStatement checkCCStatment ;
    private PreparedStatement insertCCStatement ;

    public CapoCantiereDaoPostgresImpl(Connection connection){
        this.connection = connection ;
        try {
        checkCCStatment = connection.prepareStatement("SELECT * FROM capocantiere where email like ?");
        getCCStatement = connection.prepareStatement("SELECT * FROM capocantiere where email like ?");
        insertCCStatement = connection.prepareStatement("Insert into capocantiere(nome, cognome, sesso, data_n, codicefiscale, cellulare, cellulare2, email, pswrd, id_capocantiere) VALUES (?,?,?,?,?,?,?,?,?,nextval('PK_Generator'));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkCCByEmail(String email) throws SQLException {
        boolean result = false ;
        checkCCStatment.setString(1,email);
        ResultSet resultSet = checkCCStatment.executeQuery();

        if (resultSet.next()) {
            result = true ;
        }

        return result ;

    }

    @Override
    public CapoCantiere getCCByEmail(String email) throws SQLException {
        CapoCantiere result = null;
        getCCStatement.setString(1, email );
        ResultSet resultSet = getCCStatement.executeQuery();

        if(resultSet.next()){
            result = new CapoCantiere();
            result.setNome(resultSet.getString("nome"));
            result.setCognome(resultSet.getString("cognome"));
            result.setSesso(resultSet.getString("sesso"));
            Date data_n = resultSet.getDate("data_n");
            result.setData_n(LocalDate.of(data_n.getYear(), data_n.getMonth()+1 , data_n.getDate()));
            result.setCodiceFiscale(resultSet.getString("codicefiscale"));
            result.setCellulare(resultSet.getString("cellulare"));
            result.setCellulare2(resultSet.getString("cellulare2"));
            result.setEmail(resultSet.getString("email"));
            result.setPswrd(resultSet.getString("pswrd"));
            result.setId_CapoCantiere(resultSet.getInt("id_capocantiere"));
        }

        resultSet.close();

        return result;
    }

    @Override
    public boolean inserisciCapoCantiere(CapoCantiere capoCantiere) throws SQLException {
        insertCCStatement.setString(1, capoCantiere.getNome());
        insertCCStatement.setString(2, capoCantiere.getCognome());
        insertCCStatement.setString(3,capoCantiere.getSesso());
        insertCCStatement.setDate(4, Date.valueOf(capoCantiere.getData_n()));
        insertCCStatement.setString(5, capoCantiere.getCodiceFiscale());
        insertCCStatement.setString(6,capoCantiere.getCellulare());
        if (capoCantiere.getCellulare2()==null)
            insertCCStatement.setString(7,"Non Specificato");
        else
            insertCCStatement.setString(7, capoCantiere.getCellulare2());
        insertCCStatement.setString(8, capoCantiere.getEmail());
        insertCCStatement.setString(9,capoCantiere.getPswrd());
        if (insertCCStatement.executeUpdate()>0)
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
            preparedStatement = connection.prepareStatement("Select id_capocantiere FROM capocantiere");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_capocantiere"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
