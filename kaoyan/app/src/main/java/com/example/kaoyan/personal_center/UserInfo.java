package com.example.kaoyan.personal_center;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaoyan.R;
import com.example.kaoyan.utils.UserBean;

public class UserInfo extends AppCompatActivity implements View.OnClickListener {


    private TextView text_title,tv_back;
    private Button button_backward;
    //private SwipeBackLayout layout;
    private TextView tv_nickName, tv_signature, tv_user_name, tv_sex;
    private RelativeLayout rl_nickName, rl_sex, rl_signature,rl_head, layout_title_bar;
    private String spUserName;
    private static final int CHANGE_NICKNAME = 1;   //修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE = 2; //修改签名的自定义常量
    private static final int CROP_PHOTO1 = 3;       //裁剪图片
    private static final int CROP_PHOTO2 = 4;       //裁剪本地图片
    private static final int SAVE_PHOTO = 5;        //修改签名的自定义常量
    private ImageView iv_photo;
    private Bitmap head;                                //头像Bitmap
    private static String path = "/sdcard/TopLine/myHead/"; //sd路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
//                R.layout.base, null);
//        layout.attachToActivity(this);
        setContentView(R.layout.activity_user_info);
        //从SharedPreferences中获取登录时的用户名
        //spUserName = UtilsHelper.readLoginUserName(this);
        init();
        //initData();
        //setListener();
    }
    private void init(){
        text_title= (TextView)findViewById(R.id.text_title);
        text_title.setText("个人资料");
        layout_title_bar= (RelativeLayout)findViewById(R.id.layout_title_bar);
        layout_title_bar.setBackgroundColor(getResources().getColor(R.color.themeColor));
        button_backward= (Button) findViewById(R.id.button_backward);
        button_backward.setVisibility(View.VISIBLE);
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
        rl_head= (RelativeLayout) findViewById(R.id.rl_head);
        iv_photo = (ImageView) findViewById(R.id.iv_head_icon);
    }

    /**
     * 获取数据
     */
//    private void initData() {
//        UserBean bean = null;
//        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
//        //首先判断一下数据库是否有数据
//        if (bean == null) {
//            bean = new UserBean();
//            bean.setUserName(spUserName);
//            bean.setNickName("问答精灵");
//            bean.setSex("男");
//            bean.setSignature("传智播客问答精灵");
//            iv_photo.setImageResource(R.drawable.default_head);
//            //保存用户信息到数据库
//            DBUtils.getInstance(this).saveUserInfo(bean);
//        }
//        setValue(bean);
//    }
    /**
     * 为界面控件设置值
     */
    private void setValue(UserBean bean) {
        tv_nickName.setText(bean.getNickName());
        tv_user_name.setText(bean.getUserName());
        tv_sex.setText(bean.getSex());
        tv_signature.setText(bean.getSignature());
        Bitmap bt = BitmapFactory.decodeFile(bean.getHead()); //从SD卡中找头像，转换成Bitmap
//        if (bt != null) {
//            @SuppressWarnings("deprecation")
//            Drawable drawable = new BitmapDrawable(bt);      //转换成drawable
//            iv_photo.setImageDrawable(drawable);
//        } else {
//            iv_photo.setImageResource(R.drawable.default_head);
//        }
    }
    /**
     * 设置控件的点击监听事件
     */
    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
        rl_head.setOnClickListener(this);
    }
    /**
     * 控件的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backward:      //返回键的点击事件
                this.finish();
                break;
            case R.id.rl_nickName: //昵称的点击事件
                String name = tv_nickName.getText().toString(); //获取昵称控件上的数据
                Bundle bdName = new Bundle();
                bdName.putString("content", name);                //传递界面上的昵称数据
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);                           //flag传递1时表示是修改昵称
                //跳转到个人资料修改界面
                //enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME, bdName);
                break;
//            case R.id.rl_sex:       //性别的点击事件
//                String sex = tv_sex.getText().toString(); //获取性别控件上的数据
//                sexDialog(sex);
//                break;
//            case R.id.rl_signature:  //签名的点击事件
//                String signature = tv_signature.getText().toString(); //获取签名控件上的数据
//                Bundle bdSignature = new Bundle();
//                bdSignature.putString("content", signature);            //传递界面上的签名数据
//                bdSignature.putString("title", "签名");
//                bdSignature.putInt("flag", 2);                            //flag传递2时表示是修改签名
//                //跳转到个人资料修改界面
//                enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE, bdSignature);
//                break;
//            case R.id.rl_head:       //头像的点击事件
//                showTypeDialog();
//                break;
            default:
                break;
        }
    }

}
