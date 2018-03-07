package com.mydoctor.model;

import com.mydoctor.module.DataPK;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "heartrate")
@IdClass(DataPK.class)
public class HeartRate {

	private String username;

	@Id
	@Column(name = "date",nullable = false)
	private String measurement_time;

	@Column(nullable = false)
	private int heartRate;

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;
}
