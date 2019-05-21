package com.bw.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordFeedBackActivity extends AppCompatActivity  implements  ContractInter.FeedBackInter{
    @BindView(R.id.commit_id)
    Button commit_id;
    @BindView(R.id.feed_content)
    EditText feed_content;
    ContractInter.PresenterInter presenterInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_feed_back);
        ButterKnife.bind(this);
        presenterInter=new Presenter<>(this);
        commit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = feed_content.getText().toString();
                presenterInter.recordFeedBack(content);
            }
        });
    }
     //意见反馈
    @Override
    public void MyFeedBack(String str) {
        Toast.makeText(RecordFeedBackActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("反馈成功")){
            Intent intent=new Intent(RecordFeedBackActivity.this,SuccessActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
