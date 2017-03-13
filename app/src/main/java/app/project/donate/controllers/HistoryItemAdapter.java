package app.project.donate.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.project.donate.R;
import app.project.donate.model.HistoryItem;

/**
 * Created by ArupPc on 11-03-2017.
 */

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.HistoryItemVH>{



    private Context mContext;
    private List<HistoryItem> itemListHistory;

    public HistoryItemAdapter(Context mContext, List<HistoryItem> itemListHistory) {
        this.mContext = mContext;
        this.itemListHistory = itemListHistory;
    }

    @Override
    public HistoryItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        return new HistoryItemVH(v);
    }

    @Override
    public void onBindViewHolder(HistoryItemVH holder,final int position) {
        HistoryItem item = itemListHistory.get(position);
        holder.title.setText(item.getTitle());
        holder.quant.setText(item.getQuantity()+"");
        holder.ngo.setText(item.getNgo());
        holder.stats.setText(item.getStatus());
        holder.address.setText(item.getAddress());
        Glide.with(mContext).load(item.getThumbnail()).into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return itemListHistory.size();
    }

    public class HistoryItemVH extends RecyclerView.ViewHolder{

        public ImageView thumb;
        public TextView title, stats,ngo;
        public TextView quant,address;


        public HistoryItemVH(View itemView) {
            super(itemView);
            thumb = (ImageView)itemView.findViewById(R.id.item_type_icon);
            title = (TextView) itemView.findViewById(R.id.item_title);
            quant = (TextView) itemView.findViewById(R.id.item_quantity);
            address = (TextView)itemView.findViewById(R.id.user_address);
            ngo = (TextView)itemView.findViewById(R.id.ngo_name);
            address = (TextView)itemView.findViewById(R.id.user_address);
            stats = (TextView)itemView.findViewById(R.id.status_message);
        }
    }
}
