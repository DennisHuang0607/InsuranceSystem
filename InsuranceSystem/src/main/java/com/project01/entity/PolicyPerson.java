package com.project01.entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "PolicyPerson")
public class PolicyPerson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;
	@Column(name = "name")
	private String name;
	@Column(name = "id_number",unique = true)
	private String idNumber;
	@Column(name = "gender",nullable = true)
	private String gender;
	@Column(name = "birth_date")
	private String birthDate;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email",nullable = true)
	private String email;
	@Column(name = "address",nullable = true)
	private String address;
	@Column(name = "occuption",nullable = true)
	private String occupation;
	@OneToMany(mappedBy = "personId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<PolicyPersonRole> roles;
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public List<PolicyPersonRole> getRoles() {
		return roles;
	}
	public void setRoles(List<PolicyPersonRole> roles) {
		this.roles = roles;
	}
	
}
