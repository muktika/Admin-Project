package com.companyname.biometricAdmin.model;

import javax.persistence.*;

@Entity
@Table(name = "Assessment")
public class Assessment {

	@Id
    @Column(name = "assessment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assessmentId;
	
	@Column(name = "companyname_assessment_id", nullable = false, unique = true)
	private int companynameAssessmentId;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    private Agency agency;
	
	@Column(name = "companyname_assessment_name", nullable = false)
	private String companynameAssessmentName;

	public int getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	public int getcompanynameAssessmentId() {
		return companynameAssessmentId;
	}

	public void setcompanynameAssessmentId(int companynameAssessmentId) {
		this.companynameAssessmentId = companynameAssessmentId;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getcompanynameAssessmentName() {
		return companynameAssessmentName;
	}

	public void setcompanynameAssessmentName(String companynameAssessmentName) {
		this.companynameAssessmentName = companynameAssessmentName;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", companynameAssessmentId=" + companynameAssessmentId + ", agency="
				+ agency + ", companynameAssessmentName=" + companynameAssessmentName + "]";
	}
	
	
	
}
