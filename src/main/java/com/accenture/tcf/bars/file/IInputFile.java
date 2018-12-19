package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public interface IInputFile {
	List<Request> readFile()throws BarsException;
	void setFile(File file);
	File getFile();
	
}
