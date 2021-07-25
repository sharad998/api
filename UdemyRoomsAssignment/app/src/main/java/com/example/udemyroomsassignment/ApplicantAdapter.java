package com.example.udemyroomsassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Applicant> applicantList;
    private MainActivity mainActivity;



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView email;
        private TextView country;

        public MyViewHolder(@NonNull View view) {
            super(view);

            name= view.findViewById(R.id.name);
            email= view.findViewById(R.id.email);
            country=view.findViewById(R.id.country);

        }
    }

    public ApplicantAdapter(Context context, ArrayList<Applicant> applicantArrayList, MainActivity mainActivity) {
        this.context = context;
        this.applicantList = applicantArrayList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.applicant_list_item,parent,false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Applicant applicant = applicantList.get(position);

        holder.name.setText(applicant.getName());
        holder.email.setText(applicant.getEmail());
        holder.country.setText(applicant.getCounrty());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mainActivity.addAndEditApplicants(true, applicant, position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return applicantList.size();
    }


}
