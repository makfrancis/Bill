package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class TextInputFileImpl extends AbstractInputFile{

	public TextInputFileImpl() {
		
	}
	
	public void setFile(File file) {
		super.setFile(file);
	}

	public File getFile() {
		return super.getFile();
	}
	
	public List<Request> readFile() throws BarsException{
        List<Request> requests = new ArrayList<Request>();
        List<String> arr = new ArrayList<String>();
        BufferedReader br=null;
        File file = super.getFile();
        int ctr=0;
        Date dateStart;
        Date dateEnd;
        
        try {
        	br=new BufferedReader(new FileReader(file));
        	String lineReader;
        	while((lineReader=br.readLine())!=null) {
        		arr.add(lineReader);
        	}
   
        int i =0;
        while(i<arr.size()) {
        	int billCycle=0;
        	billCycle= Integer.parseInt(arr.get(i).substring(0, 2));
        	String startDate = null;
        	startDate = arr.get(i).substring(6, 10);
        	startDate += "-" + arr.get(i).substring(2, 4);
        	startDate += "-" + arr.get(i).substring(4, 6);
        	
        	String endDate = null;
        	endDate = arr.get(i).substring(14, 18);
        	endDate += "-" + arr.get(i).substring(10, 12);
        	endDate += "-" + arr.get(i).substring(12, 14);
        	
        	ctr++;
        	if(billCycle<1||billCycle>12) {
        		requests = null;
        		br.close();
        		throw new BarsException(BarsException.BILLING_CYCLE_NOT_ON_RANGE + ctr);
        	}
        	
        	try {
        		dateStart = Date.valueOf(startDate);
        	}catch(Exception e) {
        		requests = null;
        		br.close();
        		throw new BarsException(BarsException.INVALID_START_DATE_FORMAT + ctr);
        	}
        	
        	try {
        		dateEnd = Date.valueOf(endDate);
        	}catch(Exception e) {
        		requests = null;
        		br.close();
        		throw new BarsException(BarsException.INVALID_END_DATE_FORMAT + ctr);
        	}
        	
        	System.out.println("billingCycle: " + billCycle);
			System.out.println("startDate: " + dateStart);
			System.out.println("endDate: " + dateEnd);
			requests.add(new Request(billCycle, dateStart, dateEnd));
			i++;
			if(arr.toString().equals(null)) {
				System.out.println(arr.toString() + "lol");
			}
        }
			br.close();
        	
        }catch (IOException e) {
			System.out.println(e.getMessage());
		}
        return requests;
	}
        
}
