package com.companyname.biometricAdmin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by muktika on 22/12/15.
 */

@Entity
@Table(name = "AdminUser")
public class AdminUser {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @Column(name="email_id", nullable=false, unique = true)
    private String email;

    @Column(name="password", nullable = false, columnDefinition="VARCHAR")
    private String password;

    @Column(name="enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "adminUser")
    private Set<UserRoleAllocation> roleAllocations;

    public AdminUser(){
    }

    public AdminUser(Date createdAt, String email, String password, boolean enabled) {
        this.createdAt = createdAt;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoleAllocation> getRoleAllocations() {
        return roleAllocations;
    }

    public void setRoleAllocations(Set<UserRoleAllocation> roleAllocations) {
        this.roleAllocations = roleAllocations;
    }

    public List<String> getRolesDisplay(){
        List<String> roleList = new ArrayList<>();
        Set<UserRoleAllocation> userRoles = this.getRoleAllocations();
        for(UserRoleAllocation role : userRoles){
            roleList.add(role.getUserRole().name());
        }
        return roleList;

    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "adminId=" + adminId +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roleAllocations=" + roleAllocations +
                '}';
    }
}
