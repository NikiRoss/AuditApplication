package com.fdm04.auditApp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import com.fdm04.auditApp.database.UsersDAO;
import com.fdm04.auditApp.database.util.DBconnectionManager;
import com.fdm04.auditApp.model.Users;

public class TestUsersDAO {
	
    DBconnectionManager dbcmg = new DBconnectionManager("localhost:3306", "AuditApplication", "root", "efil4zaggin");
	
    //Random generator used for this test as usernames must be unique
	@Test  
	public void create_New_User() {
		
		try {
            Connection connection = dbcmg.getConnection();
            UsersDAO dao = new UsersDAO(connection);
            Users users = new Users();
            Random random = new Random();
            int ran1 = random.nextInt(500);
            String str1 = Integer.toString(ran1); 
            users.setUsername("test" + str1);
            users.setPassword("test_PasSw0rD");
            dao.create(users);
            dao.getID(users);
            String result = users.getUsername();
            assertEquals("test" + str1, result);

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}

	// Random generator used to create new update each time test is run
	@Test  
	public void update_User() {
		
		try {
            Connection connection = dbcmg.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            Users users = new Users();
            Random random = new Random();
            int ran1 = random.nextInt(500);
            String str1 = Integer.toString(ran1);
            users = usersDAO.findById(1);
            users.setUsername("NEW" + str1);
            usersDAO.update(users);
            String result = users.getUsername();
            assertEquals(result, "NEW" + str1);

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
