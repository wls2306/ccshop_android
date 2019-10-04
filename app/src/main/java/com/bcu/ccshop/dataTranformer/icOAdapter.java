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
    private LayoutInflater layoutInflater;
    private ViewHolderOA holderOA = null;
    private int layout;
    private int type;

    public icOAdapter(List<orderItemIcon> beanList, Context context, int layout, int type) {
        this.beanList = beanList;
        this.layout = layout;
        this.type = type;
        this.layoutInflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return beanList.size();
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
            holderOA=new ViewHolderOA();
            holderOA.imageView=(ImageView) view.findViewById(R.id.imageView8);
            holderOA.id=(TextView) view.findViewById(R.id.textView19);
            holderOA.name=(TextView) view.findViewById(R.id.textView21);
            holderOA.color=(TextView) view.findViewById(R.id.textView22);
            holderOA.prace=(TextView) view.findViewById(R.id.textView20);
            holderOA.tatol=(TextView) view.findViewById(R.id.textView23);
            holderOA.imageButton1=view.findViewById(R.id.imageButton15);
            holderOA.imageButton2=view.findViewById(R.id.imageButton16);
            holderOA.imageButton3=view.findViewById(R.id.imageButton17);
            System.out.println("123");
            view.setTag(holderOA);
        }else {
            holderOA=(ViewHolderOA) view.getTag();
        }
        holderOA.imageView.setImageBitmap(beanList.get(i).getoImage());
        holderOA.id.setText("订单编号："+beanList.get(i).getoID()+"  创建日期："+beanList.get(i).getoFDate());
        holderOA.name.setText(""+beanList.get(i).getoNmae());
        holderOA.color.setText("");
        holderOA.prace.setText(""+beanList.get(i).getoGFee()+"/n×"+beanList.get(i).getoCount());
        holderOA.tatol.setText("合计："+beanList.get(i).getoFee());
        return view;
    }
    private static class ViewHolderOA{
        ImageView imageView;
        TextView id,name,color,prace,tatol;
        ImageButton imageButton1,imageButton2,imageButton3;


    }
}