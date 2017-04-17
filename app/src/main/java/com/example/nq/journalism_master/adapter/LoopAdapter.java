package com.example.nq.journalism_master.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.bean.Top.Ads;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by WangChenchen on 2017/4/11.
 * 图片无限轮播的RollPagerView适配器
 */
public class LoopAdapter extends LoopPagerAdapter {

    private List<Ads> adsList;

    public LoopAdapter(RollPagerView viewPager, List<Ads> ads) {
        super(viewPager);
        this.adsList = ads;
    }

    @Override
    public View getView(ViewGroup container, int position) {
//            ImageView view = new ImageView(container.getContext());
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_rollpagerveiw, container, false);
        final ImageView imageView = (ImageView) view.findViewById(R.id.iv_rpv);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        Glide.with(container.getContext())
//                .load(adsList.get(position).getImgsrc())
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
        ImageLoader.getInstance().loadImage(adsList.get(position).getImgsrc(), new SimpleImageLoadingListener(){

            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                imageView.setImageBitmap(loadedImage);
            }

        });
//        Log.e("ImageLoopAdapter",adsList.get(position).getImgsrc());

        TextView tv_rpv = (TextView) view.findViewById(R.id.tv_rpv);
        tv_rpv.setText(adsList.get(position).getTitle());
//        Log.e("ImageLoopAdapter",adsList.get(position).getTitle());
        return view;
    }

    @Override
    public int getRealCount() {
        return adsList.size();
    }
}

