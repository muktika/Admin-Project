package com.companyname.biometricAdmin.dao;

import com.companyname.biometricAdmin.model.AdminUser;

/**
 * Created by gsagrawal on 20/01/16.
 */

public interface UserDao {

    AdminUser findByUserName(String username);


}
