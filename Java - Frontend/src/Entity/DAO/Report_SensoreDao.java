package Entity.DAO;

import Entity.Report_Sensore;
import Entity.ViewsEntities.ReportDBView;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Report_SensoreDao {
    //Create
    boolean inserisciReportSensore(Report_Sensore report_sensore) throws SQLException;
    //Read
    ArrayList<ReportDBView> getDatiReport(String sortType);

    Integer getNumReport(Integer id_cant);
    //Update
    //Delete
}
