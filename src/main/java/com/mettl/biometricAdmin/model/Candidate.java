package com.companyname.biometricAdmin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shanky on 25/11/15.
 */
@Entity
@Table(name = "Candidate")
public class Candidate {
    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @Column(name="email_id", nullable=false)
    private String emailId;

    @Column(name="aadhar_number", nullable = false)
    private String aadharNumber;

    @Column(name="crfs", nullable = false, columnDefinition="VARCHAR")
    private String crfsJson;

    public Candidate(){
    }
    public Candidate(Date createdAt, String emailId, String aadharNumber, String crfsJson) {
        this.createdAt = createdAt;
        this.emailId = emailId;
        this.aadharNumber = aadharNumber;
        this.crfsJson = crfsJson;
    }

	public Candidate(String emailId, String aadharNumber, String crfsJson) {
		this(new Date(), emailId, aadharNumber, crfsJson);
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getCrfsJson() {
		return crfsJson;
	}

	public void setCrfsJson(String crfsJson) {
		this.crfsJson = crfsJson;
	}

	@Override
	public String toString() {
		return "Candidate{" +
				"candidateId=" + candidateId +
				", createdAt=" + createdAt +
				", emailId='" + emailId + '\'' +
				", aadharNumber='" + aadharNumber + '\'' +
				", crfsJson='" + crfsJson + '\'' +
				'}';
	}
}
