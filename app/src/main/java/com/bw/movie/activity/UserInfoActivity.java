package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HeadPicBean;
import com.bw.movie.bean.UserInfoBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity implements ContractInter.UserInfoInter {
    @BindView(R.id.my_tou_id)
    SimpleDraweeView my_tou_id;
    @BindView(R.id.my_name_id)
    TextView my_name_id;
    @BindView(R.id.my_sex_id)
    TextView my_sex_id;
    @BindView(R.id.my_date_id)
    TextView my_date_id;
    @BindView(R.id.my_phone_id)
    TextView my_phone_id;
    @BindView(R.id.my_email_id)
    TextView my_email_id;
    @BindView(R.id.reset_pwd_id)
    LinearLayout reset_pwd_id;
    @BindView(R.id.user_back)
    ImageView user_back;
    ContractInter.PresenterInter presenterInter;
    UserInfoActivity activity;
     File file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        activity=this;
        ButterKnife.bind(this);
        my_tou_id.setImageResource(R.mipmap.mytou);
        //实例化
        presenterInter=new Presenter<>(this);
        //根据Id查询个人信息
        presenterInter.getUserInfo();
        //重置密码
        initData();
        //返回
        initData1();
        //上传头像
        initData2();
    }
    @Override
    public void MyUser(UserInfoBean userInfoBean) {
        UserInfoBean.ResultBean result = userInfoBean.getResult();
        //头像
        my_tou_id.setImageURI(result.getHeadPic());
        //名字
        my_name_id.setText(result.getNickName());
        //性别
        if(result.getSex()==1){
            my_sex_id.setText("男");
        }else
        {
            my_sex_id.setText("女");
        }
        //日期
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(result.getBirthday());
        my_date_id.setText(format.format(date));
        //手机号
        my_phone_id.setText(result.getPhone());
        //Email
        my_email_id.setText(result.getEmail());

    }
    //重置密码
    public void initData() {
        reset_pwd_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserInfoActivity.this,PwdActivity.class);
                startActivity(intent);
            }
        });
    }
    //返回
    public void initData1() {
        user_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
    private void initData2() {
        my_tou_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case  1:
                Uri uri = data.getData();
                //把Uri转化为Bitmap格式
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    //创建文件夹  存入本地 在上传服务器
                    String path= Environment.getExternalStorageDirectory()+"/ABC";
                    File file=new File(path);
                    //判断f文件夹是不是存在
                    if(!file.exists()){
                        file.mkdir();
                    }
                    file1 = new File(file,"111.png");
                    //上传服务器
                    BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file1));
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                    bos.flush();
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //上传头像
                 presenterInter.getHeadPic(file1);
                break;
        }
    }
    //上传头像
    @Override
    public void MyHeadPic(HeadPicBean headPicBean) {
        my_tou_id.setImageURI(headPicBean.getHeadPath());
    }
}
