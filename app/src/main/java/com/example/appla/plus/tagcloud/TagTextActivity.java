package com.example.appla.plus.tagcloud;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appla.plus.R;
import com.example.appla.plus.db.Information;
import com.example.appla.plus.db.School;
import com.example.appla.plus.db.Speciality;
import com.example.appla.plus.http.HttpUtil;
import com.example.appla.plus.http.Info;
import com.example.appla.plus.http.Utility;

import org.litepal.crud.DataSupport;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

public class TagTextActivity extends AppCompatActivity {

    public static final int LEVEL_SCHOOL = 0;

    public static final int LEVEL_INFOMATION = 1;

    public static final int LEVEL_Speciality = 2;

    private TextView titleView;

    private Button backButton;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    /**
     * 学校列表
     */
    private List<School> schoolList;

    /**
     * 信息列表
     */
    private List<Information> informationList;

    /**
     * 专业列表
     */
    private List<Speciality> specialityList;

    //选中的学校
    private School selectedSchool;

    //选中的信息
    private Information selectedInfomation;

    //当前选中的级别
    private int currentLevel;

    private List<Item> list = new ArrayList<>();    //专业信息

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

//        initItem();
        ItemAdapter adapter = new ItemAdapter(TagTextActivity.this,R.layout.list_item,list);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String infoString = prefs.getString("info",null);
        if (infoString!=null){
            //有缓存时直接解析数据
            Info info = Utility.handleInfoResponse(infoString);
            initItem(info);
        }else {
            //无缓存时去服务器查询天气
            String itemId = getIntent().getStringExtra("itemId");
            requestInfo(itemId);
        }

//        //发起网络请求
//        HttpUtil.sendOkHttpRequest("http://127.0.0.1:8000/info/?id=20&word=jieshao&count=0\n",new okhttp3.Callback(){
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //得到服务器返回的具体数据
//
////                String responseData = response.body().string();
////                showResponse(responseData);
////                Log.d(responseData,"12345");
//
//            }
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //进行异常处理
//            }
//        });
    }

    private void initItem(Info info){

        String specialityName = info.basic.informationName;
        String title = info.now.title;
        String titleItem = info.now.titleItem;
        String updateTime = info.basic.update.updateTime.split(" ")[1];

        for(int i = 0; i < specialityList.size(); i++){
            for (int j = 0; j < 2; j++){
                Item item1 = new Item(title,titleItem);
                list.add(item1);
            }
        }
    }

    //根据Info的id请求IInformation
    public void requestInfo(final String infoId){
        String infoUrl = "https://api.heweather.net/s6/weather/now?location=beijing&key=ea01c395140b4765b8867e3e09f08e76";

//                + infoId + "";////
        HttpUtil.sendOkHttpRequest(infoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TagTextActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Info info = Utility.handleInfoResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(info!=null&&"ok".equals(info.statue)){
                            Log.d("return", responseText);
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(TagTextActivity.this).edit();
                            editor.putString("info",responseText);
                            editor.apply();
                            initItem(info);
                        }else {
                            Toast.makeText(TagTextActivity.this,"获取信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //判断从服务器传回来的文件类型
    private void type(Response type){

    }

    //查询所有学校
    private void querySchool(){
        schoolList = DataSupport.findAll(School.class);
        if (schoolList.size() > 0){
            dataList.clear();
            for(School school : schoolList){
                dataList.add(school.getSchoolName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_SCHOOL;
        }else {
            String address = "";
            queryFromServer(address,"school");
        }
    }

    //查询所有信息
    private void queryInfomation(){
        informationList = DataSupport.where("schoolId=?",String.valueOf(selectedSchool.getId())).find(Information.class);
        if (informationList.size() > 0){
            dataList.clear();
            for(Information information : informationList){
                dataList.add(information.getInformationName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_INFOMATION;
        }else {
            int infomationCode = selectedSchool.getSchoolCode();
            String address = ""+infomationCode;////
            queryFromServer(address,"infomation");
        }
    }

    //查询所有专业
    private void querySpeciality(){
        specialityList = DataSupport.where("informationId=?",String.valueOf(selectedInfomation.getId())).find(Speciality.class);
        if (specialityList.size() > 0){
            dataList.clear();
            for(Speciality speciality : specialityList){
                dataList.add(speciality.getSpecialityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_Speciality;
        }else {
            int specialityCode = selectedSchool.getSchoolCode();
            String address = ""+specialityCode;////
            queryFromServer(address,"speciality");
        }
    }

    //跟据传入的地址和类型在服务器上查询
    private void queryFromServer(String address, final String type){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //通过方法返回到主线程
                TagTextActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                if ("school".equals(type)){
                    result = Utility.handleSchoolResponse(responseText);
                }else if ("infomation".equals(type)){
                    result = Utility.handleInfomationResponse(responseText,selectedSchool.getId());
                }else if ("Speciality".equals(type)){
                    result = Utility.handleSpecialityResponse(responseText,selectedInfomation.getId());
                }
                if (result){
                    TagTextActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("school".equals(type)){
                                querySchool();
                            }else if ("infomation".equals(type)){
                                queryInfomation();
                            }else if ("speciality".equals(type)){
                                querySpeciality();
                            }
                        }
                    });
                }
            }
        });
    }

}
