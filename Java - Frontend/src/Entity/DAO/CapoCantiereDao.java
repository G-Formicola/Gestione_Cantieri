package Entity.DAO;

;
import Entity.CapoCantiere;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CapoCantiereDao {

    //Create
    public boolean inserisciCapoCantiere(CapoCantiere capoCantiere) throws SQLException;

    //Read
    public boolean checkCCByEmail(String email) throws SQLException;
    public CapoCantiere getCCByEmail(String email) throws SQLException;
    public ArrayList<Integer> getIdList();

    //Update

    //Delete

}
