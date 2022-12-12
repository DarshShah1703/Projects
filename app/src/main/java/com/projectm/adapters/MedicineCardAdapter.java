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
import com.projectm.cards.MedicineCards;
import com.projectm.R;

public class MedicineCardAdapter extends FirebaseRecyclerAdapter<MedicineCards, MedicineCardAdapter.MedicineHolder> {

    public MedicineCardAdapter(@NonNull FirebaseRecyclerOptions<MedicineCards> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MedicineHolder holder, int position, @NonNull MedicineCards model) {
        holder.nametext.setText(model.getName());
        holder.medicineImage.setImageResource(model.getImg());
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_row,parent,false);

        return new MedicineHolder(view);
    }

    public class MedicineHolder extends RecyclerView.ViewHolder {

        TextView nametext;
        ImageView medicineImage;
        public MedicineHolder(@NonNull View itemView) {
            super(itemView);
            nametext=itemView.findViewById(R.id.nametext);
            medicineImage=itemView.findViewById(R.id.medicineImage);
        }
    }
}
