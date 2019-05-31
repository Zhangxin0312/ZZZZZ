package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessActivity extends AppCompatActivity {
   @BindView(R.id.fanhui_id)
    ImageView fanhui_id;
    SuccessActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
        activity=this;
        fanhui_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }
}
