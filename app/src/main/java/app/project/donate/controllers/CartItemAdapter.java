package app.project.donate.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;
import app.project.donate.R;
import app.project.donate.model.CartItem;

/**
 * Created by ArupPc on 26-02-2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemVH> {

    private Context mContext;
    private List<CartItem> itemList;
    public CartItemAdapter(Context mContext, List<CartItem> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }
    @Override
    public CartItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new CartItemVH(v);
    }
    @Override
    public void onBindViewHolder(final CartItemVH holder, final int position) {
        CartItem item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.quant.setText(item.getQuantity()+"");
        holder.address.setText(item.getAddress());
        holder.message.setText(item.getMessage());
        Glide.with(mContext).load(item.getThumbnail()).into(holder.thumb);
        holder.cancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                itemList.remove(position);
                Toast.makeText(mContext, "Removed This Item from the Cart", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public class CartItemVH extends RecyclerView.ViewHolder {
        public ImageView thumb;
        public TextView title, address;
        public Button cancelItem;
        public EditText quant,message;

        public CartItemVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            quant = (EditText) itemView.findViewById(R.id.item_quantity);
            address = (TextView)itemView.findViewById(R.id.user_address);
            thumb = (ImageView)itemView.findViewById(R.id.item_type_icon);
            message = (EditText)itemView.findViewById(R.id.feedback_message);
            cancelItem = (Button)itemView.findViewById(R.id.remove_item);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
