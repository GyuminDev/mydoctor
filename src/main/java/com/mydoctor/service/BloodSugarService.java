package com.mydoctor.service;

import com.mydoctor.dao.BloodSugarDao;
import com.mydoctor.model.BloodSugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodSugarService {
	@Autowired
	private BloodSugarDao bloodSugarDao;

	public List<BloodSugar> getBloodSugar(String userId) {
		return this.bloodSugarDao.getBloodSugar(userId);
	}

	public BloodSugar getRecentBloodSugar(String userId) {
		List<BloodSugar> bloodSugarList = this.bloodSugarDao.getBloodSugar(userId);
		if (bloodSugarList.isEmpty())
			return null;
		else
			return bloodSugarList.get(bloodSugarList.size() - 1);
	}

	public void addBloodSugar(BloodSugar bloodSugar) {
		this.bloodSugarDao.addBloodSugar(bloodSugar);
	}
	
	public List<String> addBloodSugar(List<BloodSugar> bgList, String userId) {
		for (int i = 0; i < bgList.size(); i++) {
			bgList.get(i).setUsername(userId);
		}
		return this.bloodSugarDao.addBloodSugar(bgList);

	}
	
	public List<BloodSugar> getBloodSugarByDate(String username, String fromDate, String toDate) {

		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";
		List<BloodSugar> data = this.bloodSugarDao.getBloodSugarByDate(username, fromDate, toDate);

		return data;

	}

	public void deleteBloodSugar(String username,String measurement_time) {
		this.bloodSugarDao.deleteBloodSugar(username,measurement_time);
		
	}
}
