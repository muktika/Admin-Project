package com.companyname.biometricAdmin.model;

import javax.persistence.*;

@Entity
@Table(name = "Agency")
public class Agency {
	
	@Id
    @Column(name = "agency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agencyId;
	
	@Column(name = "agency_name", unique = true)
    private String agencyName;
	
	@Column(name = "public_key", unique = true)
    private String publicKey;
	
	@Column(name = "private_key", unique = true)
    private String privateKey;

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String toString() {
		return "Agency{" +
				"agencyId=" + agencyId +
				", agencyName='" + agencyName + '\'' +
				", publicKey='" + publicKey + '\'' +
				", privateKey='" + privateKey + '\'' +
				'}';
	}
}
