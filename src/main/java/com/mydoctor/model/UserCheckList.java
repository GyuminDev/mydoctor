package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name="userchecklist")
public class UserCheckList {
	
	@Id
	private String username;
	
	@Column
	private String name;
	
	@Column
	private String birth;
	
	@Column
	private int age;
	@Column
	private int height;
	@Column
	private double weight;
	
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean sex;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean issmoke;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isalcohol;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean ishighpressure;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isdiabetes;
	
	@Transient
	private int lastHeartrate;
	@Transient
	private int lastBloodoxygen;
	@Transient
	private int lastBloodsugar;
	@Transient
	private int lastStepcount;
	@Transient
	private int lastHP;
	@Transient
	private int lastHR;
	@Transient
	private int lastBloodOxygen;
	@Transient
	private String lastSleepingTime;
	@Transient
	private int lastCalorie;
	
	@Transient
	private String dis;
	
	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

}
