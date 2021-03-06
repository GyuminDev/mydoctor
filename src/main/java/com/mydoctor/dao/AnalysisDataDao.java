package com.mydoctor.dao;

import com.mydoctor.model.AnalysisData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnalysisDataDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public AnalysisData getAnalysisDataByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		return (AnalysisData) session.get(AnalysisData.class, username);
	}

}
