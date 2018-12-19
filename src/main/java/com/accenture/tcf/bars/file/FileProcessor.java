package com.accenture.tcf.bars.file;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.tcf.bars.dao.IRecordDAO;
import com.accenture.tcf.bars.dao.IRequestDAO;
import com.accenture.tcf.bars.dao.RecordDaoImpl;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySQLDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.factory.InputFileFactory;

public class FileProcessor {
	private Connection conn;
	private IInputFile inputFile;
	private IOutputFile outputFile;
	private IRequestDAO requestDAO;
	private IRecordDAO recordDAO;
	
	
	
	public void execute(File file) throws BarsException, SQLException{
		requestDAO = new RequestDAOImpl(conn);
		recordDAO = new RecordDaoImpl(conn);
		requestDAO.deleteRequest();

		InputFileFactory inputFactory = InputFileFactory.getInstance();
		inputFile = inputFactory.getInputFile(file);
		if (inputFile == null) {
			throw new BarsException(BarsException.NO_SUPPORTED_FILE);
		}
		inputFile.setFile(file);
		ArrayList<Request> requests = null;
		requests = (ArrayList<Request>) inputFile.readFile();
		for (Request req : requests) {
			requestDAO.insertRequest(req);
		}
	}
	
	public List<Record> retrieveRecordFromDB() throws BarsException{
		List<Record> record;
		record = recordDAO.retrieveRecords();
		System.out.println(record.get(0).getCustomerFirstName());
		return record;
		
	}
	
	public void writeOutput(List<Record> record) {
		conn = new MySQLDatasource().getConnection();
		outputFile = new XMLOutputFileImpl();
		requestDAO = new RequestDAOImpl(conn);
		outputFile.writeFile(record);
		requestDAO.deleteRequest();
	}
	
	

}
