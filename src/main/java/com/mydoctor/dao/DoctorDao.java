package com.mydoctor.dao;

import com.mydoctor.model.Advice;
import com.mydoctor.model.AssignedUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DoctorDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<AssignedUser> getAssignedUser(String doctorId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from AssignedUser as au where au.doctorname = :doctorname");
		query.setParameter("doctorname",doctorId);
		
		List<AssignedUser> userList = query.list();
		
		return userList;
	}

	public void addAdvice(Advice advice) {
		Session session = sessionFactory.getCurrentSession();
		session.save("Advice", advice);
		
		session.flush();
		session.clear();
		
	}

}