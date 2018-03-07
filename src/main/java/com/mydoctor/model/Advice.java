package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Advice {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String doctorname;

	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String advice;
	

	@MapsId("doctorname")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "doctorname")
	private User doctor;

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;
}
