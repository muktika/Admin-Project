package com.companyname.biometricAdmin.model;

import com.companyname.biometric.enums.UserRole;

import javax.persistence.*;

/**
 * Created by shanky on 30/12/15.
 */
@Entity
@Table(name = "user_role_allocation")
public class UserRoleAllocation {
    @Id
    @Column(name = "user_role_allocation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleAllocationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", nullable = false)
    private AdminUser adminUser;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role", nullable = false)
    private UserRole userRole;

    public UserRoleAllocation(){

    }

    public UserRoleAllocation(int userRoleAllocationId, AdminUser adminUser, UserRole userRole) {
        this.userRoleAllocationId = userRoleAllocationId;
        this.adminUser = adminUser;
        this.userRole = userRole;
    }

    public int getUserRoleAllocationId() {
        return userRoleAllocationId;
    }

    public void setUserRoleAllocationId(int userRoleAllocationId) {
        this.userRoleAllocationId = userRoleAllocationId;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserRoleName(UserRole userRole) {
        return userRole.name();
    }

    @Override
    public String toString() {
        return "UserRoleAllocation{" +
                "userRoleAllocationId=" + userRoleAllocationId +
                ", userRole=" + userRole +
                '}';
    }
}
