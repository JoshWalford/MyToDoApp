package com.example.mytodoapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.mytodoapp.R;


public class WeekFragment extends Fragment {

    CalendarView calendarView2;

    TextView textView2;

    Button timeBtn2;
    TextView selectedTime2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_week, container,false);

        calendarView2 = view.findViewById(R.id.calendar2);
        textView2 = view.findViewById(R.id.month_txt2);

        timeBtn2 = view.findViewById(R.id.time_picker2);
        selectedTime2 = view.findViewById(R.id.time_month2);

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });
        return inflater.inflate(R.layout.fragment_week, container, false);
    }
}