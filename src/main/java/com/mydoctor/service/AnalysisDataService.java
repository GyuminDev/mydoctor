package com.mydoctor.service;

import com.mydoctor.dao.AnalysisDataDao;
import com.mydoctor.model.AnalysisData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisDataService {

	@Autowired
	private AnalysisDataDao analysisDataDao;

	public AnalysisData getAnalysisDataByUsername(String username) {
	
		return analysisDataDao.getAnalysisDataByUsername(username);
	}
	
}
