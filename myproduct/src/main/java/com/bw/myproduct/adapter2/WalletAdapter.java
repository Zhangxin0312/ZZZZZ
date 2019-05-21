package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.WalletBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.Holder> {
    Context context;
    WalletBean.ResultBean result;
    public WalletAdapter(Context context, WalletBean.ResultBean result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //我的钱包内层RecycllerVAIew 展示
        View view=View.inflate(context, R.layout.wallet_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
          holder.wallet_money.setText(result.getDetailList().get(i).getAmount()+"");
          holder.wallet_time.setText(simpleDateFormat.format(date));
    }

    @Override
    public int getItemCount() {

        return   result.getDetailList().size();
    }

    public class  Holder extends  RecyclerView.ViewHolder{
            TextView wallet_money,wallet_time;
        public Holder(@NonNull View itemView) {
            super(itemView);
            wallet_money=itemView.findViewById(R.id.wallet_money);
            wallet_time=itemView.findViewById(R.id.wallet_time);
        }
    }
}
