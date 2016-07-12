package com.companyname.biometricAdmin.dao;

import com.companyname.biometricAdmin.model.AdminUser;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shanky on 30/12/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    public AdminUser findByUserName(String username) {

        List<AdminUser> users = hibernateTemplate.find("from AdminUser u where u.email = ?", username);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

}

