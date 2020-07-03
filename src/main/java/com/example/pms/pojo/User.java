package com.example.pms.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@ApiModel("用户实体类")
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户ID")
    @Column(name = "id" ,length = 50)
    private Integer id;
    @ApiModelProperty("用户名")
    @Column()
    private String username;
    @ApiModelProperty("密码")
    @Column()
    private String password;
    @ApiModelProperty("token")
    @Column()
    private String token;
    @ApiModelProperty("登陆时间")
    @Column()
    private Date loginTime;
    @ApiModelProperty("角色id")
    @Column()
    private String roleid;
    @ApiModelProperty("角色")
    @Column()
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
