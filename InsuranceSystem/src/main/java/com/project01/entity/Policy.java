package com.project01.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project01.component.PolicyNumberComponent;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Policy")
public class Policy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "policy_id")
	private int policyId;
	@Column(name = "policy_number", unique = true)
	private String policyNumber;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_type_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "policies"})
	private InsuranceType type;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "policies"})
	private InsuranceCompany company;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurer_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "policies"})
	private Insurer insurer;
	@Column(name = "insured_amount",precision = 12,scale = 0)
	@Digits(integer = 10, fraction = 0, message = "保費必須為整數")
	@Positive(message = "保費金額必須大於0")
	private BigDecimal insuredAmount;
	@Column(name = "accept_date")
	private LocalDate acceptDate;
	@Column(name = "begin_date")
	private LocalDate beginDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "payment_type",nullable = true)
	private String paymentType;
    @OneToMany(mappedBy = "policy",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","policy"})
    private List<PolicyPersonRole> policyPersonRoles;
	
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
	public BigDecimal getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(BigDecimal insuredAmount) {
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
	public List<PolicyPersonRole> getPolicyPersonRoles() {
		return policyPersonRoles;
	}
	public void setPolicyPersonRoles(List<PolicyPersonRole> policyPersonRoles) {
		this.policyPersonRoles = policyPersonRoles;
	}
	
}
