package com.example.kaoyan.db;

import org.litepal.crud.DataSupport;

public class Speciality extends DataSupport {

    private int id;

    private String SpecialityName; //当前专业的标签

    private int specialityCode;  //当前所属专业的Code

    private int infomationId; //记录当前所属信息的Id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return SpecialityName;
    }

    public void setSpecialityName(String specialityName) {
        SpecialityName = specialityName;
    }

    public int getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(int specialityCode) {
        this.specialityCode = specialityCode;
    }

    public int getInfomationId() {
        return infomationId;
    }

    public void setInfomationId(int infomationId) {
        this.infomationId = infomationId;
    }
}
