package com.accenture.tcf.bars.dao;

import java.util.List;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public interface IRecordDAO {
	List<Record> retrieveRecords() throws BarsException;

}
