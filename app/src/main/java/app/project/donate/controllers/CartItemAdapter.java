package app.project.donate.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import app.project.donate.Cart;
import app.project.donate.CartDb.CartDatabase;
import app.project.donate.MainUi;
import app.project.donate.R;
import app.project.donate.model.CartItem;

/**
 * Created by ArupPc on 26-02-2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemVH> {

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos;
    private Context mContext;
    private List<CartItem> itemList;
    private CartDatabase db;
    public CartItemAdapter(Context mContext, List<CartItem> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
        db = MainUi.returnDb();

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
        holder.cancelItem.setEnabled(true);
        holder.cancelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                holder.cancelItem.setEnabled(false);
                db.deleteAllData();

                itemList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());

                for(CartItem x:itemList){
                    String t= x.getTitle();
                    int q = x.getQuantity();
                    db.addData(t,q);
                }
               Log.i("Size",itemList.size()+"");
                if(itemList.isEmpty()){
                    Log.i("Empty Cart","Cart Empty!!!");
                    new AlertDialog.Builder(mContext)
                            .setIcon(null)
                            .setTitle("Empty Cart!")
                            .setMessage("Your cart is empty. Click 'Go Back' to go to home Page.")
                            .setCancelable(false)
                            .setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mContext.startActivity(new Intent(mContext,Cart.class));
                                }
                            });

                }
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
        public TextView title, address,message;
        public Button cancelItem;
        public TextView quant;

        public CartItemVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            quant = (TextView) itemView.findViewById(R.id.item_quantity);
            address = (TextView)itemView.findViewById(R.id.user_address);
            thumb = (ImageView)itemView.findViewById(R.id.item_type_icon);
            message = (TextView) itemView.findViewById(R.id.feedback_message);
            cancelItem = (Button)itemView.findViewById(R.id.remove_item);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}