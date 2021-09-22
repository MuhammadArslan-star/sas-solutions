package com.stackbuffers.sas_solutions.Activities.Tecnication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stackbuffers.sas_solutions.Activities.Booking.BookingActivity;
import com.stackbuffers.sas_solutions.Activities.MainActivity;
import com.stackbuffers.sas_solutions.Adapters.Category.CategoryAdapter;
import com.stackbuffers.sas_solutions.Adapters.Tecniations.TecnicationsAdapter;
import com.stackbuffers.sas_solutions.Model.Categories.CategoryModel;
import com.stackbuffers.sas_solutions.Model.Tecnication.TecnitionModel;
import com.stackbuffers.sas_solutions.R;

import java.util.ArrayList;

public class TecnicationsActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    ImageView ivBack;
    TextView tvTitle;
    RecyclerView rvTecnications;

//    class varibles and onject
    CategoryModel categoryModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnications);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            categoryModel= (CategoryModel) bundle.getSerializable("categoryModel");
        }

        initViews();

    }

    private void initViews() {
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        rvTecnications = findViewById(R.id.rvTecnications);

        setUpButtons();
    }

    private void setUpButtons() {
        ivBack.setOnClickListener(this);
        tvTitle.setText(categoryModel.getName());


        setUpRecyclerView();


    }

    private void setUpRecyclerView() {
        rvTecnications.setLayoutManager(new LinearLayoutManager(this));
        rvTecnications.setHasFixedSize(true);
        TecnicationsAdapter tecnicationsAdapter=new TecnicationsAdapter(this);
        rvTecnications.setAdapter(tecnicationsAdapter);

        tecnicationsAdapter.setOnClickListener(new TecnicationsAdapter.OnClickLitener() {
            @Override
            public void onClick(int position, TecnitionModel tecnitionModel) {
                startActivity(new Intent(TecnicationsActivity.this, BookingActivity.class)
                        .putExtra("tecnitionModel",tecnitionModel)
                        .putExtra("categoryModel",categoryModel));
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:{
                finish();
                break;
            }
        }
    }
}