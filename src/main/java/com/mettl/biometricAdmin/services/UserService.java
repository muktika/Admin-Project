package com.companyname.biometricAdmin.services;

import com.companyname.biometricAdmin.dao.BiometricDao;
import com.companyname.biometricAdmin.model.AdminUser;

import javax.annotation.Resource;
import javax.inject.Named;

/**
 * Created by gsagrawal on 20/01/16.
 */

@Named
public class UserService {

    @Resource
    BiometricDao biometricDao;

    public AdminUser getAdminUserCredentials(String email) {
        return biometricDao.getAdminUser(email);
    }

}
