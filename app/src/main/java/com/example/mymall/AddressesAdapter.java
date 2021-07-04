package com.example.mymall;

import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.mymall.DeliveryActivity.SELECT_ADDRESS;
import static com.example.mymall.MyAccountFragment.MANAGE_ADDRESS;
import static com.example.mymall.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> {

    private List <AddressesModel> addressesModelList;
    private int MODE;

    private int preSelectedPosition = -1;

    public AddressesAdapter(List<AddressesModel> addressesModelList,int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name = addressesModelList.get(position).getFullname();
        String address = addressesModelList.get(position).getAddress();
        String pincode = addressesModelList.get(position).getPincode();
        Boolean selected = addressesModelList.get(position).getSelected();

        holder.setData(name, address,pincode,selected,position);


    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullname;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer = itemView.findViewById(R.id.option_container);
        }


        private void setData(String username, String userAddress, String userPinCode, Boolean selected, final int position)
        {
            fullname.setText(username);
            address.setText(userAddress);
            pincode.setText(userPinCode);

            if(MODE==SELECT_ADDRESS)
            {
                icon.setImageResource(R.drawable.check);

                if(selected)
                {
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = position;
                }
                else
                {
                    icon.setVisibility(View.GONE);
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(preSelectedPosition!=position)
                        {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPosition).setSelected(false);

                            refreshItem(preSelectedPosition,position);
                            preSelectedPosition = position;
                        }
                    }
                });

            }
            else if(MODE ==MANAGE_ADDRESS)
            {
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.vertical_dots);

                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = position;


                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem(preSelectedPosition, preSelectedPosition);
                        preSelectedPosition =-1;
                    }
                });




            }


        }




    }
}
