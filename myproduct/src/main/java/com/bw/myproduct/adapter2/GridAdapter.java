package com.bw.myproduct.adapter2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bw.myproduct.R;

import java.util.List;

public class GridAdapter  extends BaseAdapter {
    Context context;
    List<Bitmap> mlist;
    public GridAdapter(Context context, List<Bitmap> list) {
        this.context=context;
        this.mlist=list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.grid_item,null);
        ImageView gv_iv=view.findViewById(R.id.gv_iv);
        gv_iv.setImageBitmap(mlist.get(position));
        return view;
    }
}
