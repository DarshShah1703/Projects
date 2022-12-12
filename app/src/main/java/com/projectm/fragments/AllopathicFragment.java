package com.projectm.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.projectm.adapters.MedicineCardAdapter;
import com.projectm.cards.MedicineCards;
import com.projectm.R;

public class AllopathicFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    MedicineCardAdapter adapter;


    public AllopathicFragment() {
        // Required empty public constructor
    }
    public static AyurvedicFragment newInstance(String param1, String param2) {
        AyurvedicFragment fragment = new AyurvedicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ayurvedic, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String diseaseName=getActivity().getIntent().getStringExtra("name");
        String diseaseType=getActivity().getIntent().getStringExtra("type");

        FirebaseRecyclerOptions<MedicineCards> options =
                new FirebaseRecyclerOptions.Builder<MedicineCards>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Disease").child(diseaseType).child(diseaseName).child("Medicine").child("Allopathic"), MedicineCards.class)
                        .build();

        adapter=new MedicineCardAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
