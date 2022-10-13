package Entity.DAOImpl;

import Entity.Cantiere;
import Entity.DAO.CantiereDao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CantiereDaoPostgresImpl implements CantiereDao {

    private Connection connection;
    private PreparedStatement insertCantiereStatement;

    public CantiereDaoPostgresImpl (Connection connection){
        this.connection = connection ;
        try {
            insertCantiereStatement = connection.prepareStatement("Insert into cantiere(titolo, descrizione, data_inizio, data_fine, localita, id_cantiere, id_creatore, id_responsabile) VALUES (?,?,?,?,?,nextval('PK_Generator'),?,?);");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inizializzazione della query : "+e.getMessage());
        }
    }

    @Override
    public boolean inserisciCantiere(Cantiere cantiere) throws SQLException {
        insertCantiereStatement.setString(1, cantiere.getTitolo());
        insertCantiereStatement.setString(2, cantiere.getDescrizione());
        insertCantiereStatement.setDate(3, Date.valueOf(cantiere.getStartDate()));
        if (cantiere.getFinishDate()==null)
            insertCantiereStatement.setDate(4, null);
        else
            insertCantiereStatement.setDate(4, Date.valueOf(cantiere.getFinishDate()));
        insertCantiereStatement.setString(5, cantiere.getLocalita());
        insertCantiereStatement.setInt(6,cantiere.getIdAdmin());
        insertCantiereStatement.setInt(7, cantiere.getIdCapoC());
        if(insertCantiereStatement.executeUpdate()>0)
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<Integer> getIdList() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select id_cantiere FROM cantiere WHERE ((data_fine IS NULL) OR (data_fine > current_date)) ORDER BY id_cantiere ASC");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("id_cantiere"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<String> getNomiList() {
        ArrayList<String> result = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select titolo FROM cantiere WHERE ((data_fine IS NULL) OR (data_fine > current_date)) ORDER BY id_cantiere ASC");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.add(resultSet.getString("titolo"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Cantiere getCantiereByTitolo(String titolo) {
        Cantiere result = new Cantiere();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cantiere WHERE titolo LIKE ? ;");
            preparedStatement.setString(1,titolo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result.setTitolo(resultSet.getString("titolo"));
                result.setDescrizione(resultSet.getString("descrizione"));
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                if (data_inizio != null)
                    result.setStartDate(LocalDate.of(data_inizio.getYear()+1900,data_inizio.getMonth()+1, data_inizio.getDate() ));
                if (data_fine != null)
                    result.setFinishDate(LocalDate.of(data_fine.getYear()+1900,data_fine.getMonth()+1,data_fine.getDate()));
                result.setLocalita(resultSet.getString("localita"));
                result.setIdCantiere(resultSet.getInt("id_cantiere"));
                result.setIdAdmin(resultSet.getInt("id_creatore"));
                result.setIdCapoC(resultSet.getInt("id_responsabile"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Cantiere getCantiereByResp(Integer idCapoCantiere) {
        Cantiere result = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cantiere WHERE id_responsabile = ? AND (data_fine > CURRENT_DATE OR data_fine IS NULL);");
            preparedStatement.setInt(1,idCapoCantiere);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                result = new Cantiere();
                result.setTitolo(resultSet.getString("titolo"));
                result.setDescrizione(resultSet.getString("descrizione"));
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                if (data_inizio != null)
                    result.setStartDate(LocalDate.of(data_inizio.getYear()+1900,data_inizio.getMonth()+1, data_inizio.getDate() ));
                if (data_fine != null)
                    result.setFinishDate(LocalDate.of(data_fine.getYear()+1900,data_fine.getMonth()+1,data_fine.getDate()));
                result.setLocalita(resultSet.getString("localita"));
                result.setIdCantiere(resultSet.getInt("id_cantiere"));
                result.setIdAdmin(resultSet.getInt("id_creatore"));
                result.setIdCapoC(resultSet.getInt("id_responsabile"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Cantiere getCantiereById(Integer idCantiere) {
        Cantiere cantiere = null;
        PreparedStatement preparedStatement ;
        ResultSet resultSet ;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cantiere WHERE id_cantiere = ? ;");
            preparedStatement.setInt(1,idCantiere);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                cantiere = new Cantiere();
                cantiere.setIdCantiere(resultSet.getInt("id_cantiere"));
                cantiere.setLocalita(resultSet.getString("localita"));
                cantiere.setDescrizione(resultSet.getString("descrizione"));
                cantiere.setTitolo(resultSet.getString("titolo"));
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                if (data_inizio!=null)
                    cantiere.setStartDate(LocalDate.of(data_inizio.getYear()+1900,data_inizio.getMonth()+1,data_inizio.getDate()));
                if (data_fine!=null)
                    cantiere.setFinishDate(LocalDate.of(data_fine.getYear()+1900,data_fine.getMonth()+1,data_fine.getDate()));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantiere;
    }

    @Override
    public boolean modifyGestione(Integer idCantiere, Integer nuovoResponsabile) throws SQLException {
        boolean result ;
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("UPDATE cantiere SET id_responsabile = ? WHERE id_cantiere = ?");
        preparedStatement.setInt(1, nuovoResponsabile);
        preparedStatement.setInt(2, idCantiere);
        int changedRows = preparedStatement.executeUpdate();
        if ( changedRows > 0 && changedRows < 2)
            result = true ;
        else
            result = false ;
        return result;
    }

}
