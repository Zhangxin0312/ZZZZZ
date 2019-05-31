package com.bw.movie.app;

import android.content.Context;

import com.bw.movie.bean.MovieScheduleListBean;
import com.bw.movie.bean.ShouBean;
import com.bw.movie.bean.ShowIngBean;
import com.bw.movie.bean.ShowOnBean;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 19:59
 * @Description：描述信息
 */
public class App {
    public static Context context;
    public static String SessionId;
    public static int UserId;
    public static long Birthday;
    public static long LastLoginTime;
    public static String NickName;
    public static String Phone;
    public static String Pwd;
    public static int Sex;
    public static String HeadPic;
    public  static List<ShouBean.ResultBean> list;
    public  static List<ShowIngBean.ResultBean> list1;
    public  static List<ShowOnBean.ResultBean> list2;
    public  static boolean flagg=false;
    public  static List<MovieScheduleListBean.ResultBean> list3;
    public  static String AppId;
    public static String Ximage;
    public static String Xtitle;
    public static String Xname;
    public static int CinemaId;
    public static String Chentime;
    public static String Chentimeend;
    public static String Chentitle;
    public static Double Chenprice;
    public static int MovieId;
}
