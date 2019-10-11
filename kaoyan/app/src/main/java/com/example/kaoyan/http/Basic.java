package com.example.kaoyan.http;

import com.google.gson.annotations.SerializedName;

public class Basic {

    //该注释让JSON字段和java字段之间建立映射关系
    @SerializedName("information")
    public String informationName;

    @SerializedName("id")
    public String infoId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;
    }
}
