package com.companyname.biometricAdmin.services;

import com.companyname.apisClient.ApiClient;
import com.companyname.biometricAdmin.dao.BiometricDao;
import com.companyname.biometricAdmin.model.Agency;
import com.companyname.model.companynameApis.v1.ApiCreateSchedule;
import com.companyname.model.companynameApis.v1.responses.ApiAssessmentResponse;
import com.companyname.model.companynameApis.v1.responses.ApiCreateSchedulesResponse;
import com.companyname.model.companynameApis.v1.responses.ApiRegisterCandidatesResponse;
import com.companyname.model.companynameApis.v1.responses.ApiScheduleTestCandidateStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class ApiClientService {
    
    @Resource
    private BiometricDao biometricDao;
    
    private Map<Integer, ApiClient> apiClientMap;
    private String apiVersion;
    private String apiBaseUrl;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }
    
    @PostConstruct
    public void init() {
    	apiClientMap = new HashMap<>();
    }

    private ApiClient _getApiClient(int agencyId){
    	logger.info("Retrieving api client for agency id {}", agencyId);
		ApiClient apiClient = apiClientMap.get(agencyId);
		if(apiClient == null){
			logger.info("No api client for agency id {} hence creating api client for this agency id ", agencyId);
			Agency agency = biometricDao.getAgencyData(agencyId);
			apiClient = ApiClient.getInstance(agencyId, agency.getPublicKey(), agency.getPrivateKey(), apiVersion, apiBaseUrl);
			apiClientMap.put(agencyId, apiClient);
		}
		return apiClient;
	}

	public ApiAssessmentResponse getAssessmentById(int agencyId, int assessmentId) {
		return _getApiClient(agencyId).getAssessmentById(assessmentId);
	}
	
}
