package app.project.donate.controllers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

import app.project.donate.R;
import app.project.donate.model.ItemModel;

/**
 * Created by AmanPC on 12-03-2017.
 */

public class RecyclerViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewRecyclerAdapter.ViewHolder> {
    public final List<ItemModel> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public RecyclerViewRecyclerAdapter(final List<ItemModel> data){
        this.data = data;
        for(int i=0; i<data.size(); i++){
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ItemModel item = data.get(position);
        holder.setIsRecyclable(false);
        holder.textView.setText(item.description);
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, item.colorId1));
        holder.expandableLayout.setInRecyclerView(true);
   //   holder.expandableLayout.setBackgroundResource(item.childImages);
        holder.imageView.setImageResource(item.childImages);
        holder.expandableLayout.setInterpolator(item.interpolator);
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandState.put(position, false);
            }
        });
        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout buttonLayout;
        public ExpandableLinearLayout expandableLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.button);
            expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(target,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
