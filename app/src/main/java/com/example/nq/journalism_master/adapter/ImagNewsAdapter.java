package com.example.nq.journalism_master.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.bean.Top.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ImagNewsAdapter extends PagerAdapter{

    private static final String TAG = "ImagNewsAdapter";
    private Context mContext;
    private List<Photo> mList;
    private List<ImageView> list = new ArrayList<>();

    public ImagNewsAdapter(Context context, List<Photo> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView view = (ImageView) object;
        container.removeView(view);
        list.add(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view;
        if (list.isEmpty()) {
            view = new ImageView(mContext);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            view = list.remove(0);
        }
        view.setBackgroundResource(R.drawable.null_icon);
        Glide.with(mContext)
                .load(mList.get(position).getImgurl())
                .into(view);
        Log.e(TAG, mList.get(position).getImgurl());
        container.addView(view);
        return view;
    }
}
