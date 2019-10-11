package com.example.kaoyan.utils;

public class UserBean {
    private String userName;   //用户名
    private String nickName;   //昵称
    private String sex;         //性别
    private String signature; //签名
    private String head;       //头像
    private  String global_school; //目标院校
    private  String global_major ;//目标专业
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public String getGlobalSchool(){
        return global_school;
    }
    public void setGlobalSchool(String global_school) {
        this.global_school = global_school;
    }
    public String getGlobalMajor(){
        return global_major;
    }
    public void setGlobalMajor(String global_major) {
        this.global_major = global_major;
    }

}
