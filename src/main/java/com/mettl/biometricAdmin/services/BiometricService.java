package com.companyname.biometricAdmin.services;

import com.companyname.biometricAdmin.dao.BiometricDao;
import com.companyname.biometricAdmin.model.AdminUser;
import com.companyname.biometricAdmin.model.Agency;
import com.companyname.biometricAdmin.model.Assessment;
import com.companyname.biometricAdmin.model.UserRoleAllocation;
import com.companyname.biometricAdmin.validators.BiometricAdminControllerPublishAgencyFormValidator;
import com.companyname.biometricAdmin.validators.BiometricAdminControllerPublishAssessmentsFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Named;
import java.util.List;

/**
 * Created by muktika on 21/1/16.
 */
@Named
public class BiometricService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BiometricDao biometricDao;

    @Resource
    private BiometricAdminControllerPublishAssessmentsFormValidator assessmentsFormValidator;


    @Resource
    private BiometricAdminControllerPublishAgencyFormValidator agencyFormValidator;


    public List<UserRoleAllocation> getUserRoles(int adminId) {return biometricDao.getUserRoles(adminId);}

    public List<AdminUser> getAllAdminUsers(){return biometricDao.getAllAdminUsers();}

    public void addAdminUser(AdminUser adminUser) {
        biometricDao.addAdminUser(adminUser);
    }

    public void addUserRoles(UserRoleAllocation userRole) {
        biometricDao.addAdminUserRoles(userRole);
    }

    public void addAssessment(Assessment assessment) {
        biometricDao.addAssessment(assessment);
        logger.info("Added assessment - {}", assessment);
    }

    public void addAgency(Agency agency) {
        biometricDao.addAgency(agency);
        logger.info("Added agency - {}", agency);
    }

    public List<Assessment> getAllAssessments() {
        List<Assessment> assessmentList = biometricDao.getAllAssessments();
        logger.info("Fetched all Assessments - {}",assessmentList);
        return assessmentList;
    }

    public Assessment getAssessmentForId(int assessmentId){
        Assessment assessment = biometricDao.getAssessmentForId(assessmentId);
        logger.info("Fetched Assessment for id - {}", assessmentId);
        return assessment;
    }

    public Agency getAgencyForId(int agencyId){
        Agency agency = biometricDao.getAgencyForId(agencyId);
        logger.info("Fetched Agency for id - {}", agencyId);
        return agency;
    }

    public void deleteAssessment(Assessment assessment){
        logger.info("Deleting assessment - {}", assessment);
        biometricDao.deleteAssessment(assessment);
    }

    public void resetAadharNumber(String aadharNumber){
        logger.info("Removing aadhar number - {}", aadharNumber);
        biometricDao.resetAadharNumber(aadharNumber);
    }

    public List<Agency> getAllAgencies() {
        return biometricDao.getAllAgencies();
    }

    public void deleteAgency(Agency agency) {
        biometricDao.deleteAgency(agency);
    }

}
