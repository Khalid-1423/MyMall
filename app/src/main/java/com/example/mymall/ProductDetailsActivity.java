package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private Button coupenRedeemBtn;

    //coupendialog
    public static TextView coupenTitle;
    public static TextView  coupenExpiryDate;
    public static TextView coupenBody;
    public static RecyclerView coupensRecyclerView;
    private static LinearLayout selectedCoupen;
    //coupendialog





    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistBtn;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    //rating layout

    private LinearLayout rateNowContainer;

    //rating layout

    private Button buyNowBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager);

        addToWishlistBtn = findViewById(R.id.add_to_wishlist_btn);

        buyNowBtn = findViewById(R.id.buy_now_btn);
        coupenRedeemBtn =findViewById(R.id.coupon_redemption_btn);



        List <Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.product_images);
        productImages.add(R.drawable.product_images);
        productImages.add(R.drawable.product_images);
        productImages.add(R.drawable.product_images);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewpagerIndicator.setupWithViewPager(productImagesViewPager , true);

        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ALREADY_ADDED_TO_WISHLIST)
                {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }
                else
                {
                    ALREADY_ADDED_TO_WISHLIST = true;

                    addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));



                }

            }
        });

        productDetailsViewPager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tabLayout);

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //rating layout


        rateNowContainer = findViewById(R.id.rate_now_container);

        for(int x=0; x<rateNowContainer.getChildCount();x++)
        {
            final  int starPosition = x;

            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setRating(starPosition);
                }
            });
        }


        //rating layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),DeliveryActivity.class);
                startActivity(intent);


            }
        });

        //// coupen dialog
        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
        coupensRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerview);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);

        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.coupen_title);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);





        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupensRecyclerView.setLayoutManager(layoutManager);






        List <RewardModel> rewardModelList  = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Buy1 geet 1 free","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));

        rewardModelList.add(new RewardModel("Cashback","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Buy1 geet 1 free","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));

        rewardModelList.add(new RewardModel("Cashback","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));
        rewardModelList.add(new RewardModel("Buy1 geet 1 free","till 2nd,June 2016","GET 20% CASHBACK on any product above Rs.200/- and below Rs.3000/-"));


        MyRewardsAdapter  myRewardsAdapter = new MyRewardsAdapter(rewardModelList,true);
        coupensRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        //// coupen dialog

        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogRecyclerView();

            }
        });



        coupenRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                checkCoupenPriceDialog.show();


            }
        });

    }

    public static  void showDialogRecyclerView()
    {
        if(coupensRecyclerView.getVisibility() == View.GONE)
        {
            coupensRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        }
        else
        {
            coupensRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }

    }


    private void setRating(int starPosition) {

        for(int x=0;x <rateNowContainer.getChildCount();x++)
        {
            ImageView startBtn = (ImageView) rateNowContainer.getChildAt(x);

            startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if(x <= starPosition)
            {
                startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }

        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_and_cart_icon,menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id== android.R.id.home)
        {
            finish();

            return true;
        }
        else if(id==R.id.main_search_icon)
        {
            //todo :notification

            return true;


        }
        else if(id==R.id.main_cart_icon)
        {
            //todo :cart


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
