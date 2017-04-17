package com.example.nq.journalism_master.adapter;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.bean.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{

    public List<Data> mData;
    public Context mContext;
    private static final int PROGRESS_VIEW = 1;
    private static final int EMPTY_VIEW = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;  //标题
        public TextView author_name;  //某址
        public TextView date;  //时间
        public ImageView pic_s;  //icon
        public ImageView pic_s02;
        public ImageView pic_s03;
//        @BindView(R.id.address_looking_up)
        public ContentLoadingProgressBar progressBar;
        public ViewHolder(View view) {
            super(view);
//            ButterKnife.bind(this, view);
            title = (TextView) view.findViewById(R.id.fragment_title);
            author_name = (TextView) view.findViewById(R.id.fragment_name);
            date = (TextView) view.findViewById(R.id.fragment_date);
            pic_s = (ImageView) view.findViewById(R.id.fragment_img);
            pic_s02 = (ImageView) view.findViewById(R.id.fragment_img_02);
            pic_s03 = (ImageView) view.findViewById(R.id.fragment_img_03);
            progressBar = (ContentLoadingProgressBar) view.findViewById(R.id.address_looking_up);
        }
    }
    public void setTopAdapter(List<Data> topAdapter) {
        this.mData = topAdapter;
    }

    public TopAdapter(Context context, List<Data> list){
        this.mContext = context;
        this.mData = list;
    }

    @Override
    public int getItemViewType(int position) {
//        if (mData.get(position) == null) {
//            return EMPTY_VIEW;
//        }
        if( mData.get(position).getThumbnail_pic_s02() == null && mData.get(position).getThumbnail_pic_s03() == null){
            return PROGRESS_VIEW;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if (viewType == EMPTY_VIEW) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent, false);
//            return new ViewHolder(view);
//        }
        if (viewType == PROGRESS_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_icon, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewHolder mHolder = holder;
//        if (mData.get(position) == null) {
//            holder.progressBar.setIndeterminate(true);
//        }
        holder.title.setText(mData.get(position).getTitle());
        holder.author_name.setText(mData.get(position).getAuthor_name());
        holder.date.setText(mData.get(position).getDate());
        Glide.with(mContext)
                .load(mData.get(position).getThumbnail_pic_s())
                .asBitmap()
                .error(R.drawable.null_icon)
                .into(holder.pic_s);
        if (mData.get(position).getThumbnail_pic_s02() != null && mData.get(position).getThumbnail_pic_s03() != null){
            Glide.with(mContext)
                    .load(mData.get(position).getThumbnail_pic_s())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(holder.pic_s);
            Glide.with(mContext)
                    .load(mData.get(position).getThumbnail_pic_s02())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(holder.pic_s02);
            Glide.with(mContext)
                    .load(mData.get(position).getThumbnail_pic_s03())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(holder.pic_s03);
        }

    }

//    class ProgressViewHolder extends ViewHolder {
//        public ProgressViewHolder(View view) {
//            super(view);
//        }
//    }

}
