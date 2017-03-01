package app.project.donate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AmanPC on 26-02-2017.
 */

public class DonateCardAdapter extends RecyclerView.Adapter<DonateCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<DonationCard> donationsList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView cardBackground;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.donation_card_title);
            cardBackground = (ImageView) view.findViewById(R.id.donation_card_image);
        }
    }

    public DonateCardAdapter(Context mContext, List<DonationCard> donationsList){
        this.mContext = mContext;
        this.donationsList = donationsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.donations_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DonationCard donationCard = donationsList.get(position);
        holder.title.setText(donationCard.getTitle());
        holder.cardBackground.setImageResource(donationCard.getCardImages());
    }

    @Override
    public int getItemCount() {
        return donationsList.size();
    }
}
