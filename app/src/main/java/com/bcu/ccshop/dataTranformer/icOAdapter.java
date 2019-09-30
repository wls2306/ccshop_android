package com.bcu.ccshop.dataTranformer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcu.ccshop.R;
import com.bcu.ccshop.model.goodsInco;
import com.bcu.ccshop.model.orderItemIcon;

import java.util.List;

public class icOAdapter extends BaseAdapter {
    private static final int COMPLETED = 0;
    private List<orderItemIcon> beanList;

    public icOAdapter(List<orderItemIcon> beanList, Context context, int layout, int type) {
        this.beanList = beanList;
        this.layout = layout;
        this.type = type;
        this.layoutInflater=LayoutInflater.from(context);
    }

    private LayoutInflater layoutInflater;
    private icOAdapter.ViewHolder holder = null;
    private int layout;
    private int type;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view=layoutInflater.inflate(layout,null);
            holder=new icOAdapter.ViewHolder();
            holder.imageView=(ImageView) view.findViewById(R.id.imageView8);
            holder.id=(TextView) view.findViewById(R.id.textView19);
            holder.name=(TextView) view.findViewById(R.id.textView21);
            holder.color=(TextView) view.findViewById(R.id.textView22);
            holder.prace=(TextView) view.findViewById(R.id.textView20);
            holder.tatol=(TextView) view.findViewById(R.id.textView23);
            holder.imageButton1=view.findViewById(R.id.imageButton15);
            holder.imageButton2=view.findViewById(R.id.imageButton16);
            holder.imageButton3=view.findViewById(R.id.imageButton17);
            System.out.println("123");
            view.setTag(holder);
        }else {
            holder=(icOAdapter.ViewHolder) view.getTag();
        }
        holder.imageView.setImageBitmap(beanList.get(i).getoImage());
        holder.id.setText("订单编号："+beanList.get(i).getoID()+"  创建日期："+beanList.get(i).getoFDate());
        holder.name.setText(""+beanList.get(i).getoNmae());
        holder.color.setText("");
        holder.prace.setText(""+beanList.get(i).getoGFee()+"/n×"+beanList.get(i).getoCount());
        holder.tatol.setText("合计："+beanList.get(i).getoFee());
        return view;
    }
    private static class ViewHolder{
        ImageView imageView;
        TextView id,name,color,prace,tatol;
        ImageButton imageButton1,imageButton2,imageButton3;


    }
}