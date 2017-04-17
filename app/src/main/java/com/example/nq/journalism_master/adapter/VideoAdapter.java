package com.example.nq.journalism_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.bean.Top.NewsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int EMPTY_VIEW = 2;

    public List<NewsModel> mData;

    private View mHeaderView;

    private Context mContext;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void setTopAdapter(List<NewsModel> topAdapter) {
        this.mData = topAdapter;
    }

    public VideoAdapter(Context context, List<NewsModel> mList) {
        this.mContext = context;
        this.mData = mList;
        notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyDataSetChanged();
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(List<NewsModel> datas, Context context) {
        mData.addAll(datas);
        this.mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0) return TYPE_HEADER; //头部
        NewsModel currNM = mData.get(position - 1);
        if (currNM.imgextra != null && currNM.imgextra.size() != 0) {
            return EMPTY_VIEW;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);

        if (viewType == TYPE_NORMAL) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new Holder(layout);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_icon, parent, false);
            return new Holder(layout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        NewsModel data = mData.get(pos);
        if(viewHolder instanceof Holder) {
            ((Holder) viewHolder).title.setText(data.getTitle());
            ((Holder) viewHolder).source.setText(data.getSource());
            ((Holder) viewHolder).ptime.setText(data.getPtime());
            Glide.with(mContext)
                    .load(data.getImgsrc())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(((Holder) viewHolder).imgsrc);
            if (data.imgextra != null && data.imgextra.size() > 1){
                Glide.with(mContext)
                        .load(data.getImgsrc())
                        .asBitmap()
                        .error(R.drawable.null_icon)
                        .into(((Holder) viewHolder).imgsrc);
                Glide.with(mContext)
                        .load(data.imgextra.get(0).imgsrc)
                        .asBitmap()
                        .error(R.drawable.null_icon)
                        .into(((Holder) viewHolder).pic_s02);
                Glide.with(mContext)
                        .load(data.imgextra.get(1).imgsrc)
                        .asBitmap()
                        .error(R.drawable.null_icon)
                        .into(((Holder) viewHolder).pic_s03);
            }

            if(mListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, mData.get(pos));
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mData.size() : mData.size() + 1;
    }


    class Holder extends RecyclerView.ViewHolder {

        public TextView title;  //标题
        public TextView source;  //某址
        public TextView ptime;  //时间
        public ImageView imgsrc;  //icon
        public ImageView pic_s02;
        public ImageView pic_s03;

        public Holder(View view) {
            super(view);
            if(itemView == mHeaderView) return;
            title = (TextView) view.findViewById(R.id.fragment_title);
            source = (TextView) view.findViewById(R.id.fragment_name);
            ptime = (TextView) view.findViewById(R.id.fragment_date);
            imgsrc = (ImageView) view.findViewById(R.id.fragment_img);
            pic_s02 = (ImageView) view.findViewById(R.id.fragment_img_02);
            pic_s03 = (ImageView) view.findViewById(R.id.fragment_img_03);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Object object);
    }
}
