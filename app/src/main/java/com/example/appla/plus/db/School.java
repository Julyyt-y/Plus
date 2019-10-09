package com.example.appla.plus.db;

import org.litepal.crud.DataSupport;

public class School extends DataSupport {

    private int id;

    private String schoolName;

    private int schoolCode;  //学校代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(int schoolCode) {
        this.schoolCode = schoolCode;
    }
}
