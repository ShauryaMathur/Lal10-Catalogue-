package com.shaurya.lal10catalogue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private int[] imgids=new int[] {R.drawable.boutiques,R.drawable.corporate_gifting,R.drawable.csr_philanthropy,R.drawable.exhibitions,R.drawable.export_houses,R.drawable.hotels};

    ImageAdapter(Context ctx){
        mContext=ctx;
    }
    @Override
    public int getCount() {
        return imgids.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imgview=new ImageView(mContext);
        imgview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgview.setImageResource(imgids[position]);
        container.addView(imgview,0);
        return imgview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

}
