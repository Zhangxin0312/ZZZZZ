package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Entity.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.MiMaBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PwdActivity extends AppCompatActivity implements ContractInterface.MiMaViewInterface {
    @BindView(R.id.old_pwd)
    TextView old_pwd;
    @BindView(R.id.new_pwd_id)
    EditText new_pwd_id;
    @BindView(R.id.reset_pwd_id)
    EditText reset_pwd_id;
    ContractInterface.PresenterInterface presenterInterface;
    @BindView(R.id.update_pwd)
    Button updatePwd;
    String string2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        presenterInterface = new MyPresenter<>(this);
        ButterKnife.bind(this);
        String pwd = App.Pwd;
        old_pwd.setText(pwd + "");
         string2 = old_pwd.getText().toString();

        updatePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String encrypt = EncryptUtil.encrypt(string2);

                String string = new_pwd_id.getText().toString();

                String encrypt1 = EncryptUtil.encrypt(string);

                String string1 = reset_pwd_id.getText().toString();
                String encrypt2 = EncryptUtil.encrypt(string1);
                presenterInterface.toMiMa(encrypt, encrypt1, encrypt2);

            }
        });


    }

    @Override
    public void showMiMa(Object o) {
        MiMaBean miMaBean= (MiMaBean) o;
        if(miMaBean.getMessage().equals("密码修改成功")){
            Intent intent=new Intent(PwdActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
