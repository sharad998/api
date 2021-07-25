package com.example.udemyroomsassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ApplicantsAppDatabase applicantsAppDatabase;
    private RecyclerView recyclerView;
    private ArrayList<Applicant> applicantArrayList=new ArrayList<>();
    private ApplicantAdapter applicantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicantsAppDatabase= Room.databaseBuilder(getApplicationContext(),ApplicantsAppDatabase.class,"ApplicantDB").allowMainThreadQueries().build();

       //Objects.requireNonNull(getSupportActionBar()).setTitle("Applicants Manager");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Contacts Manager");


        recyclerView= findViewById(R.id.recycler_view_contacts);

        applicantArrayList.addAll(applicantsAppDatabase.getApplicantDAO().getApplicants());

        applicantAdapter= new ApplicantAdapter(this,applicantArrayList,MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(applicantAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Applicant applicantToDelete = applicantArrayList.get(viewHolder.getAdapterPosition());
                deleteApplicant(applicantToDelete,viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(recyclerView);


        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                addAndEditApplicants(false,null,-1);
            }
        });


    }

    public void addAndEditApplicants(final boolean isUpdate, final Applicant applicant,final int position ) {
        LayoutInflater layoutInflaterAndroid= LayoutInflater.from(getApplicationContext());
        View view= layoutInflaterAndroid.inflate(R.layout.layout_add_applicant,null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

       TextView applicantTitle = view.findViewById(R.id.new_applicant_title);
        final EditText newApplicant = view.findViewById(R.id.name);
        final EditText applicantEmail = view.findViewById(R.id.email);
        final EditText applicantCountry = view.findViewById(R.id.country);

        applicantTitle.setText(isUpdate ? "Edit Contact":"Add New Contact" );

        if(isUpdate && applicant!=null){
            newApplicant.setText(applicant.getName());
            applicantEmail.setText(applicant.getEmail());
            applicantCountry.setText(applicant.getCounrty());
        }

        alertDialogBuilderUserInput.setCancelable(false)
                .setPositiveButton(isUpdate ? "Update":"Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                        if (isUpdate) {

                            deleteApplicant(applicant, position);
                        } else {

                            dialogBox.cancel();

                        }

                    }
                });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();


     alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (TextUtils.isEmpty(newApplicant.getText().toString())) {
                Toast.makeText(MainActivity.this, "Enter Applicant name!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                alertDialog.dismiss();
            }


            if (isUpdate && applicant != null) {

                updateApplicant(newApplicant.getText().toString(), applicantEmail.getText().toString(),applicantCountry.getText().toString(), position);
            } else {

                createApplicant(newApplicant.getText().toString(), applicantEmail.getText().toString(), applicantCountry.getText().toString());
            }
        }
    });
}

    private void deleteApplicant(Applicant applicant, int position) {

        applicantArrayList.remove(position);
        applicantsAppDatabase.getApplicantDAO().deleteApplicant(applicant);
        applicantAdapter.notifyDataSetChanged();
    }

    private void updateApplicant(String name, String email, String country, int position) {

        Applicant contact = applicantArrayList.get(position);


        applicantsAppDatabase.getApplicantDAO().updateApplicant(contact);

        applicantArrayList.set(position, contact);

        applicantAdapter.notifyDataSetChanged();


    }

    private void createApplicant(String name, String email, String country) {


        long id = applicantsAppDatabase.getApplicantDAO().addApplicant(new Applicant(0, name, email,country));


        Applicant applicant= applicantsAppDatabase.getApplicantDAO().getApplicant(id);

        if (applicant != null) {

            applicantArrayList.add(0, applicant);
            applicantAdapter.notifyDataSetChanged();

        }

    }




}