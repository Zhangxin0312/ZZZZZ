package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.MyAddressBean;
import com.bw.myproduct.view.AddressListActivity;

import java.util.List;

public class MyAddAdapter extends RecyclerView.Adapter<MyAddAdapter.Holder> {
    Context context;
    List<MyAddressBean.ResultBean> mlist;
    AddressListActivity addressActivity;
    public MyAddAdapter(Context context, List<MyAddressBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
        addressActivity= (AddressListActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //添加地址
        View view=View.inflate(context, R.layout.myadd_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
           holder.myaddress_id.setText(mlist.get(i).getAddress());
           holder.myadd_name.setText(mlist.get(i).getRealName());
           holder.myadd_phone.setText(mlist.get(i).getPhone());
           holder.add_cb.setChecked(mlist.get(i).isFlag());
           holder.add_cb.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(holder.add_cb.isChecked()){
                       int id = mlist.get(i).getId();
                       addressActivity.getAddId(id);
                   }
               }
           });
           holder.update.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int id = mlist.get(i).getId();
                       String address = mlist.get(i).getAddress();
                       String name = mlist.get(i).getRealName();
                       String phone = mlist.get(i).getPhone();
                       String zipCode = mlist.get(i).getZipCode();
                       addressActivity.toUpdate(id,address,name,phone,zipCode);
               }
           });
           holder.my_del.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

               }
           });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        TextView myadd_name,myadd_phone,myaddress_id;
        CheckBox  add_cb;
        Button update,my_del;
        public Holder(@NonNull View itemView) {
            super(itemView);
            myadd_name=itemView.findViewById(R.id.myadd_name);
            myadd_phone=itemView.findViewById(R.id.myadd_phone);
            myaddress_id=itemView.findViewById(R.id.myaddress_id);
            add_cb=itemView.findViewById(R.id.add_cb);
            update=itemView.findViewById(R.id.update);
            my_del=itemView.findViewById(R.id.my_del);
        }
    }
}
