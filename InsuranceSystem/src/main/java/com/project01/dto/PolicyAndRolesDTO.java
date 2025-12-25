package com.project01.dto;

import java.util.List;

import com.project01.entity.Policy;

import jakarta.validation.Valid;

public class PolicyAndRolesDTO {
	@Valid //必須加這行，才會去檢查Policy裡面的@Digits、@Positive
    private Policy policy; 
    private List<PolicyRolesDTO> PolicyRoles; 

    public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public List<PolicyRolesDTO> getPolicyRoles() {
		return PolicyRoles;
	}
	public void setPolicyRoles(List<PolicyRolesDTO> policyRoles) {
		PolicyRoles = policyRoles;
	}


}
