package com.example.mytodoapp;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;

public class MonthFragment extends Fragment {

    CalendarView calendarView;
    TextView textView;

    Button timeBtn;
    TextView selectedTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_month, container, false);

        calendarView = view.findViewById(R.id.calendar1);
        textView = view.findViewById(R.id.month_txt);

        timeBtn = view.findViewById(R.id.time_picker);
        selectedTime = view.findViewById(R.id.time_month);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                textView.setText(date);
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return view;
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedTime.setText(hourOfDay + ":" + minute);
            }
        }, 0, 0, false);
        timePickerDialog.show();
    }
}
