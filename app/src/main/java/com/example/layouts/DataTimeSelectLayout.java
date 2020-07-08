package com.example.layouts;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import com.example.uicustomviews.R;

import java.util.Calendar;
import java.util.Date;

public class DataTimeSelectLayout extends LinearLayout {
    public DataTimeSelectLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.date_time_select, this);
        final EditText dateEdit = (EditText) findViewById(R.id.date_edit);
        final EditText timeEdit = (EditText) findViewById(R.id.time_edit);

        Calendar calendar = Calendar.getInstance();
        Date time  = new Date();
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth= calendar.get(Calendar.MONTH)+1;
        final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        final int currentHour = time.getHours();
        final int currentMinute = time.getMinutes();


        dateEdit.setText(String.format("%d-%d-%d", currentYear
                , currentMonth, currentDay));
        timeEdit.setText(String.format("%d:%d" , currentHour, currentMinute));

        dateEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateEdit.setText(String.format("%d-%d-%d", year, month+1, dayOfMonth));
                    }
                },currentYear,currentMonth-1,currentDay).show();
            }
        });



        timeEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeEdit.setText(String.format("%d:%d",hourOfDay,minute));
                    }
                },currentHour,currentMinute,true).show();
            }
        });
    }
}
