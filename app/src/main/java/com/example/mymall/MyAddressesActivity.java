package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.mymall.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myAddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;

    private Button deliverHereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.addresses_recyclerview);

        deliverHereBtn = findViewById(R.id.deliver_here_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List <AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Abhi Khan","Dhaka","1234",true));
        addressesModelList.add(new AddressesModel("Shahrukh Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Amir Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Awal Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Suruz Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Chad Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Salman Khan","Dhaka","1234",false));
        addressesModelList.add(new AddressesModel("Hazrat Khan","Dhaka","1234",false));


        int mode = getIntent().getIntExtra("MODE",-1);

        if(mode == SELECT_ADDRESS)
        {
            deliverHereBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            deliverHereBtn.setVisibility(View.INVISIBLE);
        }

        addressesAdapter = new AddressesAdapter(addressesModelList,mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);


        ((SimpleItemAnimator)myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false); //it removes default animation.

        addressesAdapter.notifyDataSetChanged(); //it refreshes whole list



    }

    public  static void refreshItem(int deselect , int select)
    {
        addressesAdapter.notifyItemChanged(deselect); //it refreshes specific item
        addressesAdapter.notifyItemChanged(select); //it refreshes specific item


    }






    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
