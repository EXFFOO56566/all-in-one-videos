package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.item.ItemComment;
import com.viaviapp.allinonevideo.R;

import java.util.ArrayList;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemRowHolder> {

    private ArrayList<ItemComment> dataList;
    private Context mContext;

    public CommentAdapter(Context context, ArrayList<ItemComment> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment_item, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder holder, final int position) {
        final ItemComment singleItem = dataList.get(position);

        holder.text_user.setText(singleItem.getCommentName());
        holder.text_msg.setText(singleItem.getCommentMsg());

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        private TextView text_user, text_msg;
        private LinearLayout lyt_parent;

        private ItemRowHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text_user = itemView.findViewById(R.id.text_user);
            lyt_parent = itemView.findViewById(R.id.rootLayout);
            text_msg = itemView.findViewById(R.id.text_comment);

        }
    }
}
