package com.project01.entity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.project01.component.PolicyNumberComponent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Policy")
public class Policy {
	
	//用靜態setter注入，因為Entity不屬於Spring管理的Bean，所以不能用欄位注入
//	private static PolicyNumberComponent policyNumberComponent;
//	@Autowired
//    public void setPolicyNumberComponent(PolicyNumberComponent component) {
//        this.policyNumberComponent = component;
//    }
//	
//	@PrePersist
//    public void generatePolicyNumberIfNeeded() {
//        if (policyNumber == null || policyNumber.isEmpty()) {
//            this.policyNumber = policyNumberComponent.generateNewPolicyNumber(); 
//        }
//    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "policy_id")
	private int policyId;
	@Column(name = "policy_number", unique = true)
	private String policyNumber;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_type_id")
	private InsuranceType type;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
	private InsuranceCompany company;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurer_id")
	private Insurer insurer;
	@Column(name = "insured_amount")
	private int insuredAmount;
	@Column(name = "accept_date")
	private LocalDate acceptDate;
	@Column(name = "begin_date")
	private LocalDate beginDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "payment_type",nullable = true)
	private String paymentType;
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public InsuranceType getType() {
		return type;
	}
	public void setType(InsuranceType type) {
		this.type = type;
	}
	public InsuranceCompany getCompany() {
		return company;
	}
	public void setCompany(InsuranceCompany company) {
		this.company = company;
	}
	public Insurer getInsurer() {
		return insurer;
	}
	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}
	public int getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(int insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	public LocalDate getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(LocalDate acceptDate) {
		this.acceptDate = acceptDate;
	}
	public LocalDate getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
}
