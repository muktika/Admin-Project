package com.companyname.biometricAdmin.controllers;

import com.companyname.biometric.enums.UserRole;
import com.companyname.biometricAdmin.model.AdminUser;
import com.companyname.biometricAdmin.model.BiometricAdminControllerAddAdminUserForm;
import com.companyname.biometricAdmin.model.UserRoleAllocation;
import com.companyname.biometricAdmin.services.BiometricService;
import com.companyname.biometricAdmin.validators.BiometricAdminControllerAddAdminUserFormValidator;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by muktika on 31/12/15.
 */

@Controller
@RequestMapping(value = "/superuser/admin")
public class BiometricSuperUserController {

    @Resource
    BiometricService biometricService;

    @Resource
    private BiometricAdminControllerAddAdminUserFormValidator addAdminUserFormValidator;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    private String homePage(HttpServletRequest request) {

        return "@superUserHome";
    }

    @RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
    private String viewAllAdminUsers(HttpServletRequest request) {

        List<AdminUser> adminUsers = biometricService.getAllAdminUsers();
        request.setAttribute("AdminUserList", adminUsers);
        return "@viewUsers";
    }

    @RequestMapping(value = "/addAdminUser", method = RequestMethod.GET)
    private String addAdminUser(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute(value = "addAdminUserForm") BiometricAdminControllerAddAdminUserForm addAdminUserForm, BindingResult bindingResult) {

        List<String> roles = new ArrayList<>();
        roles = getRolesFromEnum();
        request.setAttribute("roles", roles);
        return "@addAdminUser";
    }

    private List<String> getRolesFromEnum(){
        List<String> roles = new ArrayList<>();
        for (UserRole role : UserRole.values()) {
            roles.add(role.name());
        }
        return roles;
    }

    @RequestMapping(value = "/addAdminUser", method = RequestMethod.POST)
    private String addNewAdminUser(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute(value = "addAdminUserForm") BiometricAdminControllerAddAdminUserForm addAdminUserForm, BindingResult bindingResult) {

        addAdminUserFormValidator.validate(addAdminUserForm, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> roles = getRolesFromEnum();
            request.setAttribute("roles", roles);
            return "@addAdminUser";
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setCreatedAt(new Date());
        adminUser.setEnabled(true);
        adminUser.setEmail(addAdminUserForm.getEmail());
        adminUser.setPassword(addAdminUserForm.getPassword());
        try{
            biometricService.addAdminUser(adminUser);
        }catch(DataIntegrityViolationException ex){
            List<String> roles = getRolesFromEnum();
            request.setAttribute("roles", roles);
            bindingResult.rejectValue("email","data.integrity");
            return "@addAdminUser";
        }
        List<String> roles = addAdminUserForm.getRoleAllocations();
        for(String rolesString : roles) {
            UserRoleAllocation roleAllocations = new UserRoleAllocation();
            UserRole role = UserRole.valueOf(rolesString);
            roleAllocations.setUserRole(role);
            roleAllocations.setAdminUser(adminUser);
            biometricService.addUserRoles(roleAllocations);
        }
        return "redirect:viewUsers";
    }


    @RequestMapping(value = "/editUserRole", method = RequestMethod.GET)
    private String editAdminUserRole(HttpServletRequest request) {

        int adminId = Integer.parseInt(request.getParameter("adminId"));
        List<UserRoleAllocation> rolesList = biometricService.getUserRoles(adminId);
        request.setAttribute("adminId",adminId);
        request.setAttribute("rolesList",rolesList);
        return "@editUserRole";
    }
}