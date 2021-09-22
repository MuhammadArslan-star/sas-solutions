package com.stackbuffers.sas_solutions.Activities.Booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.stackbuffers.sas_solutions.Activities.MainActivity;
import com.stackbuffers.sas_solutions.Model.Categories.CategoryModel;
import com.stackbuffers.sas_solutions.Model.Tecnication.TecnitionModel;
import com.stackbuffers.sas_solutions.R;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

//    class views
    ImageView ivBack;
    TextView tvTitle,tvTecnitionName;
    RelativeLayout rlDate,rlTime;
    TextView tvDate,tvTime,tvBookNow;

//    class varibles and obj
    TecnitionModel tecnitionModel;
    CategoryModel categoryModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            tecnitionModel=(TecnitionModel) bundle.getSerializable("tecnitionModel");
            categoryModel =(CategoryModel) bundle.getSerializable("categoryModel");
        }
        initViews();
    }

    private void initViews() {
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvTecnitionName = findViewById(R.id.tvTecnitionName);

        rlDate = findViewById(R.id.rlDate);
        rlTime = findViewById(R.id.rlTime);

        tvTime = findViewById(R.id.tvTime);
        tvDate = findViewById(R.id.tvDate);

        tvBookNow = findViewById(R.id.tvBookNow);

        tvTecnitionName.setText(tecnitionModel.getName());
        tvTitle.setText(categoryModel.getName());

        setUpButtons();
    }

    private void setUpButtons() {
        ivBack.setOnClickListener(this);
        rlDate.setOnClickListener(this);
        rlTime.setOnClickListener(this);
        tvBookNow.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:{
                finish();
                break;
            }
            case R.id.rlDate:{
                datePicker();
                break;
            }
            case R.id.rlTime:{
                openTimePicker();
                break;
            }
            case R.id.tvBookNow:{
              popUpBookingComplete();
                break;
            }
        }

    }

    private void showToast(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    //date time picker
    //if selectionType true so its mean time_to selected if selectionType false so its mean time_from selected
    private void openTimePicker() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                try{
                    String time=  getTime(selectedHour,selectedMinute);
                    tvTime.setText(time);
                    //  ed_time.setText(hur + ":" + min+" "+AM_PM);
                }catch (Exception e){
                    Log.v("Time Exception",e.getMessage());
                }

            }
        }, hour, minute, true);//Yes 24 hour time
//        mTimePicker.setTitle(getString(R.string.select_time));
        mTimePicker.show();

    }

    private String getTime(int hr,int min) {
        Time tme = new Time(hr,min,0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm aa");
        return formatter.format(tme);
    }
    private void datePicker(){
        int day, month, year;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //2021-03-15
                try {
                    String mth ,day;
                    month=month+1;
                    if(month < 10) {
                        mth = "0"+month;
                    } else {
                        mth = ""+month;
                    }
                    if(dayOfMonth < 10) {
                        day = "0"+dayOfMonth;
                    } else {
                        day = ""+dayOfMonth;
                    }
                    String dt = year + "-" + mth + "-" + day;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date1 = simpleDateFormat.format(simpleDateFormat.parse(dt));
                    tvDate.setText(date1);
                } catch (Exception e) {
                    Log.v("Date Exception", e.getMessage());
                }
            }


        }, year, month, day);
//        datePickerDialog.setTitle(getString(R.string.select_date));
        datePickerDialog.show();

    }


//    booking successful
    private void popUpBookingComplete() {
        try{

            AlertDialog alertDialog;
            View dialogView;
            TextView tvOk;
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();

            dialogView = inflater.inflate(R.layout.pop_up_success, null);
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


            tvOk=dialogView.findViewById(R.id.tvOk);


            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BookingActivity.this, MainActivity.class));
                    finishAffinity();
                    alertDialog.dismiss();
                }
            });

            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
        catch (Exception e){
            Log.v("ERROR",e.getMessage());
        }
    }

}