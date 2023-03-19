package org.credex.hiring.portal.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role_table")
public class Role {
    public Role(String roleId, String roleName, Timestamp createdDate) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role")
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "role_name")
    private String roleName;
    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;
//    @OneToOne(mappedBy = "roleId")
//    private Users users;

}
