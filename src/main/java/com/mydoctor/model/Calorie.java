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
@Table(name = "calorie")
@IdClass(DataPK.class)
public class Calorie {
	
	private String username;

	@Id
	@Column(name = "date", nullable = false)
	private String measurement_time;
	
	@Column(nullable = false)
	private int calorie;

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;

}
