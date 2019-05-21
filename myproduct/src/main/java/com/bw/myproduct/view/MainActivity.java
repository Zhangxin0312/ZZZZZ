package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.LoginBean;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;

public class MainActivity extends AppCompatActivity  implements  Contract.MyLoginInterface{
    EditText phone,pwd;
    Button login;
    TextView jump;
    CheckBox memory;
    Contract.MyPresenterInterface myPresenterInterface;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone=findViewById(R.id.phone);
        pwd=findViewById(R.id.pwd);
        login=findViewById(R.id.login);
        jump=findViewById(R.id.jump);
        memory=findViewById(R.id.memory);

        sp = getSharedPreferences("share", MODE_PRIVATE);
        myPresenterInterface=new MyPresenter<>(this);
        if(sp.getBoolean("flag",false)){
            phone.setText(sp.getString("phone",null));
            pwd.setText(sp.getString("pwd",null));
            memory.setChecked(true);
        }else
        {
            phone.setText(sp.getString("phone",null));
            pwd.setText(sp.getString("pwd",null));
            memory.setChecked(false);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone1 = phone.getText().toString();
                String pwd1 = pwd.getText().toString();
                edit = sp.edit();

                if(memory.isChecked()){
                    edit.putString("phone",phone1);
                    edit.putString("pwd",pwd1);
                    edit.putBoolean("flag",true);
                    edit.commit();
                }else
                {
                    edit.putString("phone",null);
                    edit.putString("pwd",null);
                    edit.putBoolean("flag",false);
                    edit.commit();
                }
                myPresenterInterface.Login(phone1,pwd1);
            }
        });
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void Logins(LoginBean loginBean) {
        Toast.makeText(MainActivity.this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        if(loginBean.getMessage().equals("登录成功")){
            Intent intent=new Intent(MainActivity.this,ShowActivity.class);
            edit.putString("sessionId",loginBean.getResult().getSessionId());
            edit.putInt("userId",loginBean.getResult().getUserId());
            edit.commit();
            startActivity(intent);
        }
    }
}
