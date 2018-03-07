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
@Table(name = "bloodoxygen")
@IdClass(DataPK.class)
public class BloodOxygen {
	
	private String username;

	@Id
	@Column(name = "date", nullable = false)
	private String measurement_time;

	@Column(nullable = false)
	private int BO; // ���߻�ҳ�

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;
	
	
	@Transient
	private String Lat;
	@Transient
	private String Lon;
	@Transient
	private String MDate;
	@Transient
	private String Note;
	@Transient
	private String LastChangeTime;
	@Transient
	private String DataSource;
	@Transient
	private String TimeZone;
}
