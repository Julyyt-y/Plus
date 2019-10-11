package com.example.kaoyan.db;

import org.litepal.crud.DataSupport;

public class Information extends DataSupport {

    private int id;

    private String informationName; //当前信息的标签

    private int informationCode;  //当前所属信息的Id

    private int SchoolId; //记录当前所属学校的Id

//    private String xuexiaojianjie;
//
//    private String jiuyelv;
//
//    private String kaoshidagang;
//
//    private String kaoshikemu;
//
//    private String xuezhuanshuorenshu;
//
//    private String baolubi;
//
//    private String list_view;
//
//    private String tuimianbili;
//
//    private String fulubi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInformationCode() {
        return informationCode;
    }

    public void setInformationCode(int informationId) {
        this.informationCode = informationId;
    }

    public int getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(int schoolId) {
        SchoolId = schoolId;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    //    public String getXuexiaojianjie() {
//        return xuexiaojianjie;
//    }
//
//    public void setXuexiaojianjie(String xuexiaojianjie) {
//        this.xuexiaojianjie = xuexiaojianjie;
//    }
//
//    public String getJiuyelv() {
//        return jiuyelv;
//    }
//
//    public void setJiuyelv(String jiuyelv) {
//        this.jiuyelv = jiuyelv;
//    }
//
//    public String getKaoshidagang() {
//        return kaoshidagang;
//    }
//
//    public void setKaoshidagang(String kaoshidagang) {
//        this.kaoshidagang = kaoshidagang;
//    }
//
//    public String getKaoshikemu() {
//        return kaoshikemu;
//    }
//
//    public void setKaoshikemu(String kaoshikemu) {
//        this.kaoshikemu = kaoshikemu;
//    }
//
//    public String getXuezhuanshuorenshu() {
//        return xuezhuanshuorenshu;
//    }
//
//    public void setXuezhuanshuorenshu(String xuezhuanshuorenshu) {
//        this.xuezhuanshuorenshu = xuezhuanshuorenshu;
//    }
//
//    public String getBaolubi() {
//        return baolubi;
//    }
//
//    public void setBaolubi(String baolubi) {
//        this.baolubi = baolubi;
//    }
//
//    public String getFenshuxian() {
//        return list_view;
//    }
//
//    public void setFenshuxian(String list_view) {
//        this.list_view = list_view;
//    }
//
//    public String getFulubi() {
//        return fulubi;
//    }
//
//    public void setFulubi(String fulubi) {
//        this.fulubi = fulubi;
//    }
//
//    public String getTuimianbili() {
//        return tuimianbili;
//    }
//
//    public void setTuimianbili(String tuimianbili) {
//        this.tuimianbili = tuimianbili;
//    }
}
