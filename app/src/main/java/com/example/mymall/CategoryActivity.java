package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title); //set title at ActionBar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //shows back button at ActionBar

        categoryRecyclerView = findViewById(R.id.category_recyclerview);



        ////Banner Slider

//        List<SliderModel>sliderModelList = new ArrayList<SliderModel>();
//
//        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.abhi,"#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.mipmap.ic_mail_outline_24px,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.app_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.abhi,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.ic_mail_outline_24px,"#077AE4"));



        ////Banner Slider




        ///Horizontal Product layout


//
//        List <HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile,"Redmi 5A", "SD 625 Processor","Rs.5999"));





        ///Horizontal Product layout





        /////////////////////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

       List<HomePageModel> homePageModelList = new ArrayList<>();
//
//        homePageModelList.add(new HomePageModel(0,sliderModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
//        homePageModelList.add(new HomePageModel(2,"Deals of the Day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(3,"Deals of the Day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));
//        homePageModelList.add(new HomePageModel(3,"Deals of the Day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(2,"Deals of the Day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripad,"#000000"));





        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();






    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_icon,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.main_search_icon)
        {
            //todo :search

            return true;
        }
        else if(id== android.R.id.home) //if back button (toolbar) is clicked
        {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
