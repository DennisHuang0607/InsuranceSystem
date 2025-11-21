package com.project01.dto;

import java.util.List;

import com.project01.entity.Policy;

public class PolicyAndRolesDTO {
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
