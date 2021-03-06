package com.mydoctor.controller;

import com.mydoctor.model.*;
import com.mydoctor.module.JsonPassingModule;
import com.mydoctor.module.MyHttpModule;
import com.mydoctor.service.*;
import com.mydoctor.vo.DataListObject;
import com.mydoctor.vo.IhealthData;
import com.mydoctor.vo.IhealthDataListStatus;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ihealth")
public class IhealthController {

	
	private final String clientId = "c4ce1e9cee9e4cf5997b92f264aea51b";
	private final String clientSecret = "42dde8e32f3c4de0b7b8d091eb2c5130";
	private final String BPSC = "dd46623886864692b7032b2ac35e46f0";
	private final String BPSV = "7be86544924c44c9a66e6d3879eac524";
	private final String BGSC = "dd46623886864692b7032b2ac35e46f0";
	private final String BGSV = "c2ad15379f674591bd5c3ea603f09aa9";
	private final String WEIGHT_SC = "dd46623886864692b7032b2ac35e46f0";
	private final String WEIGHT_SV = "064b5b81fc804e069e60461d2b6983a5";
	private final String BO_SC = "dd46623886864692b7032b2ac35e46f0";
	private final String BO_SV = "6a11211abc28431a8f775f757ee537c8";
	private String user_open_id;
	private String accessToken;
	private String username;

	private List<BloodPressure> bpList;
	private List<BloodOxygen> boList;
	private List<BloodSugar> bgList;
	private List<Weight> weightList;
	private List<IhealthDataListStatus> categoryList;

	private DataListObject dataListObject = new DataListObject();

	@Autowired
	private BloodPressureService bloodPressureService;

	@Autowired
	private BloodSugarService bloodSugarService;

	@Autowired
	private BloodOxygenService bloodOxygenService;

	@Autowired
	private UserCheckListService userCheckListService;

	@Autowired
	private WeightService weightService;

	/**
	 * getBp(). getBG(). getWeight...매소드를 실행하면 각model에 측정날짜가 들어감 DB에서 가장 최근의
	 * 데이터를 뽑아와(혈압, 혈당, 몸무게....) List에 ihealth에서 읽어온 data를
	 * list.get(i).getDate()를해서 각 항목별 측정날짜를 다뽑아 측정날짜가 더 큰 걸 db에 넣고
	 * IhealthDataCategory객체만들고 List로만들어서 Android에 주면 됨
	 */

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public DataListObject setIhealthData(@RequestBody IhealthData ihealthData)
			throws ClientProtocolException, IOException {
		this.user_open_id = ihealthData.getUser_open_id();
		this.accessToken = ihealthData.getAccessToken();
		this.username = ihealthData.getUsername();
		categoryList = new ArrayList<IhealthDataListStatus>();

		getBP();
		getBG();
		// getBO();
		getWeight();

		categoryList.add(new IhealthDataListStatus("수면", "0", "0", "0"));
		categoryList.add(new IhealthDataListStatus("혈중산소", "0", "0", "0"));

		this.dataListObject.setStatus(categoryList);

		return dataListObject;
	}

	// ------------------------혈압
	public void getBP() throws ClientProtocolException, IOException {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/bp.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + BPSC + "&");
		query.append("sv=" + BPSV);

		MyHttpModule module = new MyHttpModule();
		JSONObject obj = module.requestToServerUsingGetJSON(query.toString());
		int count = obj.getInt("RecordCount");
		JSONArray jsonArray = obj.getJSONArray("BPDataList");

		bpList = JsonPassingModule.jsonArrayToObject(jsonArray, BloodPressure.class);

		List<String> status = this.bloodPressureService.addBloodPressure(bpList, username);

		IhealthDataListStatus bpStatus = new IhealthDataListStatus("혈압", status.get(0), status.get(1), status.get(2));

		categoryList.add(bpStatus);
	}

	// ----------------------혈당
	public void getBG() throws ClientProtocolException, IOException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/glucose.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + BGSC + "&");
		query.append("sv=" + BGSV);

		MyHttpModule module = new MyHttpModule();
		JSONObject obj = module.requestToServerUsingGetJSON(query.toString());
		System.out.println(obj.toString());
		int count = obj.getInt("RecordCount");
		JSONArray jsonArray = obj.getJSONArray("BGDataList");

		bgList = JsonPassingModule.jsonArrayToObject(jsonArray, BloodSugar.class);

		List<String> status = this.bloodSugarService.addBloodSugar(bgList, username);

		IhealthDataListStatus bgStatus = new IhealthDataListStatus("혈당", status.get(0), status.get(1), status.get(2));

		categoryList.add(bgStatus);
	}

	public void getBO() throws ClientProtocolException, IOException {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		StringBuilder query = new StringBuilder();
		// Category 설정 필요.

		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/spo2.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + BO_SC + "&");
		query.append("sv=" + BO_SV);

		MyHttpModule module = new MyHttpModule();
		JSONObject obj = module.requestToServerUsingGetJSON(query.toString());

		System.out.println("bo : " + obj.toString());

		IhealthDataListStatus bgStatus = new IhealthDataListStatus("", "0", "0", "0");

		categoryList.add(bgStatus);

		// int count = obj.getInt("RecordCount");
		// JSONArray jsonArray = obj.getJSONArray("BODataList");
		//
		//
		//
		// bpList = JsonPassingModule.jsonArrayToObject(jsonArray,
		// BloodOxygen.class);
		//
		// this.bloodOxygenService.addBloodOxygen(boList, userId);
	}

	// -----------------------몸무게
	public void getWeight() throws ClientProtocolException, IOException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.ihealthlabs.com:8443/openapiv2/user/" + user_open_id + "/weight.json/?");
		query.append("client_id=" + clientId + "&");
		query.append("client_secret=" + clientSecret + "&");
		query.append("access_token=" + accessToken + "&");
		query.append("sc=" + this.WEIGHT_SC + "&");
		query.append("sv=" + this.WEIGHT_SV);

		MyHttpModule module = new MyHttpModule();
		JSONObject obj = module.requestToServerUsingGetJSON(query.toString());
		System.out.println(obj.toString());
		int count = obj.getInt("RecordCount");
		JSONArray jsonArray = obj.getJSONArray("WeightDataList");

		weightList = JsonPassingModule.jsonArrayToObject(jsonArray, Weight.class);

		List<String> status = this.weightService.addWeightByList(weightList, username);

		Weight weight = this.weightService.getRecentWeight(username);
		UserCheckList userCheckList = this.userCheckListService.findById(username);
		userCheckList.setWeight(weight.getWeightValue());
		this.userCheckListService.updateCheckList(userCheckList);

		IhealthDataListStatus weightStatus = new IhealthDataListStatus("체중", status.get(0), status.get(1),
				status.get(2));

		categoryList.add(weightStatus);

	}
}
