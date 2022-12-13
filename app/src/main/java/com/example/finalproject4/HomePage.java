package com.example.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class HomePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView datang,kembali;
    private ImageButton dateButton,returnButton,backLogin;
    private Button search;
    private DatePickerDialog datePickerDialog;
    private Spinner from,to;
    AlertDialog.Builder builder;
    String tampung = ""; //buat nampung isi  Date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initDatePicker();
        dateButton = findViewById(R.id.datepicker);
        returnButton = findViewById(R.id.returnpicker);
        search = findViewById(R.id.search);
        datang = findViewById(R.id.tanggalBerangkat);
        kembali = findViewById(R.id.tanggalKembali);

        from = findViewById(R.id.From); // spinner
        to = findViewById(R.id.To); // spinner
        backLogin = findViewById(R.id.backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listTempat, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(adapter);
        from.setOnItemSelectedListener(this);
        to.setAdapter(adapter);
        to.setOnItemSelectedListener(this);

        datang.setText(getTodayDate());
        kembali.setText(getTodayDate());
        builder = new AlertDialog.Builder(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DetailPesanan.class);
                String dataDatang = datang.getText().toString();
                String dataKembali = kembali.getText().toString();
                String dataFrom = from.getSelectedItem().toString();
                String DataTo = to.getSelectedItem().toString();
                String DataTujuan = dataFrom + " to " + DataTo;
                if (dataDatang.equals(DataTo)){
                   //pop up gagal
                }
                else{
                    intent.putExtra("datatujuan",DataTujuan);
                    intent.putExtra("tanggalAwal",datang.getText().toString());
                    intent.putExtra("tanggalKembali",kembali.getText().toString());
                    startActivity(intent);
                }
            }
        });
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView)parent.getChildAt(0)).setTextColor(Color.parseColor("#FF000000"));
        if (to.getSelectedItem().toString().equals(from.getSelectedItem().toString())){
            TextView errorText = (TextView)to.getSelectedView();
            errorText.setError("Destinasi tidak boleh sama");
            errorText.requestFocus();
            return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}