package com.project01.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "InsuranceCompany")
public class InsuranceCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int companyId;
	@Column(name = "company_name", unique = true)
	private String companyName;
	@Column(name = "address",nullable = true)
	private String address;
	@Column(name = "telephone",nullable = true)
	private String telephone;
	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Policy> policies;
	
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
	
}
