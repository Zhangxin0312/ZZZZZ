package com.bw.movie.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.PageListActivity;
import com.bw.movie.activity.RecordFeedBackActivity;
import com.bw.movie.activity.RecordListActivity;
import com.bw.movie.activity.UserInfoActivity;
import com.bw.movie.activity.XiTongActivity;
import com.bw.movie.bean.XiTongBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.opensdk.utils.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/11 10:32
 * @Description：描述信息
 */
public class MyFragment extends Fragment implements ContractInter.MyInter {
    @BindView(R.id.my_tou)
    SimpleDraweeView my_tou;
    @BindView(R.id.my_user_info)
    LinearLayout my_user_info;
    @BindView(R.id.signIn_id)
    Button signIn_id;
    @BindView(R.id.recordList_id)
    LinearLayout recordList_id;
    @BindView(R.id.page_id)
    LinearLayout page_id;
    @BindView(R.id.feed_back_id)
    LinearLayout feed_back_id;
    ImageView xitong_id;
    ContractInter.PresenterInter presenterInter;
    ImageView myversion;
    ImageView tuichu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_my,container,false);
        ButterKnife.bind(this,view);
        xitong_id=view.findViewById(R.id.xitong_id);
        myversion=view.findViewById(R.id.myversion);
        tuichu=view.findViewById(R.id.tuichu);
        my_tou.setImageResource(R.mipmap.mytou);
        presenterInter=new Presenter<>(this);
        //我的信息
        initData1();
        //我的关注
        initData2();
        //购票记录
        initData3();
        //意见反馈
        initData4();
        //版本更新
        initData7();
        //退出登录
        initData8();
        return view;
    }

    private void initData8() {
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData7() {
        myversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVersionCode() < 3) {
                    showDialogUpdate();//弹出提示版本更新的对话框

                }else{
                    //否则吐司，说现在是最新的版本
                    Toast.makeText(getContext(),"当前已经是最新的版本",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //我的信息
    public void initData1() {
        my_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInfoActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        //签到
        signIn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterInter.toSignIn();
            }
        });
    }
    //我的关注
    public void initData2() {
        page_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PageListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    //购票记录
    public void initData3() {
        recordList_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getActivity(),RecordListActivity.class);
               startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    //意见反馈
    public void initData4() {
        feed_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RecordFeedBackActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
   //签到
    @Override
    public void MySign(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xitong_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),XiTongActivity.class);
                startActivity(intent);
            }
        });

    }
    public void checkVersion(View view) {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新

    }
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.mipmap.ic_launcher).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }
    private void loadNewVersionProgress() {
        final   String uri="http://172.17.8.100/media/movie.apk";
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Toast.makeText(getContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }}.start();
    }
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(uri);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time= System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time+"updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }
    private int getVersionCode() {
        try {

            //获取packagemanager的实例
            PackageManager packageManager = getContext().getPackageManager();
            //getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            Log.e("TAG", "版本号" + packInfo.versionCode);
            Log.e("TAG", "版本名" + packInfo.versionName);
            return packInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return  1;
    }
}
