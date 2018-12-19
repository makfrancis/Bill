package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Record;

public interface IOutputFile {
	void writeFile(List<Record> records);
	void setFile(File file);
	File getFile();
	

}
