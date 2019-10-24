package com.fdm04.auditApp.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fdm04.auditApp.database.util.DataAccessObject;
import com.fdm04.auditApp.model.Audit;

public class AuditDAO extends DataAccessObject<Audit>{
		
	private static String INSERT = "INSERT INTO Audits (ProjectName, ProjectManager, Auditor, Score) VALUES (?, ?, ?, ?)";
	private static String UPDATE = "UPDATE Audits SET ProjectName = ?, ProjectManager = ?, Auditor = ?, Summary = ?, Score = ? WHERE id = ?";
	private static String GET = "SELECT id, ProjectName, ProjectManager, Auditor, Summary, Score FROM Audits WHERE id = ?";
	private static String ALL = "SELECT * FROM Audits";
	private static String DELETE = "DELETE FROM Audits WHERE id = ?";
	private static String GETID = "SELECT id, ProjectName, ProjectManager, Auditor, Summary, Score FROM Audits"
			+ " WHERE ProjectName = ? AND ProjectManager = ? AND Auditor = ? order by id desc limit 1";	
	
	public AuditDAO(Connection connection) {
		super(connection);
	}

	// Method used to create and commit a new audit to the DB
	@Override
	public Audit create(Audit dto) {
		
		try (PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
            statement.setString(1, dto.getProjectName());
            statement.setString(2, dto.getProjectManager());
            statement.setString(3, dto.getAuditor());
            statement.setDouble(4, dto.getScore());
            statement.execute();
            return null; 
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	// Method used to change or update existing audit details and commit to DB
	@Override
	public Audit update(Audit dto) {
		
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
            statement.setString(1, dto.getProjectName());
            statement.setString(2, dto.getProjectManager());
            statement.setString(3, dto.getAuditor());
            statement.setString(4, dto.getSummary());
            statement.setDouble(5, dto.getScore());
            statement.setInt(6, dto.getId());           
            statement.executeUpdate(); 
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return this.findById(dto.getId());
	}

	// Method used to find an audit by its unique ID
	@Override
	public Audit findById(int id) {
		
		Audit audit = new Audit();
        try (PreparedStatement statement = this.connection.prepareStatement(GET);) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                audit.setId(rs.getInt("id"));
                audit.setProjectName(rs.getString("ProjectName"));
                audit.setProjectManager(rs.getString("ProjectManager"));
                audit.setScore(rs.getDouble("Score"));
                audit.setAuditor(rs.getString("Auditor"));
                audit.setSummary(rs.getString("Summary"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return audit;
	}

	// Method used to return all audits stored in the DB
	@Override
	public List<Audit> findAll() {
		
		List<Audit> audits = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(ALL);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Audit audit = new Audit();
                audit.setId(rs.getInt("id"));
                audit.setProjectName(rs.getString("ProjectName"));
                audit.setProjectManager(rs.getString("ProjectManager"));
                audit.setScore(rs.getDouble("Score"));
                audit.setAuditor(rs.getString("Auditor"));
                audits.add(audit);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return audits;
	}

	// Method used to delete an audit from the DB
	@Override
	public void delete(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }		
	}
	
	/*
	 * Method used to get the ID of a newly created audit this is required to allow
	 * changes to the audit to be to DB straight after creating
	 */
	public Audit getID(Audit audit) {
		
		try (PreparedStatement statement = this.connection.prepareStatement(GETID);){
			statement.setString(1, audit.getProjectName());
			statement.setString(2, audit.getProjectManager());
			statement.setString(3, audit.getAuditor());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                audit.setId(rs.getInt("id"));
                audit.setProjectName(rs.getString("ProjectName"));
                audit.setProjectManager(rs.getString("ProjectManager"));
                audit.setScore(rs.getDouble("Score"));
                audit.setAuditor(rs.getString("Auditor"));     	
            }	
            
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
			return audit;
	}
	
}
