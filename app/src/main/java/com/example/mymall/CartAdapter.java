package com.example.mymall;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {


    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {

        switch (cartItemModelList.get(position).getType()) {
            case 0:

                return CartItemModel.CART_ITEM;

            case 1:

                return CartItemModel.TOTAL_AMOUNT;

            default:
                return -1;
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType)
        {
            case CartItemModel.CART_ITEM:

                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new CartItemViewHolder(cartItemView);


            case CartItemModel.TOTAL_AMOUNT:

                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new CartTotalAmountViewHolder(cartTotalView);

            default:

                return null;
        }



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType())
        {
            case CartItemModel.CART_ITEM:

                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupens = cartItemModelList.get(position).getFreeCoupens();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();
                int offersApplied = cartItemModelList.get(position).getOffersApplied();


                ((CartItemViewHolder)holder).setItemDetails(resource,title,freeCoupens,productPrice,cuttedPrice,offersApplied);

                break;

             case CartItemModel.TOTAL_AMOUNT:

                 String totalItems = cartItemModelList.get(position).getTotalItems();
                 String totalItemPrice = cartItemModelList.get(position).getTotalItemPrice();
                 String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                 String totalAmount = cartItemModelList.get(position).getTotalAmount();
                 String savedAmount = cartItemModelList.get(position).getSavedAmount();

                 ((CartTotalAmountViewHolder)holder).setTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);




                 break;

            default:

                return ;



        }





    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends  RecyclerView.ViewHolder
    {
        private ImageView productImage;
        private ImageView freeCoupenIcon;
        private TextView productTitle;
        private TextView freeCoupens;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView coupensApplied;
        private TextView productQuantity;




        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle  = itemView.findViewById(R.id.product_title);
            freeCoupenIcon = itemView.findViewById(R.id.free_coupon_icon);
            freeCoupens = itemView.findViewById(R.id.tv_free_coupon);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            coupensApplied = itemView.findViewById(R.id.coupens_applied);
            productQuantity = itemView.findViewById(R.id.product_quantity);

        }

        private void setItemDetails(int resource , String title, int freeCoupensNo,String productPriceText,String cuttedPriceText, int offersAppliedNo)
        {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(freeCoupensNo > 0) {

                freeCoupenIcon.setVisibility(View.VISIBLE);
                freeCoupens.setVisibility(View.VISIBLE);

                if(freeCoupensNo ==1)
                {
                    freeCoupens.setText("free " + freeCoupensNo + " Coupen");
                }
                else{
                      freeCoupens.setText("free "+freeCoupensNo+" Coupens");
                }
            }
            else {

                freeCoupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }

            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

            if(offersAppliedNo >0)
            {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + " Offers applied");
            }
            else {
                offersApplied.setVisibility(View.INVISIBLE);
            }

            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog  quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                    quantityDialog.setCancelable(false);

                    final EditText quantityNo = quantityDialog.findViewById(R.id.quantity_no);
                    Button cancelBt  = quantityDialog.findViewById(R.id.cancel_btn);
                    Button okBtn = quantityDialog.findViewById(R.id.ok_btn);

                    cancelBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });

                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productQuantity.setText("Qty: "+quantityNo.getText());
                            quantityDialog.dismiss();

                        }
                    });

                    quantityDialog.show();


                }
            });


        }





    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder
    {
        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;





        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);

        }


        private void setTotalAmount(String totalItemText, String totaItemPriceText, String deliveryPriceText, String totalAmountText,String savedAmountText)
        {
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totaItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }

    }




}









