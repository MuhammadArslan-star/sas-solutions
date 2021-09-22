package com.stackbuffers.sas_solutions.Activities.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.stackbuffers.sas_solutions.Activities.Auth.LoginActivity;
import com.stackbuffers.sas_solutions.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    ImageView ivBack;
    CardView cvLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
    }

    private void initViews() {
        ivBack = findViewById(R.id.ivBack);
        cvLogout = findViewById(R.id.cvLogout);

        setUpButtons();
    }

    private void setUpButtons() {
        ivBack.setOnClickListener(this);
        cvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:{
                finish();
                break;
            }
            case R.id.cvLogout:{
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finishAffinity();
                break;
            }
        }
    }
}