package com.project01.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "PolicyPersonRole",
       uniqueConstraints = @UniqueConstraint(columnNames = {"policy_id", "person_id", "role"}))
public class PolicyPersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Policy policy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "roles"})
    private PolicyPerson person;
    @Column(name = "role")
    private String role;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public PolicyPerson getPerson() {
		return person;
	}
	public void setPerson(PolicyPerson person) {
		this.person = person;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
}
