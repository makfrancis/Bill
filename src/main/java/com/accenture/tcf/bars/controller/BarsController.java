package com.accenture.tcf.bars.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.FileProcessor;



@Controller
public class BarsController {
	private FileProcessor fileProcessor = new FileProcessor();
	private Logger log = LoggerFactory.getLogger(BarsController.class);
	
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	
	@PostMapping("/process")
	public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response)throws BarsException, NullPointerException {
		
		if (request == null || response == null) {
			throw new BarsException(
					"Error!",
					new NullPointerException());
		}
		String pathName = request.getParameter("file");
		log.info("File name : " + pathName);
		ModelAndView mav = new ModelAndView();
		try {
		
		File file = new File(pathName);
		fileProcessor.execute(file);
		List<Record> recs = fileProcessor.retrieveRecordFromDB();
		mav.setViewName("/success");
		mav.addObject("records", recs);
		fileProcessor.writeOutput(recs);
		
		
		
		}
		catch(Exception e) {
			if (e.getMessage().contains(
					BarsException.BILLING_CYCLE_NOT_ON_RANGE)) {
				log.error(BarsException.BILLING_CYCLE_NOT_ON_RANGE);
				mav.setViewName("/error_billing_cycle");
				mav.addObject("Error", e.getMessage());
			} else if (e.getMessage().contains(
					BarsException.INVALID_END_DATE_FORMAT)) {
				log.error(BarsException.INVALID_END_DATE_FORMAT);
				mav.setViewName("/error_invalid_end_date");
				mav.addObject("Error", e.getMessage());
			} else if (e.getMessage().contains(BarsException.INVALID_START_DATE_FORMAT)) {
				log.error(BarsException.INVALID_START_DATE_FORMAT);
				mav.setViewName("/error_invalid_start_date");
				mav.addObject("Error", e.getMessage());
			} else if (e.getMessage().contains(BarsException.NO_SUPPORTED_FILE)) {
				mav.setViewName("/error_format");
			} else if (e.getMessage()
					.contains(BarsException.NO_RECORDS_TO_READ)) {
				mav.setViewName("/error_no_records");
			} else if (e.getMessage().contains(
					BarsException.NO_RECORDS_TO_WRITE)) {
				mav.setViewName("/error_no_request");
			}
		}
	

		

//		FileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			List<FileItem> fields = upload.parseRequest(request);
//			Iterator<FileItem> it = fields.iterator();
//			
//			while (it.hasNext()) {
//				FileItem fileItem = it.next();
//					String[] line = fileItem.getString().split("\n");
//					for(int x=0;x<line.length;x++) { 
//						System.out.println(x + "\t" + line[x]);
//						System.out.println(line[x].split(",")[0] + "\t" + 
//								line[x].split(",")[1].replaceAll("/", "-") + "\t" + 
//								line[x].split(",")[2].replaceAll("/", "-"));
//						
//					}
//			}
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		
//		}
		


		
		return mav;
		
	}
	
	

}
