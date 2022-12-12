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
import com.projectm.cards.DoctorsCard;

public class DoctorCardAdapter extends FirebaseRecyclerAdapter<DoctorsCard,DoctorCardAdapter.DoctorHolder> {

    public DoctorCardAdapter(@NonNull FirebaseRecyclerOptions<DoctorsCard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorCardAdapter.DoctorHolder holder, int position, @NonNull DoctorsCard model) {
        holder.nametext.setText(model.getName());
        holder.doctorsImage.setImageResource(model.getImg());
    }

    @NonNull
    @Override
    public DoctorCardAdapter.DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_row,parent,false);

        return new DoctorHolder(view);
    }

    public class DoctorHolder extends RecyclerView.ViewHolder {
        TextView nametext;
        ImageView doctorsImage;
        public DoctorHolder(@NonNull View itemView) {
            super(itemView);
            nametext=itemView.findViewById(R.id.nametext);
            doctorsImage=itemView.findViewById(R.id.doctorsImage);
        }
    }
}
