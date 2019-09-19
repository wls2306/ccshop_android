package com.bcu.ccshop.model;

import java.util.Date;

public class User {
    /**
     * 此项
     */
    private String userId;

    /**
     * 小程序openId
     */
    private String userOpenid;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户性别
     * 男1
     * 女2
     */
    private Byte userSex;

    /**
     * 用户生日
     */
    private String userBirthday;

    /**
     * 用户邮箱号码
     */
    private String userEmail;

    /**
     * 用户手机号码
     */
    private String userPhoneCall;

    /**
     * 用户住址
     */
    private String userAddress;

    /**
     * 用户积分
     */
    private Integer userScore;

    /**
     * 用户类别
     */
    private Integer userType;

    /**
     * 用户状态
     */
    private Integer userStatus;

    /**
     * 注册ip
     */
    private String userRegisterIp;

    /**
     * 注册时间
     */
    private Date userRegisterTime;

    /**
     * 最后一次登陆ip
     */
    private String userLastLoginIp;

    /**
     * 最后一次登陆时间
     */
    private Date userLastLoginTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneCall() {
        return userPhoneCall;
    }

    public void setUserPhoneCall(String userPhoneCall) {
        this.userPhoneCall = userPhoneCall;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserRegisterIp() {
        return userRegisterIp;
    }

    public void setUserRegisterIp(String userRegisterIp) {
        this.userRegisterIp = userRegisterIp;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserLastLoginIp() {
        return userLastLoginIp;
    }

    public void setUserLastLoginIp(String userLastLoginIp) {
        this.userLastLoginIp = userLastLoginIp;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public String getUserPassword() {return userPassword;}

    public void setUserPassword(String userPassword) {this.userPassword = userPassword; }
}