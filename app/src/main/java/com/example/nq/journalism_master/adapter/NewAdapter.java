package com.example.nq.journalism_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int EMPTY_VIEW = 2;

    public List<Data> mData;

    private View mHeaderView;

    private Context mContext;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public void setTopAdapter(List<Data> topAdapter) {
        this.mData = topAdapter;
    }

    public NewAdapter(Context context, List<Data> mList) {
        this.mContext = context;
        this.mData = mList;
        notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(List<Data> datas, Context context) {
        mData.addAll(datas);
        this.mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return TYPE_HEADER; //头部

//        if(position == 1) {
////            Log.e("ZhihuDaily0", mData.get(position).getType().equals);
//            if (mData.get(position).getThumbnail_pic_s02() != null && mData.get(position).getThumbnail_pic_s03() != null) {
//                Log.e("ZhihuDaily0", "11");
//                return EMPTY_VIEW;
//            } else {
//                Log.e("ZhihuDaily0", "22");
//                return TYPE_NORMAL;
//            }
//        }
        //因为头部占了1   所以这里要减去1  不然报错   不能用 final int pos = getRealPosition(viewHolder);  viewHolder传不进
        if (mData.get(position - 1).getThumbnail_pic_s02() != null && mData.get(position - 1).getThumbnail_pic_s03() != null) {
            Log.e("ZhihuDaily0", "11");
            return EMPTY_VIEW;
        } else {
            Log.e("ZhihuDaily0", "22");
            return TYPE_NORMAL;
        }
//        Log.e("ZhihuDaily0", "33");
//        return EMPTY_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
//        switch (viewType) {
//            case EMPTY_VIEW:
//                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_icon, parent, false);
//                return new Holder(layout);
//            case TYPE_NORMAL:
//                View layout_1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
//                return new Holder(layout_1);
//            default:
//                return null;
//        }
        if (viewType == TYPE_NORMAL) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new Holder(layout);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_icon, parent, false);
            return new Holder(layout);
        }
//        if (viewType == TYPE_NORMAL) {
//            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_icon, parent, false);
//            return new Holder(layout);
//        } else if (viewType == EMPTY_VIEW) {
//            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
//            return new Holder(layout);
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        final Data data = mData.get(pos);
        if(viewHolder instanceof Holder) {
            ((Holder) viewHolder).title.setText(data.getTitle());
            ((Holder) viewHolder).author_name.setText(data.getAuthor_name());
            ((Holder) viewHolder).date.setText(data.getDate());
            Glide.with(mContext)
                    .load(data.getThumbnail_pic_s())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(((Holder) viewHolder).pic_s);
            if (((Holder) viewHolder).pic_s02 != null && ((Holder) viewHolder).pic_s03 != null){
                Glide.with(mContext)
                        .load(data.getThumbnail_pic_s02())
                        .asBitmap()
                        .error(R.drawable.null_icon)
                        .into(((Holder) viewHolder).pic_s02);
                Glide.with(mContext)
                        .load(data.getThumbnail_pic_s03())
                        .asBitmap()
                        .error(R.drawable.null_icon)
                        .into(((Holder) viewHolder).pic_s03);
            }

            if(mListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, data);
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
        public TextView author_name;  //某址
        public TextView date;  //时间
        public ImageView pic_s;  //icon
        public ImageView pic_s02;
        public ImageView pic_s03;

        public Holder(View view) {
            super(view);
            if(itemView == mHeaderView) return;
            title = (TextView) view.findViewById(R.id.fragment_title);
            author_name = (TextView) view.findViewById(R.id.fragment_name);
            date = (TextView) view.findViewById(R.id.fragment_date);
            pic_s = (ImageView) view.findViewById(R.id.fragment_img);
            pic_s02 = (ImageView) view.findViewById(R.id.fragment_img_02);
            pic_s03 = (ImageView) view.findViewById(R.id.fragment_img_03);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, Data data);
    }
}
