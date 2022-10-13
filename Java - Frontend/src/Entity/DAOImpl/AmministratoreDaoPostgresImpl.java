package Entity.DAOImpl;

import Entity.Amministratore;
import Entity.DAO.AmministratoreDao;
import java.sql.*;
import java.time.LocalDate;

public class AmministratoreDaoPostgresImpl implements AmministratoreDao {

    private Connection connection ;
    private PreparedStatement checkAdminStatment ;
    private PreparedStatement getAdminStatement ;
    private PreparedStatement insertAmministratoreStatement ;


    public AmministratoreDaoPostgresImpl(Connection connection){
        this.connection = connection ;
        try {
            checkAdminStatment = connection.prepareStatement("SELECT * FROM amministratore where email like ?");
            getAdminStatement = connection.prepareStatement("SELECT * FROM amministratore where email like ?");
            insertAmministratoreStatement = connection.prepareStatement("Insert into amministratore(nome, cognome, sesso, data_n, codicefiscale, cellulare, cellulare2, email, pswrd, id_amministratore) VALUES (?,?,?,?,?,?,?,?,?,nextval('PK_Generator'));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkAdminByEmail(String email) throws SQLException {
        boolean result = false ;
        checkAdminStatment.setString(1,email);
        ResultSet resultSet = checkAdminStatment.executeQuery();
        if (resultSet.next()) {
            result = true ;
        }

        return result ;

    }

    @Override
    public Amministratore getAdminByEmail(String email) throws SQLException {

        Amministratore result = null;

        getAdminStatement.setString(1, email );

        ResultSet resultSet = getAdminStatement.executeQuery();

        if(resultSet.next()){
            result = new Amministratore();
            result.setNome(resultSet.getString("nome"));
            result.setCognome(resultSet.getString("cognome"));
            result.setSesso(resultSet.getString("sesso"));
            Date data_n = resultSet.getDate("data_n");
            result.setData_n(LocalDate.of( data_n.getYear() ,data_n.getMonth()+1, data_n.getDate() ));
            result.setCodiceFiscale(resultSet.getString("codicefiscale"));
            result.setCellulare(resultSet.getString("cellulare"));
            result.setCellulare2(resultSet.getString("cellulare2"));
            result.setEmail(resultSet.getString("email"));
            result.setPswrd(resultSet.getString("pswrd"));
            result.setId_amministratore(resultSet.getInt("id_amministratore"));
        }

        resultSet.close();

        return result;
    }

    @Override
    public boolean inserisciAmministratore(Amministratore admin) throws SQLException {
        insertAmministratoreStatement.setString(1, admin.getNome());
        insertAmministratoreStatement.setString(2, admin.getCognome());
        insertAmministratoreStatement.setString(3,admin.getSesso());
        insertAmministratoreStatement.setDate(4, Date.valueOf(admin.getData_n()));
        insertAmministratoreStatement.setString(5, admin.getCodiceFiscale());
        insertAmministratoreStatement.setString(6,admin.getCellulare());
        if (admin.getCellulare2()==null)
            insertAmministratoreStatement.setString(7,"Non Specificato");
        else
            insertAmministratoreStatement.setString(7, admin.getCellulare2());
        insertAmministratoreStatement.setString(8, admin.getEmail());
        insertAmministratoreStatement.setString(9,admin.getPswrd());
        if (insertAmministratoreStatement.executeUpdate()>0)
            return true ;
        else
            return false ;
    }

}
