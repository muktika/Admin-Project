package com.companyname.biometricAdmin.services;

import com.companyname.biometricAdmin.dao.UserDao;
import com.companyname.biometricAdmin.model.AdminUser;
import com.companyname.biometricAdmin.model.UserRoleAllocation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shanky on 30/12/15.
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        AdminUser user = userDao.findByUserName(username);
        if(user==null){
            return null;
        }
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRoleAllocations());

        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(AdminUser user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRoleAllocation> userRoleAllocations) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRoleAllocation userRoleAllocation : userRoleAllocations) {
            setAuths.add(new SimpleGrantedAuthority(userRoleAllocation.getUserRole().name()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}

