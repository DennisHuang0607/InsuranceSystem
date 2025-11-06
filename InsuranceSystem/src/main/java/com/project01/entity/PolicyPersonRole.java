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
    private Policy policyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PolicyPerson personId;
    @Column(name = "role")
    private String role;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	public PolicyPerson getPersonId() {
		return personId;
	}
	public void setPersonId(PolicyPerson personId) {
		this.personId = personId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
}
