package com.accenture.tcf.bars.dao;

import java.sql.SQLException;

import com.accenture.tcf.bars.domain.Request;

public interface IRequestDAO {
	void insertRequest(Request request) throws SQLException;
	void deleteRequest();

}
