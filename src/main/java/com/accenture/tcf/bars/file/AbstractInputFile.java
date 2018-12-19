package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Request;

public abstract class AbstractInputFile implements IInputFile{
	File file;


	public void setFile(File file) {
		this.file = file;
		
	}

	public File getFile() {
		return file;
	}
	
}
