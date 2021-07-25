package com.example.one_tap.presentation.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.one_tap.R;
import com.example.one_tap.databinding.FragmentOTPBinding;
import com.example.one_tap.presentation.viewmodel.PhoneAndOTPViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OTP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OTP extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
   // private FragmentOTPBinding otpBinding;
    // private PhoneAndOTPViewModel otpViewModel;
    // private String mParam2;

    public OTP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OTP.
     */
    // TODO: Rename and change types and number of parameters
    public static OTP newInstance(String param1, String param2) {
        OTP fragment = new OTP();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentOTPBinding otpBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_o_t_p, container, false);
        View view= otpBinding.getRoot();

        PhoneAndOTPViewModel otpViewModel= new ViewModelProvider(this).get(PhoneAndOTPViewModel.class);

        EditText[] editTexts= {otpBinding.editTextOne,
                                otpBinding.editTextTwo,
                                otpBinding.editTextThree,
                                otpBinding.editTextFour};


//        otpBinding.editTextOne.addTextChangedListener(new OTPTextWatcher(editTexts[0], editTexts));
//        otpBinding.editTextTwo.addTextChangedListener(new OTPTextWatcher(editTexts[1], editTexts));
//        otpBinding.editTextThree.addTextChangedListener(new OTPTextWatcher(editTexts[2], editTexts));
//        otpBinding.editTextFour.addTextChangedListener(new OTPTextWatcher(editTexts[3], editTexts));


        String otpText= otpBinding.editTextOne.getText().toString();
        otpViewModel.OTP=otpText;

        otpBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(otpText.length()>0){
//                    Navigation.findNavController(view).navigate(R.id.action_OTP_to_password);
//
//
//                }
//                else{
//                    Toast.makeText(getActivity(),"entries are empty",Toast.LENGTH_LONG).show();
//
//
//
//                }
            }
        });

        return view;
    }
}