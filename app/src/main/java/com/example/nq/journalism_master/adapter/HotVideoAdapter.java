package com.example.nq.journalism_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.bean.Video.VideoModel;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/4/8.
 */

public class HotVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<VideoModel> mList;

    public HotVideoAdapter (Context context,  List<VideoModel> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    public void setTopAdapter (List<VideoModel> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_item, parent, false);
        return new ViewHolderNormal(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoModel  currVM = mList.get(position);
        ViewHolderNormal normalHolder = (ViewHolderNormal) holder;
        normalHolder.tv_source_video.setText("来源：" + currVM.topicName);
        normalHolder.tv_length_video.setText("时长：" + (currVM.length / 60) + ":" + (currVM.length % 60));
        normalHolder.tv_play_video.setText("点击：" + currVM.playCount + "次");
        boolean setUp = normalHolder.jcvps.setUp(currVM.mp4_url, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, currVM.title);
        if (setUp)
            Glide.with(mContext).load(currVM.cover).into(normalHolder.jcvps.thumbImageView);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolderNormal extends RecyclerView.ViewHolder {

        private JCVideoPlayerStandard jcvps;
        private TextView tv_source_video;
        private TextView tv_length_video;
        private TextView tv_play_video;

        public ViewHolderNormal(View itemView) {
            super(itemView);
            jcvps = (JCVideoPlayerStandard) itemView.findViewById(R.id.jcvps);
            tv_source_video = (TextView) itemView.findViewById(R.id.tv_source_video);
            tv_length_video = (TextView) itemView.findViewById(R.id.tv_length_video);
            tv_play_video = (TextView) itemView.findViewById(R.id.tv_play_video);
        }
    }

}
