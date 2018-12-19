package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class CSVInputFileImpl extends AbstractInputFile{

	public CSVInputFileImpl() {

	}
	

	public File getFile() {
		return super.getFile();
	}

	public void setFile(File file) {
		super.setFile(file);
		
	}
	
	public List<Request> readFile() throws BarsException{
		
	List<Request> requests = new ArrayList<Request>();	
	 File csvFile = super.getFile();
	 BufferedReader br = null;
	 String startDate = null;
	 String endDate = null;
	 String formatStartDate;
	 String formatEndDate;
	 String lineReader = "";
	 int ctr = 0;
	 
	 

	 try {
			br = new BufferedReader(new FileReader(csvFile));

			while ((lineReader = br.readLine()) != null) {
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
						"mm-dd-yyyy");
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
						"yyyy-mm-dd");

				String[] parts = (lineReader.replaceAll("/", "-")).split(",");

				System.out.println("Billing no: " + parts[0] + " Start Date: "
						+ parts[1] + " End Date: " + parts[2]);

				startDate = parts[1];
				endDate = parts[2];

				int billCycle = Integer.parseInt(parts[0]);
				System.out.println("billingCycle: " + billCycle);


				ctr++;
				if (billCycle < 1 || billCycle > 12) {
					br.close();
					throw new BarsException(
							BarsException.BILLING_CYCLE_NOT_ON_RANGE + ctr);
				}
				try {
					formatStartDate = simpleDateFormat2
							.format(simpleDateFormat1.parse(startDate));
					System.out.println("startDate: " + formatStartDate);
				} catch (ParseException e) {
					br.close();
					throw new BarsException(
							BarsException.INVALID_START_DATE_FORMAT + ctr);
				}
				try {
					formatEndDate = simpleDateFormat2.format(simpleDateFormat1
							.parse(endDate));
					System.out.println("endDate: " + formatEndDate);
				} catch (ParseException e) {
					br.close();
					throw new BarsException(
							BarsException.INVALID_END_DATE_FORMAT + ctr);
				}
				requests.add(new Request(billCycle, Date.valueOf(formatStartDate),
						Date.valueOf(formatEndDate)));
			}

			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	 
		
	 	return requests;
	}

	
	
	
	
	
	
	

}
