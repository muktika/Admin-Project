package com.companyname.biometricAdmin.controllers;

import com.companyname.biometricAdmin.Constants.AdminLoginConstants;
import com.companyname.biometricAdmin.model.*;
import com.companyname.biometricAdmin.services.ApiClientService;
import com.companyname.biometricAdmin.services.BiometricService;
import com.companyname.biometricAdmin.services.UserService;
import com.companyname.biometricAdmin.validators.AadharNumberFormValidator;
import com.companyname.biometricAdmin.validators.BiometricAdminControllerPublishAgencyFormValidator;
import com.companyname.biometricAdmin.validators.BiometricAdminControllerPublishAssessmentsFormValidator;
import com.companyname.biometricAdmin.validators.LoginFormValidator;
import com.companyname.model.companynameApis.v1.responses.ApiAssessmentResponse;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by muktika on 18/12/15.
 */

@Controller
@RequestMapping(value = "/admin")
public class BiometricAdminController {

    public static final String HomePageUrl = "home";

    @Resource
    private LoginFormValidator loginFormValidator;

    @Resource
    private BiometricAdminControllerPublishAgencyFormValidator agencyFormValidator;

    @Resource
    private BiometricAdminControllerPublishAssessmentsFormValidator assessmentsFormValidator;

    @Resource
    private AadharNumberFormValidator aadharNumberFormValidator;

    @Resource
    BiometricService biometricService;

    @Resource
    ApiClientService apiService;

    @Resource
    UserService userService;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String getLoginPage(HttpServletRequest request, HttpServletResponse response,
                                Model model,@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout){
        if (error != null) {
            request.setAttribute("error", "Invalid Login");
        }

        if (logout != null) {
            request.setAttribute("msg", "Successfully Logged out");
        }

        return "@login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    private String homePage(HttpServletRequest request) {

        Set<String> roles = AuthorityUtils
                .authorityListToSet(SecurityContextHolder.getContext()
                        .getAuthentication().getAuthorities());
        if (roles.contains("ROLE_companyname_ADMIN")) {
            return "@home";
        }
        else{
            return "@reportingHome";
        }

    }

    @RequestMapping(value = "/report/historicalData", method = RequestMethod.GET)
    public String getHistoricalData(HttpServletRequest request, HttpServletResponse response) {
        String toDate = request.getParameter("todate");
        String fromDate = request.getParameter("fromdate");
        if (toDate == null || fromDate == null) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date date = new Date(cal.getTimeInMillis());
            String strDate = formatDate.format(date);
            request.setAttribute("to", strDate);
            request.setAttribute("from", strDate);
        } else {
            request.setAttribute("to", toDate);
            request.setAttribute("from", fromDate);
        }
        return "@report";
    }


    @RequestMapping(value = "/report/candidateReportData", method = RequestMethod.GET)
    public String getCandidateReport(HttpServletRequest request, HttpServletResponse response,
                                     Model model, @ModelAttribute(value = "aadharForm") AadharNumberForm aadharForm, BindingResult bindingResult) {

        return "@candidatereport";
    }

    @RequestMapping(value = "/report/candidateReportData", method = RequestMethod.POST)
    public String getCandidateReportData(HttpServletRequest request, HttpServletResponse response,
                                     Model model, @ModelAttribute(value = "aadharForm") AadharNumberForm aadharForm, BindingResult bindingResult) {

        aadharNumberFormValidator.validate(aadharForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "@candidatereport";
        }
        String aadharNumber = aadharForm.getAadharNumber();
        request.setAttribute("success", true);
        request.setAttribute("aadharNumber" , aadharNumber);
        return "@candidatereport";
    }


    @RequestMapping(value = "/viewAssessments", method = RequestMethod.GET)
    public String viewAllAssessments(HttpServletRequest request, HttpServletResponse response) {
        List<Assessment> assessmentsList = biometricService.getAllAssessments();
        logger.info("Fetched Assessment List to display");
        request.setAttribute("assessmentsList", assessmentsList);
        return "@viewAssessments";

    }

    @RequestMapping(value = "/publishAssessments", method = RequestMethod.GET)
    public String getPublishAssessments(HttpServletRequest request, HttpServletResponse response,
                                        Model model, @ModelAttribute(value = "publishAssessmentsForm") BiometricAdminControllerPublishAssessmentsForm publishAssessmentsForm, BindingResult bindingResult) {
        if (request.getParameter("assessmentId") != null) {
            int assessmentId = Integer.parseInt(request.getParameter("assessmentId"));
            Assessment assessment = biometricService.getAssessmentForId(assessmentId);
            publishAssessmentsForm.setcompanynameAssessmentId(assessment.getcompanynameAssessmentId());
            publishAssessmentsForm.setcompanynameAssessmentName(assessment.getcompanynameAssessmentName());
            publishAssessmentsForm.setAgencyId(assessment.getAgency().getAgencyId());
            publishAssessmentsForm.setAssessmentId(assessmentId);
        }
        return "@publishAssessments";
    }

    @RequestMapping(value = "/publishAssessments", method = RequestMethod.POST)
    public String getPublishAssessmentsAndInsertIntoDatabase(HttpServletRequest request, HttpServletResponse response,
                                                             Model model, @ModelAttribute(value = "publishAssessmentsForm") BiometricAdminControllerPublishAssessmentsForm publishAssessmentsForm, BindingResult bindingResult) {

        assessmentsFormValidator.validate(publishAssessmentsForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "@publishAssessments";
        }

        Assessment assessment = new Assessment();
        assessment.setAssessmentId(publishAssessmentsForm.getAssessmentId());
        assessment.setcompanynameAssessmentId(publishAssessmentsForm.getcompanynameAssessmentId());
        assessment.setcompanynameAssessmentName(publishAssessmentsForm.getcompanynameAssessmentName());
        Agency agency = new Agency();
        agency.setAgencyId(publishAssessmentsForm.getAgencyId());
        assessment.setAgency(agency);
        try {
            ApiAssessmentResponse assessmentResponse = apiService.getAssessmentById(publishAssessmentsForm.getAgencyId(), publishAssessmentsForm.getcompanynameAssessmentId());
        } catch (Exception e) {
            bindingResult.rejectValue("companynameAssessmentId", "assessment.not.exist");
            return "@publishAssessments";
        }

        try {
            biometricService.addAssessment(assessment);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("companynameAssessmentId", "data.integrity");
            return "@publishAssessments";
        }
        return "redirect:viewAssessments";

    }

    @RequestMapping(value = "/deleteAssessment", method = RequestMethod.POST)
    public String deleteAssessment(HttpServletRequest request, HttpServletResponse response) {
        int assessmentId = Integer.parseInt(request.getParameter("assessmentId"));
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(assessmentId);
        biometricService.deleteAssessment(assessment);
        return "redirect:viewAssessments";

    }

    @RequestMapping(value = "/viewAgencies", method = RequestMethod.GET)
    public String viewAllAgencies(HttpServletRequest request, HttpServletResponse response) {
        List<Agency> agencyList = biometricService.getAllAgencies();
        logger.info("Fetched Agency List to display");
        request.setAttribute("agencyList", agencyList);
        return "@viewAgencies";

    }

    @RequestMapping(value = "/publishAgency", method = RequestMethod.GET)
    public String publishAgency(HttpServletRequest request, HttpServletResponse response,
                                Model model, @ModelAttribute(value = "publishAgencyForm") BiometricAdminControllerPublishAgencyForm publishAgencyForm, BindingResult bindingResult) {
        if (request.getParameter("agencyId") != null) {
            int agencyId = Integer.parseInt(request.getParameter("agencyId"));
            Agency agency = biometricService.getAgencyForId(agencyId);
            publishAgencyForm.setAgencyName(agency.getAgencyName());
            publishAgencyForm.setPublicKey(agency.getPublicKey());
            publishAgencyForm.setPrivateKey(agency.getPrivateKey());
            publishAgencyForm.setAgencyId(agencyId);
            request.setAttribute("edit",true);
        }
        return "@publishAgency";
    }

    @RequestMapping(value = "/publishAgency", method = RequestMethod.POST)
    public String getPublishAgencyAndInsertIntoDatabase(HttpServletRequest request, HttpServletResponse response,
                                                        Model model, @ModelAttribute(value = "publishAgencyForm") BiometricAdminControllerPublishAgencyForm publishAgencyForm, BindingResult bindingResult) {

        agencyFormValidator.validate(publishAgencyForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "@publishAgency";
        }

        Agency agency = new Agency();
        agency.setAgencyName(publishAgencyForm.getAgencyName());
        agency.setPrivateKey(publishAgencyForm.getPrivateKey());
        agency.setPublicKey(publishAgencyForm.getPublicKey());
        agency.setAgencyId(publishAgencyForm.getAgencyId());
        try {
            biometricService.addAgency(agency);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("agencyName", "data.integrity");
            return "@publishAgency";
        }
        return "redirect:viewAgencies";

    }

    @RequestMapping(value = "/deleteAgency", method = RequestMethod.POST)
    public String deleteAgency(HttpServletRequest request, HttpServletResponse response) {
        int agencyId = Integer.parseInt(request.getParameter("agencyId"));
        Agency agency = new Agency();
        agency.setAgencyId(agencyId);
        biometricService.deleteAgency(agency);
        return "redirect:viewAgencies";

    }

    @RequestMapping(value = "/resetAadharNumber", method = RequestMethod.GET)
    public String getAadharNumberToDeleteFromDatabase(HttpServletRequest request, HttpServletResponse response,
                                                      Model model, @ModelAttribute(value = "aadharForm") AadharNumberForm aadharForm, BindingResult bindingResult) {

        return "@resetAadharNumber";
    }

    @RequestMapping(value = "/resetAadharNumber", method = RequestMethod.POST)
    public String deleteAadharNumber(HttpServletRequest request, HttpServletResponse response,
                                     Model model, @ModelAttribute(value = "aadharForm") AadharNumberForm aadharForm, BindingResult bindingResult) {
        aadharNumberFormValidator.validate(aadharForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "@resetAadharNumber";
        }
        String aadharNumber = aadharForm.getAadharNumber();
        biometricService.resetAadharNumber(aadharNumber);
        request.setAttribute("success", true);
        return "@resetAadharNumber";
    }

    }





