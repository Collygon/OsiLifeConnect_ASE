package com.example.osilifeconnect_ase;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osilifeconnect_ase.DataModels.dummyBloodPressureData;

import java.util.List;


/**
 * The Blood Pressure Adapter handles a list of blood pressure data models
 * and formats them to be shown in the Blood Pressure Activity recycler view.
 */
public class BloodPressureAdapter extends RecyclerView.Adapter<BloodPressureAdapter.MyViewHolder> {

    private List<dummyBloodPressureData> dataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder{

        // each data item is just a string in this case
        //View itemView;
        TextView date;
        TextView systolic;
        TextView diastolic;
        TextView pulse;
        TextView sysNum;
        TextView diaNum;
        TextView pulseNum;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View view){
            super(view);
            //itemView = view;
            date = view.findViewById(R.id.bloodDateView);
            systolic = view.findViewById(R.id.systolicView);
            diastolic = view.findViewById(R.id.diastolicView);
            pulse = view.findViewById(R.id.pulseView);
            sysNum = view.findViewById(R.id.systolicNum);
            diaNum = view.findViewById(R.id.diastolicNum);
            pulseNum = view.findViewById(R.id.pulseNum);
            constraintLayout = view.findViewById(R.id.constraintLayout);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BloodPressureAdapter(List<dummyBloodPressureData> myDataset) {
        dataSet = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BloodPressureAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_blood_pressure, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        holder.date.setText(dataSet.get(position).getDateString());
        holder.sysNum.setText(String.valueOf(dataSet.get(position).getSystolic()));
        holder.diaNum.setText(String.valueOf(dataSet.get(position).getDynostolic()));
        holder.pulseNum.setText(String.valueOf(dataSet.get(position).getPulseRate()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
