package com.shaurya.lal10catalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private final String[] web;
    private final int[] Imageid;

    public  GridViewAdapter(Context mContext, String[] web, int[] Imageid){
        this.mContext=mContext;
        this.web=web;
        this.Imageid=Imageid;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int i) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view==null){
            grid=new View(mContext);
            grid=inflater.inflate(R.layout.gridview_with_text_view,null);
            TextView textView= (TextView)grid.findViewById(R.id.textView2);
            ImageView imageView1= (ImageView)grid.findViewById(R.id.imageView2);
            textView.setText(web[i]);
            imageView1.setImageResource(Imageid[i]);

        }else{
            grid= (View) view;
        }
        return grid;
    }
}
