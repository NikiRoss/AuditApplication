package com.fdm04.auditApp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.fdm04.auditApp.database.AuditDAO;
import com.fdm04.auditApp.database.DBconnectionManager;
import com.fdm04.auditApp.model.Audit;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class AuditDAOTest {

    DBconnectionManager dbcmg = new DBconnectionManager("localhost:3306", "Audit_Application", "root", "efil4zaggin");

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createAudit(){

        try{
            //Need to investigate why score has to be set
            //in order for it to pass this test
            //this should open an audit with a score of 100

            Connection connection = dbcmg.getConnection();

            AuditDAO auditDAO = new AuditDAO(connection);
            Audit audit = new Audit();

            audit.setProjectName("Berelands Expansion");
            audit.setProjectManager("Maureen Ross");
            audit.setScore(100);
            auditDAO.create(audit);

            String result = audit.getProjectName();

            assertEquals(result, "Berelands Expansion");


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Test
    public void findAudit() {

        try {

            Connection connection = dbcmg.getConnection();

            AuditDAO auditDAO = new AuditDAO(connection);
            Audit audit = new Audit();

            audit = auditDAO.findById(1);

            String result = audit.getProjectManager();

            assertEquals(result, "Kevin Young");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Test
    public void deleteAudit(){

        try {
            Connection connection = dbcmg.getConnection();

            AuditDAO auditDAO = new AuditDAO(connection);
            Audit audit = new Audit();
            auditDAO.delete(4);

            String result = audit.getProjectName();

            assertEquals(result, null);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Test
    public void updateAudit(){

        try{

            Connection connection = dbcmg.getConnection();

            AuditDAO auditDAO = new AuditDAO(connection);
            Audit audit = new Audit();

            audit = auditDAO.findById(1);
            System.out.println(audit.getProjectName() + " " + audit.getProjectManager() + " " + audit.getScore());
            audit.setProjectName("Kelvinhall Refurb");
            auditDAO.update(audit);
            System.out.println(audit.getProjectName() + " " + audit.getProjectManager() + " " + audit.getScore());

            String result = audit.getProjectName();

            assertEquals(result, "Kelvinhall Refurb");


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}