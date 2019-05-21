package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.AssessAdapter;
import com.bw.movie.bean.PersonCard;
import com.bw.movie.adapter.StaggerAdapter;
import com.bw.movie.bean.AssessBean;
import com.bw.movie.bean.MovingBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity implements ContractInter.MovieInter,ContractInter.MovieXiangInter {
       @BindView(R.id.movie_iv)
       SimpleDraweeView movie_iv;
       @BindView(R.id.movie_name)
        TextView movie_name;
       @BindView(R.id.movie_back)
       ImageView movie_back;
       @BindView(R.id.xiang_id)
       Button  xiang_id;
       @BindView(R.id.herald_id)
       Button herald_id;
       @BindView(R.id.phone_id)
        Button phone_id;
        @BindView(R.id.assess_id)
        Button assess_id;
        @BindView(R.id.fllow_id)
       ImageView  fllow_id;
       @BindView(R.id.buy_id)
       Button buy_id;
       ContractInter.PresenterInter presenterInter;
       MovieActivity movieActivity;
       MovingBean.ResultBean result;
       List<AssessBean.ResultBean> mlist=new ArrayList<>();
     public AssessAdapter assessAdapter;
    int id;
    ImageView  ping_id;
    private EditText ping_etn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieActivity=this;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        ButterKnife.bind(this);

        //获取详情
        presenterInter=new Presenter<>(this);
        presenterInter.Xiang(id);
        presenterInter.Moving(id);
        //返回
        initData();
        //详情
        initData1();
        //预告
        initData2();
        //剧照
        initData3();
        //影评
        initData4();
        //购票
        initData5();
    }

    //返回
    public void initData() {
        movie_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieActivity.finish();
            }
        });
    }

    //设置影片名称和图片
    @Override
    public void MyXiang(XiangBean xiangBean) {
        movie_name.setText(xiangBean.getResult().getName());
        movie_iv.setImageURI(xiangBean.getResult().getImageUrl());
    }

    //详情
    private void initData1() {
        xiang_id.setOnClickListener(new View.OnClickListener() {
             PopupWindow pop;
            @Override
            public void onClick(View v) {
                View view=View.inflate(MovieActivity.this,R.layout.movie_view,null);
                ImageView moving_iv = view.findViewById(R.id.moving_iv);
                TextView  moving_name=view.findViewById(R.id.moving_name);
                TextView  moving_type = view.findViewById(R.id.moving_type);
                TextView  moving_author = view.findViewById(R.id.moving_author);
                TextView  moving_time  =view.findViewById(R.id.moving_time);
                TextView  moving_address = view.findViewById(R.id.moving_address);
                TextView  content_id = view.findViewById(R.id.content_id);
                ImageView  moving_back = view.findViewById(R.id.moving_back);
                TextView  name1  =view.findViewById(R.id.name1);
                TextView  name2  =view.findViewById(R.id.name2);
                TextView  name3  =view.findViewById(R.id.name3);
                TextView  name4  =view.findViewById(R.id.name4);
                //图片
                moving_iv.setImageURI(Uri.parse(result.getImageUrl()));
                //名字
                moving_name.setText(result.getName());
                //类型
                moving_type.setText(result.getMovieTypes());
                //导演
                moving_author.setText(result.getDirector());
                //时长
                moving_time.setText(result.getDuration());
                //地址
                moving_address.setText(result.getPlaceOrigin());
                //剧情简介
                content_id.setText(result.getSummary());
                String starring = result.getStarring();
                String[] split = starring.split(",");
                name1.setText(split[0]);
                name2.setText(split[1]);
                name3.setText(split[2]);
                name4.setText(split[3]);
                moving_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
            }
        });
    }
    //得到详情的返回数据
    @Override
    public void MyMoving(MovingBean movingBean) {
        result = movingBean.getResult();
        if(result.getFollowMovie()!=2){
            fllow_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
        }else
        {
            fllow_id.setImageResource(R.mipmap.com_icon_collection_default);
        }
    }

    //预告
    public void initData2() {
       herald_id.setOnClickListener(new View.OnClickListener() {
           SimpleDraweeView sufer_image;
           MediaPlayer player;
           @Override
           public void onClick(View v) {
               View view=View.inflate(MovieActivity.this,R.layout.herald_item,null);
               final SurfaceView sufer=view.findViewById(R.id.sufer);
               ImageView yu_back= view.findViewById(R.id.yu_back);
               sufer_image = view.findViewById(R.id.sufer_image);
               //设置音乐播放器
               player = new MediaPlayer();
              //获取播放和音乐和图片
               List<MovingBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
               String videoUrl = shortFilmList.get(0).getVideoUrl();
               String imageUrl = shortFilmList.get(0).getImageUrl();
               //设置图片
               sufer_image.setImageURI(Uri.parse(imageUrl));
               sufer_image.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       //让图片消失
                       sufer_image.setVisibility(View.GONE);
                       player.start();
                   }
               });
               //播放音乐 设置网址 准备资源
               try {
                   player.setDataSource(getApplicationContext(), Uri.parse(videoUrl));
                   player.prepare();

               } catch (Exception e) {
                   e.printStackTrace();
               }
               //获取缓冲区

               SurfaceHolder holder = sufer.getHolder();
               holder.addCallback(new SurfaceHolder.Callback() {
                   @Override
                   public void surfaceCreated(SurfaceHolder holder) {
                       //展示
                      player.setDisplay(holder);
                       sufer.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               //如果播放就暂停  如果暂停就播放
                               if(player.isPlaying()){
                                   player.pause();
                               }else
                               {
                                   player.start();
                               }
                           }
                       });
                   }

                   @Override
                   public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                   }

                   @Override
                   public void surfaceDestroyed(SurfaceHolder holder) {
                       //销毁的时候释放资源
                       if(player!=null){
                           player.release();
                           player.stop();
                           player=null;
                       }
                   }
               });
               final PopupWindow pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
               yu_back.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       //视频关闭  pop关闭
                       player.stop();
                       pop.dismiss();
                   }
               });
               pop.setBackgroundDrawable(new BitmapDrawable());
               pop.setOutsideTouchable(true);
               pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
           }
       });
    }

  //剧照
  public void initData3() {
      phone_id.setOnClickListener(new View.OnClickListener() {
          RecyclerView phone_recycler;
          ImageView phone_back;
          @Override
          public void onClick(View v) {
              View view=View.inflate(MovieActivity.this,R.layout.phone_view,null);
              phone_recycler=  view.findViewById(R.id.phone_recycler);
              phone_back=  view.findViewById(R.id.phone_back);
              //得到数据
              List<String> posterList = result.getPosterList();
              //瀑布流管理器
              StaggeredGridLayoutManager  layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
              phone_recycler.setLayoutManager(layoutManager);
              //将高度和图片地址放入一个类中
              List<PersonCard> list = new ArrayList<>();
              for(int i=0;i<posterList.size();i++) {
                  PersonCard p = new PersonCard();
                  p.avatarUrl = posterList.get(i);
                  if(i%2==0){
                      p.imgHeight=500;
                  }else
                  {
                      p.imgHeight = (i % 2)*100 + 300;
                  }
                 //偶数和奇数的图片设置不同的高度，以到达错开的目的
                  list.add(p);
              }
              StaggerAdapter staggerAdapter=new StaggerAdapter(MovieActivity.this,list);
              phone_recycler.setAdapter(staggerAdapter);
              //popWindow
              final PopupWindow pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
              phone_back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      pop.dismiss();
                  }
              });
              pop.setBackgroundDrawable(new BitmapDrawable());
              pop.setOutsideTouchable(true);
              pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
          }
      });
  }
    //影评
    public void initData4() {
        assess_id.setOnClickListener(new View.OnClickListener() {
            RecyclerView assess_recycler;
            ImageView assess_back;
            @Override
            public void onClick(View v) {
                View view=View.inflate(MovieActivity.this,R.layout.assess_view,null);
                assess_recycler=  view.findViewById(R.id.assess_recycler);
                assess_back=  view.findViewById(R.id.assess_back);
                ping_id = view.findViewById(R.id.ping_id);

                //设置布局管理器
                LinearLayoutManager layoutManager=new LinearLayoutManager(MovieActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                assess_recycler.setLayoutManager(layoutManager);
                //获取数据
                presenterInter.Assess(id);
                //适配器
                assessAdapter = new AssessAdapter(MovieActivity.this,mlist);
                assess_recycler.setAdapter(assessAdapter);
                final PopupWindow pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                //返回
                assess_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                ping_id.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view=View.inflate(MovieActivity.this,R.layout.ping_view,null);
                        ping_etn = view.findViewById(R.id.ping_etn);
                        TextView  send_id = view.findViewById(R.id.send_id);
                        //弹出软键盘
                        final PopupWindow pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                        ping_etn.setFocusable(true);
                        InputMethodManager imm = (InputMethodManager)MovieActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        send_id.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String   content = ping_etn.getText().toString();
                                HashMap<String,Object> map = new HashMap<>();
                                map.put("movieId",id);
                                map.put("commentContent",content);
                                presenterInter.AddPing(map);
                            }
                        });
                        pop.setBackgroundDrawable(new BitmapDrawable());
                        pop.setOutsideTouchable(true);
                        pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
                    }
                });
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
            }
        });
    }
    //得到评论返回的数据
    @Override
    public void MyAssess(List<AssessBean.ResultBean> list) {
        mlist.clear();
        mlist.addAll(list);
        assessAdapter.notifyDataSetChanged();
    }
    //去评价
    public void Ping(final int commentId) {
        View view=View.inflate(MovieActivity.this,R.layout.ping_view,null);
        ping_etn = view.findViewById(R.id.ping_etn);
         TextView  send_id = view.findViewById(R.id.send_id);
         final PopupWindow pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
         ping_etn.setFocusable(true);
         InputMethodManager imm = (InputMethodManager)MovieActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
         imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        send_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String   content = ping_etn.getText().toString();
                HashMap<String,Object> map = new HashMap<>();
                map.put("commentId",commentId);
                map.put("replyContent",content);
                presenterInter.AddReply(map);

            }
        });
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
     }
     //添加用户对影片的评论
    @Override
    public void MyAddPing(String str) {
        Toast.makeText(MovieActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("评论成功")){
            ping_etn.setText("");
            //刷新适配器
            presenterInter.Assess(id);
        }
    }
    //添加用户对评论的回复
    @Override
    public void MyAddReply(String str) {
        Toast.makeText(MovieActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("回复成功")){
            ping_etn.setText("");
            presenterInter.Assess(id);
        }
    }
    //点赞
    public void Dian(int commentId) {
         presenterInter.Dian(commentId);
    }
    //点赞的返回值
    @Override
    public void MyDian(String str) {
        Toast.makeText(MovieActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("点赞成功")){
            //刷新数据
            presenterInter.Assess(id);
        }
    }
    //购票
    public void initData5() {
       buy_id.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(MovieActivity.this,BuyActivity.class);
               intent.putExtra("id",id);
               intent.putExtra("name",result.getName());
               startActivity(intent);
               overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
           }
       });
    }
}
