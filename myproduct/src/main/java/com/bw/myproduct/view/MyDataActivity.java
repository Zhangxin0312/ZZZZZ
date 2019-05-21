package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.DataBean;
import com.bw.myproduct.beans.ImageBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyDataActivity extends AppCompatActivity  implements ContractInterface.MyDataInter {
     SharedPreferences sp;
     int userId;
     String sessionId;
     ContractInterface.MyPresenterInter myPresenterInter;
     SimpleDraweeView my_tou_id;
     TextView my_name_id,my_pwd_id;
     EditText my_edit_id,new_pwd_id,old_pwd_id,newName;
     Button up_btn,up_pwd_btn,data_confirm,data_cancle,old_pwd_cancle;
     String pwd;
     PopupWindow pop;
     DataBean dataBean;
     File file1;
     DataBean.ResultBean result;
     SimpleDraweeView update_tou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        my_tou_id=findViewById(R.id.my_tou_id);
        my_name_id=findViewById(R.id.my_name_id);
        my_pwd_id=findViewById(R.id.my_pwd_id);
        up_btn=findViewById(R.id.up_btn);
        //获取userId和sessionId
        sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        pwd = sp.getString("pwd", null);
        sessionId = sp.getString("sessionId", null);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //去请求我的资料
        myPresenterInter.Datas(userId,sessionId);
        //上传头像
         toHead();
         //修改昵称
         upName();
         //修改密码
         upPwd();
    }
    @Override
    public void MyData(DataBean dataBean) {
        this.dataBean=dataBean;
        //得到返回的数据 设置值
        result = dataBean.getResult();
        my_tou_id.setImageURI(result.getHeadPic());
        my_name_id.setText(result.getNickName());
        my_pwd_id.setText(pwd);

        //第二次调用的时候  把修改后的名字传回去
        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString("newName",dataBean.getResult().getNickName());
        edit.putString("newImage",dataBean.getResult().getHeadPic());
        edit.commit();
    }
    //修改昵称
    public void upName() {
        my_edit_id=findViewById(R.id.my_edit_id);
        my_name_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(MyDataActivity.this,R.layout.my_data_upname,null);
                final PopupWindow pop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                pop.setFocusable(true);
                newName = view.findViewById(R.id.newName);
                data_cancle = view.findViewById(R.id.data_cancle);
                data_confirm = view.findViewById(R.id.data_confirm);
                String name = my_name_id.getText().toString();
                newName.setText(name);
                data_confirm.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                          String names = newName.getText().toString();
                          myPresenterInter.upNikeName(userId,sessionId,names);
                     }
                 });
                 data_cancle.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         pop.dismiss();
                     }
                 });
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
               }
           });
        }
    //修改昵称的结果
    @Override
    public void MyUpName(String str) {
        Toast.makeText(MyDataActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("修改成功")){
            myPresenterInter.Datas(userId,sessionId);
            //返回我的页面
            Intent intent=new Intent(MyDataActivity.this,ShowActivity.class);
            intent.putExtra("flag",5);
            startActivity(intent);
        }
    }
    //修改密码
    private void upPwd() {
        my_pwd_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 View view1=View.inflate(MyDataActivity.this,R.layout.up_pwd_item,null);
                pop = new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                  pop.setFocusable(true);
                 //获取旧密码  新密码  button的id
                  old_pwd_cancle=view1.findViewById(R.id.old_pwd_cancle);
                  old_pwd_id=view1.findViewById(R.id.old_pwd_id);
                  new_pwd_id=view1.findViewById(R.id.new_pwd_id);
                  up_pwd_btn=view1.findViewById(R.id.up_pwd_btn);
                  old_pwd_id.setText(my_pwd_id.getText().toString());
                 //点击事件
                  up_pwd_btn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          String oldName = old_pwd_id.getText().toString();
                          String newName = new_pwd_id.getText().toString();
                          myPresenterInter.upPwd(userId,sessionId,oldName,newName);
                      }
                  });
                old_pwd_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                  pop.setBackgroundDrawable(new BitmapDrawable());
                  pop.setOutsideTouchable(true);
                  pop.showAtLocation(view1,Gravity.CENTER,0,0);
            }
        });
    }
    //修改密码的结果
    @Override
    public void MyUpPwd(String str) {
        Toast.makeText(MyDataActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("修改成功")) {
            //修改成功 关闭PopupWindow
            pop.dismiss();
            //重新登录  显示新密码
            Intent intent=new Intent(MyDataActivity.this,MainActivity.class);
            startActivity(intent);
          }
        }
        //上传头像
        public  void toHead() {
        //点击头像  进行修改
            my_tou_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹出一个popupWindow
                    View view=View.inflate(MyDataActivity.this,R.layout.data_pop_tou,null);
                    final PopupWindow pop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    update_tou = view.findViewById(R.id.update_tou);
                    update_tou.setImageURI(result.getHeadPic());
                    //修改头像
                    update_tou.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //第一步  跳转手机相册
                            Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent, 2);

                        }
                    });
                    pop.setBackgroundDrawable(new BitmapDrawable());
                    pop.setOutsideTouchable(true);
                    pop.showAtLocation(view,Gravity.CENTER,0,0);
                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case  2:
                Uri uri = data.getData();
                crop(uri);
                break;
            case 3:
                //从相册中取出照片
                Bitmap bitmap = data.getParcelableExtra("data");
                //把图片转成File格式
                String path= Environment.getExternalStorageDirectory()+"/zhangxin";
                Log.e("tag" ,path);
                 File  file = new File(path);//将要保存图片的路径
                if (file.exists()) {
                    Log.e("tag" ,"已存在");
                }else {
                    file.mkdirs();
                }
                if(file.exists()){
                    file1 = new File(file , "1234.png");
                }
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPresenterInter.upImage(userId,sessionId,file1);
                break;
        }
    }
    public  void crop(Uri  uri){
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 3);
    }
   //上传头像
    @Override
    public void MyUpImage(ImageBean imageBean) {
        update_tou.setImageURI(imageBean.getHeadPath());
        myPresenterInter.Datas(userId,sessionId);
        if(imageBean.getMessage().equals("上传成功")){
             //返回我的页面
             Intent intent=new Intent(MyDataActivity.this,ShowActivity.class);
             intent.putExtra("flag",5);
             startActivity(intent);
        }


    }
}

