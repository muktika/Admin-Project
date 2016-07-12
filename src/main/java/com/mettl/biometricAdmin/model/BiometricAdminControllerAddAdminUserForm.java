package com.companyname.biometricAdmin.model;

import java.util.List;

/**
 * Created by muktika on 31/12/15.
 */

public class BiometricAdminControllerAddAdminUserForm {

    private String email;
    private String password;
    private List<String> roleAllocations;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleAllocations() {
        return roleAllocations;
    }

    public void setRoleAllocations(List<String> roleAllocations) {
        this.roleAllocations = roleAllocations;
    }
}
