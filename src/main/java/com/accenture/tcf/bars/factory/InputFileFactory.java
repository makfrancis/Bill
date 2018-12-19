package com.accenture.tcf.bars.factory;

import java.io.File;

import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.CSVInputFileImpl;
import com.accenture.tcf.bars.file.IInputFile;
import com.accenture.tcf.bars.file.TextInputFileImpl;

public class InputFileFactory {
	
	private static InputFileFactory inputFileFactory;
	
	private InputFileFactory() {
		
	}

	public static InputFileFactory getInstance() {
		if (inputFileFactory == null) {
			inputFileFactory = new InputFileFactory();
		}
		return inputFileFactory;
		
	}
	
	public IInputFile getInputFile(File file) throws BarsException {
		String fileName = file.getName();
		
		String fileType = "";
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		if (fileType.trim().equals("csv")){

			System.out.println(fileName+" file");
			CSVInputFileImpl csv = new CSVInputFileImpl();
			csv.setFile(file);
			return csv;

		}else if(fileType.trim().equals("txt")) {
			
			System.out.println(fileName+" file");
			TextInputFileImpl text = new TextInputFileImpl();
			text.setFile(file);
			return text;
			
			
		} else {

			System.out.println("Invalid file extension!");
			return null;
			
		}

	}
	
}
