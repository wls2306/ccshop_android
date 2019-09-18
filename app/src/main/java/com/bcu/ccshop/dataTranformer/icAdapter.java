package com.bcu.ccshop.dataTranformer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcu.ccshop.R;
import com.bcu.ccshop.entity.goodsInco;



import java.util.List;

public class icAdapter extends BaseAdapter {

    private List<goodsInco> beanList;
    private LayoutInflater layoutInflater;
    public  icAdapter(List<goodsInco> beanList, Context context){
        this.beanList=beanList;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view=layoutInflater.inflate(R.layout.ic_layout,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView) view.findViewById(R.id.img_icon);
            holder.textViewN=(TextView) view.findViewById(R.id.txt_icon_n);
            holder.textViewP=(TextView) view.findViewById(R.id.txt_icon_p);
            view.setTag(holder);
        }else {
            holder=(ViewHolder) view.getTag();
        }
        holder.textViewN.setText(beanList.get(i).getName());
        holder.textViewP.setText(""+beanList.get(i).getPrice());
        //holder.imageView.setImage




        return view;
    }
    private static class ViewHolder{
        ImageView imageView;
        TextView textViewN;
        TextView textViewP;

    }
}
