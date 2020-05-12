package com.smc.model;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	@Column(name = "username", length = 32)
	@NotNull
	@Size(min = 3, max = 32)
	private String username;

	@Column(name = "email", length = 32, unique = true)
	@NotNull
	@Size(min = 8, max = 32)
	private String email;

	@JsonIgnore
    @Column(name = "password", length = 128)
    @NotNull
    @Size(min = 8, max = 128)
	private String password;

	@Column(name = "mobile", length = 16)
    @Size(min = 0, max = 16)
	private String mobile;

	
	@Column(name = "create_time")
	@CreatedDate
	private Timestamp createTime;

	@Column(name = "last_upd_time")
	@LastModifiedDate
	private Timestamp lastUpdTime;

	@Column(name = "user_type")
    @Enumerated(EnumType.STRING)
	private UserType userType;//role define: ADMIN/USER

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(Timestamp lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
