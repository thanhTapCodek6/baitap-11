package vn.nhutthanh.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class Users_24162118 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password")
    private String password;

    @Column(name = "images")
    private String images;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private int status;

    @Column(name = "code")
    private String code;

    @Column(name = "roleId")
    private int roleId;

    @Column(name = "sellerid")
    private Integer sellerid;

    public Users_24162118() {}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public Integer getSellerid() { return sellerid; }
    public void setSellerid(Integer sellerid) { this.sellerid = sellerid; }
}