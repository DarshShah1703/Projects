package com.projectm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.projectm.R;
import com.projectm.cards.HospitalCard;

public class HospitalCardAdapter extends FirebaseRecyclerAdapter<HospitalCard,HospitalCardAdapter.HospitalHolder> {

    public HospitalCardAdapter(@NonNull FirebaseRecyclerOptions<HospitalCard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HospitalCardAdapter.HospitalHolder holder, int position, @NonNull HospitalCard model) {
        holder.nametext.setText(model.getName());
        holder.hospitalsImage.setImageResource(model.getImg());
    }

    @NonNull
    @Override
    public HospitalCardAdapter.HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hospitals_row,parent,false);

        return new HospitalHolder(view);
    }

    public class HospitalHolder extends RecyclerView.ViewHolder {

        TextView nametext;
        ImageView hospitalsImage;
        public HospitalHolder(@NonNull View itemView) {
            super(itemView);
            nametext=itemView.findViewById(R.id.nametext);
            hospitalsImage=itemView.findViewById(R.id.hospitalsImage);
        }
    }
}
