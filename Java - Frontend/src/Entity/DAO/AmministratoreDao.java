package Entity.DAO;

import Entity.Amministratore;
import java.sql.SQLException;

public interface AmministratoreDao {

    //Create
    public boolean inserisciAmministratore(Amministratore admin) throws SQLException;

    //Read
    public boolean checkAdminByEmail (String email) throws SQLException;
    public Amministratore getAdminByEmail (String email) throws SQLException;

    //Update

    //Delete
}
