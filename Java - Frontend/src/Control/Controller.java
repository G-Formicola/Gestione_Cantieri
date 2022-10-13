package Control;

import Boundary.AdminPanelView;
import Boundary.CapoCantierePanelView;
import Boundary.LoginView;
import Boundary.UtilityViews.*;
import Entity.*;
import Entity.DAO.*;
import Entity.DAOImpl.*;
import Entity.DataB.DBConnection;
import Entity.ViewsEntities.AreeDBView;
import Entity.ViewsEntities.ReportDBView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    private static Controller uniqueInstance = null ;
    private DBConnection dbConnection = null;
    private final Stage theStage;

    // Riferimenti alle 3 view principali
    private LoginView logInView;
    private AdminPanelView adminPanelView;
    private CapoCantierePanelView capoCantierePanelView;

    private Controller(Stage theStage) {
        this.theStage = theStage;
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al DataBase");
            e.printStackTrace();
        }
    }

    // Metodi per aprire views principali
    public void openLoginPage() {

        logInView = new LoginView();
        try {

            logInView.start(theStage);

        } catch (IOException e) {
            System.err.println("Impossibile caricare Log In Page");
            e.printStackTrace();
        }

    }
    private void openCapoCantierePanel(Integer idCC) {
        capoCantierePanelView = new CapoCantierePanelView();
        try {

            capoCantierePanelView.start(theStage, idCC);

        }catch (IOException e){
            System.err.println("Impossibile caricare pannello CapoCantiere");
            e.printStackTrace();
        }
    }
    private void openAdminPanel(Integer idAdmin) {
        adminPanelView = new AdminPanelView();
        try {

            adminPanelView.start(theStage, idAdmin);

        }catch (IOException e){
            System.err.println("Impossibile caricare pannello Amministrativo");
            e.printStackTrace();
        }
    }

    // General logic (può essere utilizzata da qualsiasi pannello)
    public void eseguiLogout() {
        theStage.hide();
        openLoginPage();
    }

    //Login Page logic
    public void effettuaLogIn(String username, String password) {

        if ( username.length() > 0 && password.length() > 0 )
        {
            try {

                AmministratoreDao adminDao = new AmministratoreDaoPostgresImpl(dbConnection.getConnection());

                if(adminDao.checkAdminByEmail(username))
                {
                    Amministratore admin = adminDao.getAdminByEmail(username);
                    if (password.equals(admin.getPswrd()))
                    {
                        logInView.hide();
                        openAdminPanel(admin.getId_amministratore());
                    }
                    else
                    {
                        logInView.failedLogIn("Wrong password !");
                    }
                }
                else
                {

                    CapoCantiereDao ccDao = new CapoCantiereDaoPostgresImpl(dbConnection.getConnection());

                    if(ccDao.checkCCByEmail(username))
                    {
                        CapoCantiere capoCantiere = ccDao.getCCByEmail(username);
                        if (password.equals(capoCantiere.getPswrd()))
                        {
                            logInView.hide();
                            openCapoCantierePanel(capoCantiere.getId_CapoCantiere());
                        }
                        else
                        {
                            logInView.failedLogIn("Wrong password !");
                        }
                    }
                    else
                    {
                        logInView.failedLogIn("Credentials not found !");
                    }

                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            if (username.length() > 0)
            {
                    logInView.failedLogIn("Login Failed : Password field is empty !");
            }
            else
            {
                if (password.length() > 0)
                {
                    logInView.failedLogIn("Login Failed : Username field is empty !");
                }
                else
                {
                    logInView.failedLogIn("Username and Password fields are empty !");
                }
            }
        }

    }

    //Admin Page logic
    public void openInsertAdminView() throws IOException {
        InsertAdminView insertAdminView = InsertAdminView.getInstance(theStage);
        insertAdminView.show();
    }
    public void openInsertCapocantiereView() throws IOException {
        InsertCapocantiereView insertCapocantiereView = InsertCapocantiereView.getInstance(theStage);
        insertCapocantiereView.show();
    }
    public void openInsertOperaioView() throws IOException {
        InsertOperaioView insertOperaioView = InsertOperaioView.getInstance(theStage);
        insertOperaioView.show();
    }
    public void openInsertCantiereView(Integer idAdmin) throws IOException {
            InsertCantiereView insertCantiereView = InsertCantiereView.getInstance(theStage, idAdmin);
            insertCantiereView.show();
    }
    public void openInsertSensoreView() throws IOException {
        InsertSensoreView insertSensoreView = InsertSensoreView.getInstance(theStage);
        insertSensoreView.show();
    }
    public void openInsertRuoloView() throws IOException {
        InsertRuoloView insertRuoloView = InsertRuoloView.getInstance(theStage);
        insertRuoloView.show();
    }
    public void openInsertAreaCantiereView() throws IOException {
        InsertAreaCantiereView insertAreaCantiereView = InsertAreaCantiereView.getInstance(theStage);
        insertAreaCantiereView.show();
    }
    public void openInsertMontaggioSensoreView() throws IOException {
        InsertMontaggioSensoreView insertMontaggioSensoreView = InsertMontaggioSensoreView.getInstance(theStage);
        insertMontaggioSensoreView.show();
    }
    public void openInsertReportSensoreView() throws IOException {
        InsertReportSensoreView insertReportSensoreView = InsertReportSensoreView.getInstance(theStage);
        insertReportSensoreView.show();
    }
    public void openModifySogliaView() throws IOException {
        ModifySogliaView modifySogliaView = ModifySogliaView.getInstance(theStage);
        modifySogliaView.show();
    }
    public void openModifyGestioneView() throws IOException {
        ModifyGestioneView modifyGestioneView = ModifyGestioneView.getInstance(theStage);
        modifyGestioneView.show();
    }
    public void openModifyMansioneView() throws IOException {
        ModifyMansioneView modifyMansioneView = new ModifyMansioneView();
        modifyMansioneView.show();
    }
    public void openModifyMasioneView(Integer idRuolo) throws IOException {
        ModifyMansioneView modifyMansioneView = new ModifyMansioneView(idRuolo);
        modifyMansioneView.show();
    }
    public void openModifyRespView() throws IOException {
        ModifyRespView modifyRespView = ModifyRespView.getInstance(theStage);
        modifyRespView.show();
    }
    public void openReadReportView() throws IOException {
        ReadReportView readReportView = ReadReportView.getInstance(theStage);
        readReportView.show();
    }
    public void openReadStatAreeView(Integer idCantiereGestito) throws IOException {
        ReadStatAreeView readStatAreeView = ReadStatAreeView.getInstance(theStage);
        readStatAreeView.show(idCantiereGestito);
    }
    public void openReadStatCantView(Integer idCantiereGestito) throws IOException {
        ReadStatCantView readStatCantView = ReadStatCantView.getInstance(theStage);
        readStatCantView.show(idCantiereGestito);
    }
    public void openReadStatView(String nomeCantiere) throws IOException {
        ReadStatView readStatView = ReadStatView.getInstance(theStage);
        readStatView.show(nomeCantiere);
    }
    public void openReadStatView(Integer operaioScelto, Integer cantiereLavori) throws IOException {
        ReadInfoOperaioView readInfoOperaioView = ReadInfoOperaioView.getInstance(theStage);
        readInfoOperaioView.show(operaioScelto, cantiereLavori);
    }


    // DATABASE LOGIC

    //Insert
    public boolean insertAdminIntoDB(String nome, String cognome, String email, String pswd, String codiceF, String cell1, String cell2, LocalDate birthDate, String sex) {
        Amministratore amministratore = new Amministratore(nome, cognome, sex, birthDate, codiceF, cell1, cell2, email, pswd);
        AmministratoreDao amministratoreDao = new AmministratoreDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = amministratoreDao.inserisciAmministratore(amministratore);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result;
    }
    public boolean insertCapocantiereIntoDB(String nome, String cognome, String email, String pswd, String codiceF, String cell1, String cell2, LocalDate birthDate, String sex) {
        CapoCantiere capoCantiere = new CapoCantiere(nome, cognome, sex, birthDate, codiceF, cell1, cell2, email, pswd);
        CapoCantiereDao capoCantiereDao = new CapoCantiereDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = capoCantiereDao.inserisciCapoCantiere(capoCantiere);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result;
    }
    public boolean insertOperaioIntoDB(String nome, String cognome, String codiceF, String cell1, String cell2, LocalDate birthDate, String sex) {
        Operaio operaio = new Operaio(nome, cognome, sex, birthDate, codiceF, cell1, cell2);
        OperaioDao operaioDao = new OperaioDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = operaioDao.inserisciOperaio(operaio);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result;
    }
    public boolean insertCantiereIntoDB (String titolo, String descrizione, LocalDate start, LocalDate finish, String localita, Integer idAmm, Integer idCC){
        Cantiere cantiere = new Cantiere(titolo, descrizione, start, finish, localita, idAmm, idCC);
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = cantiereDao.inserisciCantiere(cantiere);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result ;
    }
    public boolean insertSensoreIntoDB(String sensType, Integer soglia) {
        Sensore sensore = new Sensore(sensType,soglia);
        SensoreDao sensoreDao = new SensoreDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = sensoreDao.inserisciSensore(sensore);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result;
    }
    public boolean insertRuoloIntoDB(String mansione, LocalDate startDate, Integer id_operaio, Integer id_cantiere, String descrizione) {
        Ruolo ruolo = new Ruolo(mansione, startDate, id_operaio, id_cantiere, descrizione);
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = ruoloDao.inserisciRuolo(ruolo);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result ;
    }
    public boolean insertAreaCantiereIntoDB(Integer id_Cantiere, Integer id_Responsabile, String nomeZona, String descrizione) {
        Area_Cantiere area_cantiere = new Area_Cantiere(nomeZona, descrizione, id_Responsabile, id_Cantiere);
        Area_CantiereDao area_CantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = area_CantiereDao.inserisciAreaCantiere(area_cantiere);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result ;
    }
    public boolean insertMontaggioSensoreIntoDB(Integer idAreaCantiere, Integer idOperatore, Integer idSensore, LocalDate dataMontaggio) {
        Montaggio_Sensore montaggio_sensore = new Montaggio_Sensore(dataMontaggio,idSensore,idAreaCantiere,idOperatore);
        Montaggio_SensoreDao montaggio_sensoreDao = new Montaggio_SensoreDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = montaggio_sensoreDao.inserisciMontaggioSensore(montaggio_sensore);
        } catch (SQLException e){
            e.printStackTrace();
            result = false ;
        }
        return result ;
    }
    public boolean insertReportSensoreIntoDB(Integer idArea, Integer idSensore, LocalDate dataRilievo, Integer ore, Integer minuti, Integer secondi, Integer rilievo) {
        Time orario = new Time(ore,minuti,secondi);
        Report_Sensore report_sensore = new Report_Sensore(dataRilievo,orario,rilievo,idSensore,idArea);
        Report_SensoreDao reportSensoreDao = new Report_SensoreDaoPostgresImpl(dbConnection.getConnection());
        boolean result ;
        try {
            result = reportSensoreDao.inserisciReportSensore(report_sensore);
        } catch (SQLException e){
            e.printStackTrace();
            result = false ;
        }
        return result ;
    }
    //Get
    public ArrayList<Integer> getListaIdCapoCantieri() {
        ArrayList<Integer> ret ;
        CapoCantiereDao ccDao = new CapoCantiereDaoPostgresImpl(dbConnection.getConnection());
        ret = ccDao.getIdList();
        return ret;
    }
    public ArrayList<Integer> getListaIdOperai() {
        // Restituisce la lista di tutti gli ID degli operai presenti all'interno del DB
        ArrayList<Integer> ret ;
        OperaioDao operaioDao = new OperaioDaoPostgresImpl(dbConnection.getConnection());
        ret = operaioDao.getIdList();
        return ret;
    }
    public ArrayList<Integer> getListaIdRuolo() {
        ArrayList<Integer> ret ;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        ret = ruoloDao.getIdRuoliList();
        return ret;
    }
    public ArrayList<Integer> getListaIdOperaiConRuolo(Integer id_Cantiere) {
        // Restituisce la lista di tutti gli ID degli operai che hanno un ruolo all'interno del cantiere passato in Input
        ArrayList<Integer> ret ;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        ret = ruoloDao.getIdRuoliList(id_Cantiere);
        return ret;
    }
    public ArrayList<Integer> getListaIdCantieriInCorso() {
        ArrayList<Integer> ret ;
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        ret = cantiereDao.getIdList();
        return ret;
    }
    public ArrayList<Integer> getListaIdAreaCantiere() {
        ArrayList<Integer> ret ;
        Area_CantiereDao areaCantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        ret = areaCantiereDao.getIdList();
        return ret;
    }
    public ArrayList<Integer> getListaIdOperatori(Integer id_areaCantiere) {
        ArrayList<Integer> ret ;
        Integer id_Cantiere = null;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        id_Cantiere = getIdCantiere(id_areaCantiere);
        ret = ruoloDao.getOperatoriList(id_Cantiere);

        return ret;
    }
    public ArrayList<Integer> getListaIdSensoriArea() {
        ArrayList<Integer> ret ;
        SensoreDao sensoreDao = new SensoreDaoPostgresImpl(dbConnection.getConnection());
        ret = sensoreDao.getIdList();
        return ret;
    }
    public ArrayList<Integer> getListaIdMontaggiArea(Integer id_areaCantiere) {
        ArrayList<Integer> ret ;
        Montaggio_SensoreDao montaggioSensoreDao = new Montaggio_SensoreDaoPostgresImpl(dbConnection.getConnection());
        ret = montaggioSensoreDao.getIdList(id_areaCantiere);
        return ret;
    }
    public ArrayList<ReportDBView> getDatiReport(String sortType) {
        ArrayList<ReportDBView> ret ;
        Report_SensoreDao reportSensoreDao = new Report_SensoreDaoPostgresImpl(dbConnection.getConnection());
        ret = reportSensoreDao.getDatiReport(sortType);
        return ret ;
    }
    public ArrayList<AreeDBView> getDatiAreeCantiere(Integer idCantiereGestito) {
        ArrayList<AreeDBView> ret ;
        Area_CantiereDao area_cantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        ret = area_cantiereDao.getDatiAree(idCantiereGestito);
        return ret ;
    }
    public ArrayList<String> getListaNomiCantieriInCorso() {
        ArrayList<String> ret ;
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        ret = cantiereDao.getNomiList();
        return ret ;
    }
    public ArrayList<String> getListaAreeGestite(Integer operaioScelto, Integer cantiereLavori) {
        ArrayList<String> result = new ArrayList<>();
        Area_CantiereDao area_cantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        ArrayList<Area_Cantiere> areeCantiereGestite = area_cantiereDao.getAreeGestite(operaioScelto, cantiereLavori);
        for ( Area_Cantiere area : areeCantiereGestite ) {
            result.add(area.toString());
        }
        return result;
    }
    public Integer getIdCantiere (Integer id_areaCantiere) {
        Integer id_Cantiere = null ;
        Area_CantiereDao areaCantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        try {
            id_Cantiere = areaCantiereDao.getIdCantiere(id_areaCantiere);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_Cantiere;
    }
    public Cantiere getCantiereByNome (String nomeCantiere){
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        Cantiere cantiere = cantiereDao.getCantiereByTitolo(nomeCantiere);
        return cantiere ;
    }
    public Cantiere getCantiereByResponsabile(Integer idCapoCantiere){
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        return cantiereDao.getCantiereByResp(idCapoCantiere);
    }
    public Cantiere getCantiereById(Integer idCantiere) {
        Cantiere cantiere ;
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        cantiere = cantiereDao.getCantiereById(idCantiere);
        return cantiere;
    }
    public double getNumCarpentieri(Integer id_cant) {
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        return ruoloDao.getNumCarpentieri(id_cant);
    }
    public double getNumOperatori(Integer id_cant) {
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        return ruoloDao.getNumOperatori(id_cant);
    }
    public double getNumIng(Integer id_cant) {
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        return ruoloDao.getNumIng(id_cant);
    }
    public double getNumElett(Integer id_cant) {
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        return ruoloDao.getNumElett(id_cant);
    }
    public Integer getNumSensori(Integer id_cant) {
        SensoreDao sensoreDao = new SensoreDaoPostgresImpl(dbConnection.getConnection());
        return sensoreDao.getNumSensori(id_cant);
    }
    public Integer getNumReport(Integer id_cant) {
        Report_SensoreDao reportSensoreDao = new Report_SensoreDaoPostgresImpl(dbConnection.getConnection());
        return reportSensoreDao.getNumReport(id_cant);
    }
    public Operaio getOperaioById(Integer operaioScelto) {
        Operaio ret ;
        OperaioDao operaioDao = new OperaioDaoPostgresImpl(dbConnection.getConnection());
        ret = operaioDao.getOperaioById(operaioScelto);
        return ret;
    }
    public String getMansioneOperaio(Integer operaioScelto, Integer cantiereLavori) {
        String result ;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        result = ruoloDao.getMansione(operaioScelto,cantiereLavori);
        return result;
    }
    public Integer getIdRuolo(Integer operaioScelto, Integer cantiereLavori) {
        Integer result ;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        result = ruoloDao.getIdRuolo(operaioScelto,cantiereLavori);
        return result ;
    }
    // Modify
    public boolean modifySoglia(Integer idSensore, Integer newSoglia) {
        boolean result ;
        SensoreDao sensoreDao = new SensoreDaoPostgresImpl(dbConnection.getConnection());
        try {
            result = sensoreDao.modifySoglia(idSensore,newSoglia);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result ;
    }
    public boolean modifyGestione(Integer idCantiere, Integer idCapoCantiere) {
        boolean result ;
        CantiereDao cantiereDao = new CantiereDaoPostgresImpl(dbConnection.getConnection());
        try {
            result = cantiereDao.modifyGestione(idCantiere, idCapoCantiere);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false ;
        }
        return result;
    }
    public boolean modifyMansione(Integer idRuolo, String Mansione ) {
        boolean result ;
        RuoloDao ruoloDao = new RuoloDaoPostgresImpl(dbConnection.getConnection());
        try {
            result = ruoloDao.modifyMansione(idRuolo, Mansione);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result ;
    }
    public boolean modifyResponsability(Integer areaCantiere, Integer idResponsabile) {
        boolean result ;
        Area_CantiereDao area_cantiereDao = new Area_CantiereDaoPostgresImpl(dbConnection.getConnection());
        try {
            result = area_cantiereDao.modifyResponsabile(areaCantiere, idResponsabile);
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result ;
    }


    // Metodi per ottenere istanza singleton
    public static Controller getInstance(){

        return uniqueInstance ;

    }
    public static Controller getInstance(Stage stage){

        if (uniqueInstance == null){
            uniqueInstance = new Controller(stage);
        }

        return uniqueInstance;
    }



}
