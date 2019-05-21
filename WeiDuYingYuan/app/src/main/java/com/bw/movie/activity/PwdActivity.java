package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.App;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PwdActivity extends AppCompatActivity {
    @BindView(R.id.old_pwd)
    TextView old_pwd;
    @BindView(R.id.new_pwd_id)
    EditText new_pwd_id;
    @BindView(R.id.reset_pwd_id)
    EditText reset_pwd_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        ButterKnife.bind(this);
        String pwd=App.Pwd;
        old_pwd.setText(pwd+"");

    }
}
