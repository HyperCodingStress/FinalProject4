package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {
    private TextView datang,kembali;
    private ImageButton dateButton,returnButton;
    private DatePickerDialog datePickerDialog;
    String tampung = ""; //buat nampung isi tombol
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initDatePicker();
        dateButton = findViewById(R.id.datepicker);
        returnButton = findViewById(R.id.returnpicker);
        datang = findViewById(R.id.tanggalBerangkat);
        kembali = findViewById(R.id.tanggalKembali);
        datang.setText(getTodayDate());
        kembali.setText(getTodayDate());
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String  data = makeDateString(day,month,year);
                if (tampung.equals("datang")){
                    datang.setText(data);
                }else{
                    kembali.setText(data);
                }
            }

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,day,month,year);
        System.out.println(year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JAN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DES";
        return "JAN";
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
        tampung = "datang";
    }

    public void returnDatePicker(View view) {
        datePickerDialog.show();
        tampung = "kembali";
    }


}