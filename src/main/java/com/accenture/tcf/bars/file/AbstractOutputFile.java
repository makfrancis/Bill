package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.tcf.bars.domain.Record;

public abstract class AbstractOutputFile implements IOutputFile{
	
	private File file;
	protected Logger log = LoggerFactory.getLogger(AbstractOutputFile.class);
	@Override
	public void writeFile(List<Record> records) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setFile(File file) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
