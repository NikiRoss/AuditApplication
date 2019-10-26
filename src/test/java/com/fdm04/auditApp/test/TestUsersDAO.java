package com.fdm04.auditApp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import com.fdm04.auditApp.database.DBconnectionManager;
import com.fdm04.auditApp.database.UsersDAO;
import com.fdm04.auditApp.model.Users;

public class TestUsersDAO {
	
    DBconnectionManager dbcmg = new DBconnectionManager("localhost:3306", "AuditApplication", "root", "efil4zaggin");
	

	// Ask Aravind about this method. Currently requires me to "predict" the ID
	// of a newly created user in order to return their details from the DB
	@Test  
	public void create_New_User() {
		
		try {
            Connection connection = dbcmg.getConnection();
            UsersDAO dao = new UsersDAO(connection);
            Users users = new Users();
            users.setUsername("testuser_name");
            users.setPassword("test_PasSw0rD");
            dao.create(users);
            users = dao.findById(13);
            String result = users.getUsername();
            assertEquals("testuser_name", result);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}

	@Test  
	public void update_User() {
		
		try {
            Connection connection = dbcmg.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            Users users = new Users();
            users = usersDAO.findById(1);
            users.setPassword("NEWPASSWORD");
            usersDAO.update(users);
            String result = users.getPassword();
            assertEquals(result, "NEWPASSWORD");

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}
	
	@Test
    public void find_User(){

        try {
            Connection connection = dbcmg.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            Users users = new Users();
            users = usersDAO.findById(3);
            String result = users.getUsername();
            assertEquals("Ian Ross", result);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
	
	@Test
    public void delete_User(){

        try {
            Connection connection = dbcmg.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            Users users = new Users();
            usersDAO.delete(4);
            users = usersDAO.findById(4);
            String result = users.getUsername();
            assertEquals(result, null);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
	
	@Test
    public void verify_User(){

        try {
            Connection connection = dbcmg.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            boolean result = usersDAO.verifyUser("Ian Ross", "wordPass");
            assertEquals(result, true);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
