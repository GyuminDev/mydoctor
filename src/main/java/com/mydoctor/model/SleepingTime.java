package com.mydoctor.model;

import com.mydoctor.module.DataPK;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="sleepingtime")
@IdClass(DataPK.class)
public class SleepingTime {

	private String username;
	
	@Id
	@Column(name = "date",nullable = false)
	private String measurement_time;

	@Column(nullable = false)
	private String sleepingTime;
	
	private String startSleepTime;
	
	private String endSleepTime;

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;
	
}
