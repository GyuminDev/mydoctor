package com.mydoctor.service;

import com.mydoctor.dao.DoctorDao;
import com.mydoctor.model.Advice;
import com.mydoctor.model.AssignedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;

	public List<AssignedUser> getAssignedUser(String doctorId) {
		// TODO Auto-generated method stub
		return this.doctorDao.getAssignedUser(doctorId);
	}

	public void addAdvice(Advice advice) {
		this.doctorDao.addAdvice(advice);
		
	}
	
	
}
