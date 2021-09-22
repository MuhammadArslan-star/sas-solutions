package com.stackbuffers.sas_solutions.Activities.Auth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.stackbuffers.sas_solutions.R;
import com.stackbuffers.sas_solutions.Utils.AppUtiles;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    TextInputEditText etName,etEmail,etPhone,etAddress,etPassword,etCPassword;
    TextView tvLogIn,tvSignUp;
    ImageView ivProfileImage;
    LinearLayout llSeleceImage;
    NestedScrollView scrollView;
    RadioButton rbMale,rbFemale;


//    class varibles
    String profileImage="";
    private final static int PICKPROFILE_RESULT_CODE=1021;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);

        tvLogIn = findViewById(R.id.tvLogIn);
        tvSignUp = findViewById(R.id.tvSignUp);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        llSeleceImage = findViewById(R.id.llSeleceImage);

        scrollView = findViewById(R.id.scrollView);

        setUpButtons();
    }

    private void setUpButtons() {
        tvLogIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        llSeleceImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvLogIn:{
                finish();
                break;
            }
            case R.id.tvSignUp:{
                if(AppUtiles.isOnline(this)){
                    finish();
//                    if(dataValidation()){
//
//                    }

                }else{
                    showToast(getString(R.string.net_error));
                }
                break;
            }
            case R.id.llSeleceImage:{

                if (AppUtiles.hasPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    ImagePicker.Companion.with(RegisterActivity.this)
                            .cropSquare()            //Crop image(Optional), Check Customization for more option
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .maxResultSize(512, 512)    //Final image resolution will be less than 1080 x 1080(Optional)
                            .start();

                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICKPROFILE_RESULT_CODE);
                }

                break;
            }
        }
    }

    private boolean dataValidation() {

        if(profileImage.equals("") || profileImage==null){
            showToast(getString(R.string.select_profile_image));
            scrollView.fullScroll(ScrollView.FOCUS_UP);
            return false;
        }
        else if(etName.getText().toString().isEmpty()){
            etName.requestFocus();
            etName.setError(getString(R.string.please_enter_your_name));
            return false;

        }
        else if(etEmail.getText().toString().isEmpty()){
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.please_enter_your_email));
            return false;

        }
        else if(etPhone.getText().toString().isEmpty()){
            etPhone.requestFocus();
            etPhone.setError(getString(R.string.please_enter_your_phone));
            return false;

        }
        else if(etAddress.getText().toString().isEmpty()){
            etAddress.requestFocus();
            etAddress.setError(getString(R.string.please_enter_your_address));
            return false;

        }
        else if(etPassword.getText().toString().isEmpty()){
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.please_enter_your_password));
            return false;

        }

        else if(etCPassword.getText().toString().isEmpty()){
            etCPassword.requestFocus();
            etCPassword.setError(getString(R.string.please_re_enter_your_password));
            return false;

        }

        else if(!etCPassword.getText().toString().equals(etPassword.getText().toString())){
            etCPassword.requestFocus();
            etCPassword.setError(getString(R.string.password_not_match));
            return false;

        }

        return true;
    }



    @Override
    public void onRequestPermissionsResult ( int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PICKPROFILE_RESULT_CODE: {

                if (AppUtiles.hasPermission(this, permissions)) {
                    ImagePicker.Companion.with(RegisterActivity.this)
                            .cropSquare()            //Crop image(Optional), Check Customization for more option
                            .compress(1024)            //Final image size will be less than 1 MB(Optional)
                            .maxResultSize(512, 512)    //Final image resolution will be less than 1080 x 1080(Optional)
                            .start();

                } else {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.permission_required), Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if ( resultCode == Activity.RESULT_OK) {

            try{
                if(data!=null) {
                    final InputStream imageStream = getContentResolver().openInputStream(data.getData());
                    Bitmap bm = BitmapFactory.decodeStream(imageStream);
                    profileImage = encodeImage(bm);
                    ivProfileImage.setVisibility(View.VISIBLE);
                    ivProfileImage.setImageURI(data.getData());
                }
            }catch (Exception e){
                Log.v("Exception",e.getMessage());
            }

        }


    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = /*"data:image/;base64,"+*/ Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }






    private void showToast(String message){
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}