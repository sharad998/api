package com.example.one_tap.presentation.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.one_tap.R;
import com.example.one_tap.databinding.FragmentPhoneBinding;
import com.example.one_tap.presentation.viewmodel.PhoneAndOTPViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Phone newInstance} factory method to
 * create an instance of this fragment.
 */
public class Phone extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);








    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        FragmentPhoneBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_phone, container, false);
        View view = binding.getRoot();
        ;

        PhoneAndOTPViewModel phoneViewModel= new ViewModelProvider(this).get(PhoneAndOTPViewModel.class);
        phoneViewModel.phoneNumber= binding.tenDigitNumber.getText().toString();

        binding.GetOTPClick.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
//                Log.e("MAST",phoneViewModel.phoneNumber);
//                Navigation.findNavController(view).navigate(R.id.action_phone_to_OTP);


            }


        });

        //here data must be an instance of the class MarsDataProvider
        return view;
        //return inflater.inflate(R.layout.fragment_phone, container, false);

    }



}

//nave_graph_page1_phone_otp.xml