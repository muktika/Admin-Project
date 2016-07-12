package com.companyname.biometricAdmin.dao;

import com.companyname.biometricAdmin.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;
import javax.inject.Named;
import java.util.List;

/**
 * Created by muktika on 21/1/16.
 */
@Named
public class BiometricDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<AdminUser> getAllAdminUsers() {
        logger.info("Retrieving all admin users");
        List<AdminUser> adminUsers = hibernateTemplate.find("from AdminUser");
        return adminUsers;
    }

    public List<UserRoleAllocation> getUserRoles(int adminId) {
        logger.info("Retrieving all roles for admin id - {}", adminId);
        List<UserRoleAllocation> roleAllocations = hibernateTemplate.find("from UserRoleAllocation u where u.adminUser.adminId = ?",adminId);
        if(roleAllocations != null && roleAllocations.size() > 0)
            return roleAllocations;
        else
            return null;
    }

    public void addAdminUser(AdminUser adminUser) {
        logger.info("Saving Admin User in database. {}", adminUser);
        hibernateTemplate.saveOrUpdate(adminUser);
    }

    public void addAdminUserRoles(UserRoleAllocation userRole) {
        logger.info("Saving Admin User Role in database for admin details- {}", userRole);
        hibernateTemplate.saveOrUpdate(userRole);
    }

    public AdminUser getAdminUser(String email) {
        logger.info("Retrieving admin user credentials for email id {}", email);
        List<AdminUser> adminUsers = hibernateTemplate.find("from AdminUser a where a.email = ? ", email);
        if(adminUsers != null && adminUsers.size() > 0){
            return adminUsers.get(0);
        }
        return null;
    }

    public void addAssessment(Assessment assessment) {
        logger.info("saving Assessment in database. {}", assessment);
        hibernateTemplate.saveOrUpdate(assessment);
    }

    public void addAgency(Agency agency) {
        logger.info("saving Agency in database. {}", agency);
        hibernateTemplate.saveOrUpdate(agency);
    }

    public List<Assessment> getAllAssessments(){
        List<Assessment> assessmentsList = hibernateTemplate.find("from Assessment");
        return assessmentsList;
    }

    public void deleteAssessment(Assessment assessment){
        hibernateTemplate.delete(assessment);
    }

    public void resetAadharNumber(String aadharNumber){
        hibernateTemplate.bulkUpdate("Update Candidate c set aadharNumber=concat('-', c.aadharNumber), emailId = concat('-', c.emailId) where aadharNumber=? ", aadharNumber);
    }

    public Assessment getAssessmentForId(int assessmentId){
        logger.info("Retrieving assessment for assessment Id - {}", assessmentId);
        List<Assessment> assessment = hibernateTemplate.find("from Assessment a where a.assessmentId = ? ", assessmentId);
        return assessment.get(0);
    }

    public Agency getAgencyForId(int agencyId){
        logger.info("Retrieving agency for agency Id - {}", agencyId);
        List<Agency> agencies = hibernateTemplate.find("from Agency a where a.agencyId = ? ", agencyId);
        return agencies.get(0);
    }

    public List<Agency> getAllAgencies() {
        List<Agency> agencies = hibernateTemplate.find("from Agency");
        logger.info("count of agencies found :{}", agencies.size());
        return agencies;
    }

    public Agency getAgencyData(Integer agencyId) {
        logger.info("retrieving data for agencyId {}:", agencyId);
        List<Agency> agencies = hibernateTemplate.find("from Agency where agencyId = ?", agencyId);
        logger.info("count of agency data retrieved {}", agencies.size());
        if(agencies.size() > 0){
            return agencies.get(0);
        }
        return null;
    }

    public void deleteAgency(Agency agency) {
        logger.info("Deleting agency - {}", agency);
        hibernateTemplate.delete(agency);
    }

}
