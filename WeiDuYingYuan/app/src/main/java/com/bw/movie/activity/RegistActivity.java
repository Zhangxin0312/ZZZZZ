package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.Entity.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistActivity extends AppCompatActivity implements ContractInterface.RegViewInterface {

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_sex)
    EditText editSex;
    @BindView(R.id.edit_birthday)
    EditText editBirthday;
    @BindView(R.id.edit_telephone)
    EditText editTelephone;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_zhuce)
    Button btnZhuce;
    ContractInterface.PresenterInterface presenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        presenterInterface=new MyPresenter<>(this);
        btnZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String birthday = editBirthday.getText().toString();
                String email = editEmail.getText().toString();
                String name = editName.getText().toString();
                String pwd = editPassword.getText().toString();
                String phone = editTelephone.getText().toString();
                String sex = editSex.getText().toString();
               // String pwd2 = editPassword.getText().toString();
                String encrypt = EncryptUtil.encrypt(pwd);
                presenterInterface.toRegist(phone,encrypt,name,birthday,email, Integer.parseInt(sex),encrypt);
            }
        });
    }

    @Override
    public void showReg(Object o) {
        RegistBean registBean= (RegistBean) o;
        if(registBean.getMessage().equals("注册成功")){
            Toast.makeText(RegistActivity.this,(String)o,Toast.LENGTH_SHORT).show();
        }

    }
}
