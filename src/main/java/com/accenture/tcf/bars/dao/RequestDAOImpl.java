package com.accenture.tcf.bars.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Request;

public class RequestDAOImpl implements IRequestDAO{

	private Connection conn;
	public final static Logger log = LoggerFactory.getLogger(RequestDAOImpl.class);
	
	public RequestDAOImpl(Connection conn) {
		this.conn=conn;
	}
	
	public void insertRequest(Request request) throws SQLException{
		
		conn = MySQLDatasource.getConnection();

		int set1 = request.getBillingCycle();
		Date set2 = request.getStartDate();
		Date set3 = request.getEndDate();

		if (set2 == null) {
			throw new SQLException("Null values are not allowed!");
		} else if (set3 == null){
			throw new SQLException("Null values are not allowed!");
		}

			if (conn != null) {
				log.info("Condition successfully established");
			}

		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into request (billing_cycle, start_date, end_date) values (?,?,?)");

		preparedStatement.setInt(1, set1);
		preparedStatement.setDate(2, set2);
		preparedStatement.setDate(3, set3);

		conn.setAutoCommit(false);

		int rows = preparedStatement.executeUpdate();

		conn.commit();
		log.info(rows + " row(s) added");

		conn.close();
	}

	public void deleteRequest() {
		try {
			conn = MySQLDatasource.getConnection();

			Statement stmt = conn.createStatement();

			stmt.execute("truncate request");
		} catch (Exception e) {
		}
		
	}
	
	

}
