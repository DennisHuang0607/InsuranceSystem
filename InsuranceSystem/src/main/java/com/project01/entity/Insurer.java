package com.project01.entity;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Insurer")
public class Insurer{
	@Id
	@GeneratedValue(generator = "insurer-id-generator")
    @GenericGenerator(name = "insurer-id-generator", strategy = "com.project01.component.InsurerIdGenerator")
    @Column(name = "insurer_id")
	private String insurerId;
	@Column(name = "name")
	private String name;
	@Column(name = "account_id", unique = true)
	private String accountId;
	@Column(name = "password")
	private String password;
	@Column(name = "phone", nullable = true)
	private String phone;
	@Column(name = "email", nullable = true)
	private String email;
	@OneToMany(mappedBy = "insurer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Policy> policies;
	
	public String getInsurerId() {
		return insurerId;
	}
	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
	
	
}
