package com.fdm04.auditApp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fdm04.auditApp.database.util.DataAccessObject;
import com.fdm04.auditApp.model.Users;

public class UsersDAO extends DataAccessObject<Users> {
	
	private static String INSERT = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
	private static String UPDATE = "UPDATE Users SET Username = ?, Password = ? WHERE id = ?";
	private static String GET = "SELECT id, Username, Password FROM Users WHERE id = ?";
	private static String ALL = "SELECT * FROM Users";
	private static String DELETE = "DELETE FROM Users WHERE id = ?";
	private static String VERIFY = "SELECT Username, Password FROM Users";
	
	public UsersDAO(Connection connection) {
		super(connection);
		
	}
	
	// Method to create and commit new users to DB
	@Override
	public Users create(Users dto) {
		
		try (PreparedStatement statement = this.connection.prepareStatement(INSERT);){			
			statement.setString(1, dto.getUsername());
			statement.setString(2,  dto.getPassword());
			statement.execute();
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 
	}
	
	// Method used to update user details in the DB
	@Override
	public Users update(Users dto) {
		Users users = null;
		try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);){			
			statement.setString(1, dto.getUsername());
			statement.setString(2, dto.getPassword());
			statement.setInt(3, dto.getId());
			statement.execute();
			users = this.findById(dto.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		return users;
	}
	
	// Method used to find a user by their unique ID
	@Override
	public Users findById(int id) {
		Users users = new Users();
		try (PreparedStatement statement = this.connection.prepareStatement(GET);){
			
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("Username"));
				users.setPassword(rs.getString("Password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		return users;
	}
	
	// Method used to return all user details in the DB
	@Override
	public List<Users> findAll() {
		List<Users> users = new ArrayList<Users>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(ALL);){
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		return users;
	}
	
	// Method used to delete a user from the DB
	@Override
	public void delete(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(DELETE);){
			statement.setInt(1, id);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	// Method used to verify a users log in credentials
	public boolean verifyUser(String uname, String pwd) {
		
		boolean flag = false;
		
		try (PreparedStatement statement = this.connection.prepareStatement(VERIFY);){
			ResultSet rs = statement.executeQuery();			
			while (rs.next()) {
				String username = rs.getString("Username");
				String password = rs.getString("Password");				
				if (username.equals(uname) && password.equals(pwd)) {
					flag = true;
				}
				
			}			
			return flag;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
				
	}
				
}


