package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "username")
	private String id;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private int enabled;
	
	@Column(name = "token", nullable = false)
	private int token;
	
	@Column(nullable = false)
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean islogin;

}
