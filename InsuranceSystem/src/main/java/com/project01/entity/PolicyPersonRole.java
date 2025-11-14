package com.project01.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PolicyPersonRole",
       uniqueConstraints = @UniqueConstraint(columnNames = {"policy_id", "person_id", "role"}))
public class PolicyPersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PolicyPerson person;
    @Column(name = "role")
    private String role;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
