package com.companyname.biometricAdmin.model;

/**
 * Created by muktika on 21/12/15.
 */

public class BiometricAdminControllerPublishAssessmentsForm {
    private int assessmentId;
    private int companynameAssessmentId;
    private int agencyId;
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

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getcompanynameAssessmentName() {
        return companynameAssessmentName;
    }

    public void setcompanynameAssessmentName(String companynameAssessmentName) {
        this.companynameAssessmentName = companynameAssessmentName;
    }

    @Override
    public String toString() {
        return "BiometricAdminControllerPublishAssessmentsForm{" +
                "assessmentId=" + assessmentId +
                ", companynameAssessmentId=" + companynameAssessmentId +
                ", agencyId=" + agencyId +
                ", companynameAssessmentName='" + companynameAssessmentName + '\'' +
                '}';
    }


}
