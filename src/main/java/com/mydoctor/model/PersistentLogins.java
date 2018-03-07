package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {
	private String username;
	@Id
	private String series;
	private String token;
	private Timestamp last_used;
}
