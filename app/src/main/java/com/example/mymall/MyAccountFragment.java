package com.example.mymall;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    public MyAccountFragment() {
        // Required empty public constructor
    }

    public  static final  int MANAGE_ADDRESS = 1;
    private Button viewAllAddressBtn;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_account, container, false);

        viewAllAddressBtn = view.findViewById(R.id.view_all_addresses_btn);

        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyAddressesActivity.class);
                intent.putExtra("MODE",MANAGE_ADDRESS);
                startActivity(intent);
            }
        });


        return view;
    }
}
