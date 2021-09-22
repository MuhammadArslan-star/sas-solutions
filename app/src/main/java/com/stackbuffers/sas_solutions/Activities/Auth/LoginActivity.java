package com.stackbuffers.sas_solutions.Activities.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.stackbuffers.sas_solutions.Activities.MainActivity;
import com.stackbuffers.sas_solutions.R;
import com.stackbuffers.sas_solutions.Utils.AppUtiles;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    TextInputEditText etEmail,etPassword;
    TextView tvForgetPassword,tvLogIn,tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        tvLogIn = findViewById(R.id.tvLogIn);
        tvSignUp = findViewById(R.id.tvSignUp);

        setUpButtons();
    }

    private void setUpButtons() {
        tvLogIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSignUp:{
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            }
            case R.id.tvLogIn:{
                if(AppUtiles.isOnline(this)){

//                    if(dataValidation()){
//
//                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finishAffinity();

                }else{
                    showToast(getString(R.string.net_error));
                }
                break;
            }
        }
    }
    private boolean dataValidation() {

          if(etEmail.getText().toString().isEmpty()){
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.please_enter_your_email));
            return false;

        }
        else if(etPassword.getText().toString().isEmpty()){
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.please_enter_your_password));
            return false;

        }

        return true;
    }
    private void showToast(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}