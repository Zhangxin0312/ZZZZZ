package com.bw.myproduct.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.WalletAdapter;
import com.bw.myproduct.beans.WalletBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

public class MyWalletActivity extends AppCompatActivity implements ContractInterface.MyWalletInter {
      TextView money;
      RecyclerView wallet_rlv;
    int page=1;
    int count=5;
    ContractInterface.MyPresenterInter myPresenterInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        money=findViewById(R.id.money);
        wallet_rlv=findViewById(R.id.wallet_rlv);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wallet_rlv.setLayoutManager(layoutManager);
        //获取userId和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        int  userId = sp.getInt("userId", 0);
        String  sessionId = sp.getString("sessionId", null);
        myPresenterInter=new MyPresens<>(this);
        myPresenterInter.Wallet(userId,sessionId,page,count);

    }

    @Override
    public void MyWallet(WalletBean walletBean) {
        //我的钱包展示的数据
        money.setText(walletBean.getResult().getBalance()+"");
        WalletAdapter walletAdapter=new WalletAdapter(MyWalletActivity.this,walletBean.getResult());
        wallet_rlv.setAdapter(walletAdapter);
    }
}
