package com.alkemy.disney.app.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
public class UserEntity implements Serializable{
	/*
	 * Representa la entidad de la DB
	 */
	private static final long serialVersionUID = -889520309470388939L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String userId;
	
	@Getter
	@Setter
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	@Getter
	@Setter
	@Column(nullable = false, length = 120, unique = true)
	private String email;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Getter
	@Setter
	private String emailVerificationToken;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private Boolean emailVerificationStatus = false;
}
