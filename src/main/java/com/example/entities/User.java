package com.example.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="created_on")
	private Timestamp createdOn = new Timestamp(new Date().getTime());

	@Column(name="email", length =50, nullable = false)
	private String email;

	@Column(name="name", length =100, nullable = false)
	private String userName;

	private String password;

}