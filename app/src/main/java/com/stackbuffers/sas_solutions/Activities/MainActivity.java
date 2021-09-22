package com.stackbuffers.sas_solutions.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.stackbuffers.sas_solutions.Activities.Profile.ProfileActivity;
import com.stackbuffers.sas_solutions.Activities.Tecnication.TecnicationsActivity;
import com.stackbuffers.sas_solutions.Adapters.Category.CategoryAdapter;
import com.stackbuffers.sas_solutions.Model.Categories.CategoryModel;
import com.stackbuffers.sas_solutions.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    ImageView ivProfileImage;
    EditText etSearch;
    ImageView ivSearch;
    CarouselView carouselView;
    RecyclerView rvCategories;


//    class varibles
    ArrayList<Integer> slidersModelList=new ArrayList<>();
    ArrayList<CategoryModel> categoryModelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        ivProfileImage = findViewById(R.id.ivProfileImage);
        etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
        carouselView = findViewById(R.id.carouselView);
        rvCategories = findViewById(R.id.rvCategories);

        setUpButtons();
    }

    private void setUpButtons() {
        ivProfileImage.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

        slidersModelList.add(R.drawable.banner1);
        slidersModelList.add(R.drawable.banner2);
        slidersModelList.add(R.drawable.banner3);

        setUpSliderImages(slidersModelList);

        {
            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.car_panter);
                categoryModel.setName("Car Panter");
                categoryModelList.add(categoryModel);
            }

            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.car_maintenance);
                categoryModel.setName("Car Maintenance");
                categoryModelList.add(categoryModel);
            }

            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.plumber);
                categoryModel.setName("Plumber");
                categoryModelList.add(categoryModel);
            }



            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.electrician);
                categoryModel.setName("Electrician");
                categoryModelList.add(categoryModel);
            }


            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.construction);
                categoryModel.setName("Construction");
                categoryModelList.add(categoryModel);
            }

            {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setImage("" + R.drawable.housekeeping);
                categoryModel.setName("Housekeeping");
                categoryModelList.add(categoryModel);
            }


        }

        setUpRecyclerView(categoryModelList);


    }

    private void setUpRecyclerView(ArrayList<CategoryModel> categoryModelList) {
        rvCategories.setLayoutManager(new GridLayoutManager(this,2));
        rvCategories.setHasFixedSize(true);
        CategoryAdapter categoryAdapter=new CategoryAdapter(this,categoryModelList,"");
        rvCategories.setAdapter(categoryAdapter);

        categoryAdapter.setOnClickListener(new CategoryAdapter.OnClickLitener() {
            @Override
            public void onClick(int position, CategoryModel categoryModel) {
                startActivity(new Intent(MainActivity.this, TecnicationsActivity.class)
                .putExtra("categoryModel",categoryModel));
            }
        });
    }

    //screen top slider function
    private void setUpSliderImages(ArrayList<Integer> slidersModelList) {

        try{

            ImageListener imageListener = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(slidersModelList.get(position));

                }
            };
            carouselView.setImageListener(imageListener);
            carouselView.setPageCount(slidersModelList.size());


        }
        catch (Exception e){
            Log.v("Exception",e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivProfileImage:{
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            }
            case R.id.ivSearch:{
                break;
            }
        }
    }
}