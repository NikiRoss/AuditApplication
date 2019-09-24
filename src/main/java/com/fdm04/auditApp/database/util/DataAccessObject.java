package com.fdm04.auditApp.database.util;

import java.sql.Connection;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject>{
	
	protected final Connection connection;

	public DataAccessObject(Connection connection) {
		
		super();
		this.connection = connection;
	}
	
	public abstract T create(T dto);
	public abstract T update(T dto);
	public abstract T findById(int id);
	public abstract List<T> findAll();
	public abstract void delete(int id);

	
	
	

}
