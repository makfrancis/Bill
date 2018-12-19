package com.accenture.tcf.bars.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class RecordDaoImpl implements IRecordDAO{
	
	private Connection conn;
	
	

	public RecordDaoImpl(Connection conn) {
		this.conn = conn;
	}

	public List<Record> retrieveRecords() throws BarsException{
		List<Request> req = new ArrayList<Request>();
		List<Record> rec = new ArrayList<Record>();
		try {
			conn = MySQLDatasource.getConnection();
			Statement sts = conn.createStatement();
			ResultSet rs = sts.executeQuery("select * from request");

			if (rs.next() == false) {
				throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
			}
			do {
				Request reqs = new Request();

				reqs.setBillingCycle(rs.getInt(2));
				reqs.setStartDate(rs.getDate(3));
				reqs.setEndDate(rs.getDate(4));

				Request requests = new Request(
						reqs.getBillingCycle(),
						reqs.getStartDate(), 
						reqs.getEndDate());
						req.add(requests);

				try {
					int bC = 0;
					Date sDate = null;
					Date eDate = null;
					for (Request r : req) {
						bC = r.getBillingCycle();
						sDate = r.getStartDate();
						eDate = r.getEndDate();
					}
					
					conn = MySQLDatasource.getConnection();
					PreparedStatement ps = conn
							.prepareStatement("select b.billing_cycle, b.start_date, b.end_date, c.first_name, c.last_name, b.amount from billing b inner join account a on b.account_id = "
									+ "a.account_id inner join customer c on c.customer_id = a.customer_id where b.billing_cycle = ? "
									+ "and b.start_date = ? and b.end_date = ?; ");
					ps.setInt(1, bC);
					ps.setDate(2, sDate);
					ps.setDate(3, eDate);
					ResultSet rs1 = ps.executeQuery();
					Record recs = new Record();

					if (rs1.next() == false) {
						throw new BarsException(
								BarsException.NO_RECORDS_TO_READ);
					} else {
						do {
							recs.setBillingCycle(rs1.getInt(1));
							recs.setStartDate(rs1.getDate(2));
							recs.setEndDate(rs1.getDate(3));
							recs.setCustomerFirstName(rs1.getString(4));
							recs.setCustomerLastName(rs1.getString(5));
							recs.setAmount(rs1.getDouble(6));
							Record records = new Record(recs.getBillingCycle(),
									recs.getStartDate(), recs.getEndDate(),
									recs.getCustomerFirstName(),
									recs.getCustomerLastName(),
									recs.getAmount());
							rec.add(records);
						} while (rs1.next());
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new BarsException(BarsException.NO_RECORDS_TO_WRITE);
				}
			} while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rec;
	}
	
	
}
