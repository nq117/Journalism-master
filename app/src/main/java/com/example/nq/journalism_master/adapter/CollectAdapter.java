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
import com.example.nq.journalism_master.bean.Data;

public class CollectAdapter extends BaseRecyclerAdapter<Data> {

    private Data mData;
    private Context mContext;

    public CollectAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new CollectHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Data data) {
        if (viewHolder instanceof CollectHolder) {
           this.mData = data;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        if (viewHolder instanceof CollectHolder) {
            ((CollectHolder) viewHolder).title.setText(mData.getTitle());
            ((CollectHolder) viewHolder).author_name.setText(mData.getAuthor_name());
            ((CollectHolder) viewHolder).date.setText(mData.getDate());
            Glide.with(mContext)
                    .load(mData.getThumbnail_pic_s())
                    .asBitmap()
                    .error(R.drawable.null_icon)
                    .into(((CollectHolder) viewHolder).pic_s);
        }
    }

    class CollectHolder extends BaseRecyclerAdapter.Holder {
        public TextView title;  //标题
        public TextView author_name;  //某址
        public TextView date;  //时间
        public ImageView pic_s;  //icon
        public CollectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.fragment_title);
            author_name = (TextView) itemView.findViewById(R.id.fragment_name);
            date = (TextView) itemView.findViewById(R.id.fragment_date);
            pic_s = (ImageView) itemView.findViewById(R.id.fragment_img);
        }
    }
}
