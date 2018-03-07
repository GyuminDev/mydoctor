package com.mydoctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "analysis_data")
public class AnalysisData {
	
	@Id
	private String username;
	
	@Column(name="BTH_G")
	private String bth_g;
	
	@Column(name = "SBP")
	private String sbp;
	
	@Column(name = "DBP")
	private String dbp;
	
	@Column(name = "FBS")
	private String fbs;
	
	@Column(name = "SEX")
	private int sex;
	
	@Column(name = "BMI")
	private double bmi;
	
	@Column(name = "Class")
	private String dis;
	
	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	@JsonIgnore
	private User user;

}
