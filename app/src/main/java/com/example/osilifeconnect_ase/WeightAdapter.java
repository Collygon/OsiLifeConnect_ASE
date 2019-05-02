package com.example.osilifeconnect_ase;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osilifeconnect_ase.DataModels.dummyDataWeight;

import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder> {
    private List<dummyDataWeight> weightSet;

    public class WeightViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView weight;
        TextView lbs;
        ConstraintLayout constraintLayout;

        public WeightViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.weightDateView);
            weight = view.findViewById(R.id.weightWeightNum);
            lbs = view.findViewById(R.id.lbsView);
            constraintLayout = view.findViewById(R.id.weightConstraintLayout);
        }
    }

    public WeightAdapter(List<dummyDataWeight> dataSet) {
        weightSet = dataSet;
    }

    @NonNull
    @Override
    public WeightAdapter.WeightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weight_rec_view, viewGroup, false);
        WeightViewHolder viewHolder= new WeightViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeightViewHolder viewHolder, int i) {
        viewHolder.date.setText(weightSet.get(i).getDate().toString());
        //viewHolder.date.setText("02-05-19");

        viewHolder.weight.setText(String.valueOf(weightSet.get(i).getWeightLbs()));
    }

    @Override
    public int getItemCount() {
        return weightSet.size();
    }
}
