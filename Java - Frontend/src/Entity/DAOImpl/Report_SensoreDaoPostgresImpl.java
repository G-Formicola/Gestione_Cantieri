package Entity.DAOImpl;

import Entity.DAO.Report_SensoreDao;
import Entity.Report_Sensore;
import Entity.ViewsEntities.ReportDBView;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Report_SensoreDaoPostgresImpl implements Report_SensoreDao {

    private Connection connection ;
    private PreparedStatement insertReportStatement ;

    public Report_SensoreDaoPostgresImpl(Connection connection){
        this.connection = connection ;
        try {
            insertReportStatement = connection.prepareStatement("Insert into report_sensore(id_report_sensore, data_rilievo, ore_rilievo, rilievo, id_sensmontato, id_area_rilievo) VALUES (nextval('PK_Generator'),?,?,?,?,?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inserisciReportSensore(Report_Sensore report_sensore) throws SQLException {
        insertReportStatement.setDate(1, Date.valueOf(report_sensore.getDataRilievo()));
        insertReportStatement.setTime(2, report_sensore.getOreRilievo());
        insertReportStatement.setInt(3, report_sensore.getValoreRilevato());
        insertReportStatement.setInt(4, report_sensore.getId_Sensore());
        insertReportStatement.setInt(5, report_sensore.getId_Area_Rilievo());
        if (insertReportStatement.executeUpdate()>0)
            return true ;
        else
            return false ;
    }

    @Override
    public ArrayList<ReportDBView> getDatiReport(String sortType) {
        ArrayList<ReportDBView> ret = new ArrayList<ReportDBView>();
        PreparedStatement getDatiReportStatement ;
        String sqlQuery = "SELECT report_sensore.id_report_sensore, sensore.tipo, sensore.id_sensore, sensore.soglia, area_cantiere.nome_zona, area_cantiere.id_area_cantiere, report_sensore.data_rilievo, report_sensore.ore_rilievo, report_sensore.rilievo FROM sensore JOIN montaggio_sensore ON sensore.id_sensore = montaggio_sensore.id_sensore JOIN area_cantiere ON montaggio_sensore.id_area = area_cantiere.id_area_cantiere JOIN report_sensore ON (report_sensore.id_sensmontato = montaggio_sensore.id_montaggio AND report_sensore.id_area_rilievo = area_cantiere.id_area_cantiere) ORDER BY "+sortType+";";
        ResultSet resultSet ;
        try {
            getDatiReportStatement = connection.prepareStatement(sqlQuery);
            resultSet = getDatiReportStatement.executeQuery();
            while (resultSet.next())
            {
                ReportDBView reportDBViewRow = new ReportDBView();

                reportDBViewRow.setIdReport(resultSet.getInt("id_report_sensore"));
                reportDBViewRow.setTipo(resultSet.getString("tipo"));
                reportDBViewRow.setIdSensore(resultSet.getInt("id_sensore"));
                reportDBViewRow.setSoglia(resultSet.getInt("soglia"));
                reportDBViewRow.setNomeZona(resultSet.getString("nome_zona"));
                reportDBViewRow.setIdZona(resultSet.getInt("id_area_cantiere"));
                Date data = resultSet.getDate("data_rilievo");
                reportDBViewRow.setDataRilievo(LocalDate.of(data.getYear()+1900, data.getMonth()+1, data.getDate()));
                reportDBViewRow.setOreRilievo(resultSet.getTime("ore_rilievo"));
                reportDBViewRow.setRilievo(resultSet.getInt("rilievo"));

                ret.add(reportDBViewRow);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public Integer getNumReport(Integer id_cant) {
        Integer ret = 0 ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM report_sensore JOIN area_cantiere ON report_sensore.id_area_rilievo = area_cantiere.id_area_cantiere JOIN cantiere ON area_cantiere.id_cantiereappartenenza = cantiere.id_cantiere WHERE cantiere.id_cantiere = ? ;");
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
