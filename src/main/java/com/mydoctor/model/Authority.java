package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	private String username;

	@Column(nullable = false)
	private String authority;

	@MapsId("username")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

}
