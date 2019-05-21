package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.GridAdapter;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssessActivity extends AppCompatActivity  implements ContractInterface.MyAssessInter {
     SimpleDraweeView assess_iv;
     TextView assess_title,assess_price;
     int commodityId,price;
     String orderId,name,image;
     EditText assess_etn;
     ImageView photo_id;
     Button pai_id,pink_id,cancle,report;
     GridView  gv_id;
     GridAdapter adapter;
     CheckBox sync_id;
     List<Bitmap>  mlist=new ArrayList<>();
     ContractInterface.MyPresenterInter myPresenterInter;
     int userId;
     String sessionId;
     File file, file1;;
      String  content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //获取订单Fragment 待评价 传来的值
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("commodityId", 0);
        orderId = intent.getStringExtra("orderId");
        price = intent.getIntExtra("price", 0);
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("pic");
        //从sp中取userId和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        //找到控件 并赋值
        initData();
    }
    private void initData() {
        //找到控件
          assess_iv=findViewById(R.id.assess_iv);
          assess_title=findViewById(R.id.assess_title);
          assess_price=findViewById(R.id.assess_price);
          assess_etn=findViewById(R.id.assess_etn);
          photo_id=findViewById(R.id.photo_id);
          gv_id=findViewById(R.id.gv_id);
          report=findViewById(R.id.report);
          sync_id=findViewById(R.id.sync_id);
          //赋值
          assess_price.setText("￥"+price);
          assess_title.setText(name);
          assess_iv.setImageURI(image);

          //从相册选择照片
          photo_id.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //弹出一个popupWindow
                  View view=View.inflate(AssessActivity.this,R.layout.photo_pop_ping,null);
                  final PopupWindow pop=new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                  pai_id=view.findViewById(R.id.pai_id);
                  cancle=view.findViewById(R.id.cancle);
                  pink_id=view.findViewById(R.id.pink_id);
                  //相机拍照
                  pai_id.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                          startActivityForResult(intent, 1);
                      }
                  });
                  //相册拍照
                  pink_id.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          //第一步  跳转手机相册
                          Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                          intent.setType("image/*");
                          startActivityForResult(intent, 2);
                      }
                  });
                  //GridView展示图片
                  adapter = new GridAdapter(AssessActivity.this,mlist);
                  gv_id.setAdapter(adapter);
                   //点击取消
                  cancle.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          pop.dismiss();
                      }
                  });
                  pop.setBackgroundDrawable(new BitmapDrawable());
                  pop.setOutsideTouchable(true);
                  pop.showAtLocation(view,Gravity.CENTER,0,0);
              }
          });
          report.setOnClickListener(new View.OnClickListener() {


              @Override
              public void onClick(View v) {
                  //获取输入框的内容
                   content = assess_etn.getText().toString();
                  //发布到圈子
                  if(sync_id.isChecked()){
                      myPresenterInter.Synch(userId,sessionId,commodityId,content,file1);
                      //待评价  需要userId  sessionId  commodityId   content  image
                      myPresenterInter.Report(userId,sessionId,commodityId,orderId,content,file1);
                  }else
                  {
                      //待评价  需要userId  sessionId  commodityId   content  image
                      myPresenterInter.Report(userId,sessionId,commodityId,orderId,content,file1);
                  }
              }
          });
    }
    //第二步就是返回的时候获取相册的路径
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 1:
               //相机拍照

                break;
            case 2:
                //从相册选择照片
                Uri uri = data.getData();
                //裁减图片
                crop(uri);
                break;
            case 3:
                //从相册中取出照片
                Bitmap bitmap = data.getParcelableExtra("data");
                //将得到的Bitmap放入集合中
                List<Bitmap>  list=new ArrayList<>();
                list.add(bitmap);
                mlist.addAll(list);
                adapter.notifyDataSetChanged();

                //把图片转成File格式  获取SD卡路径
                 String path= Environment.getExternalStorageDirectory()+"/zhangxin";
                 file = new File(path);//将要保存图片的路径
                //判断文件夹是否存在  如果存在打印log  如果没有存在 就创建文件夹
                if (file.exists()) {
                    Log.e("tag" ,"已存在");
                }else {
                    file.mkdir();
                }
                //如果文件夹存在  就将图片放进去
                if(file.exists()){
                    file1 = new File(file , "1234.png");
                }
                try {
                    //将图片用流写进去
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
                    //进行压缩
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    //刷新缓冲区
                    bos.flush();
                    //关闭流
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    @Override
    public void MyAssess(String str) {
        //评论的回调结果
        Toast.makeText(AssessActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("评论成功")){
            //如果评论成功   回到首页   根据flag判断要去的订单Fragment
             Intent intent=new Intent(AssessActivity.this,ShowActivity.class);
              intent.putExtra("flag",4);
             startActivity(intent);
        }
    }
    //同步到圈子
    @Override
    public void MySynch(String str) {
        if(str.equals("发布成功")){
            Toast.makeText(AssessActivity.this,str,Toast.LENGTH_SHORT).show();
        }
    }
}
