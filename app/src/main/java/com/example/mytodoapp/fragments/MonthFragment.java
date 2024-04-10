package com.example.mytodoapp.fragments;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mytodoapp.R;
import com.example.mytodoapp.activities.AlarmReceiver;

import java.util.Calendar;

public class MonthFragment extends Fragment {

    private CalendarView calendarView;

    private TimePicker timePicker;
    private TextView textView;
    private Button timeBtn,attachBtn,setBtn,cancelBtn;
    private TextView selectedTime;

    private AlarmManager alarmManager;

    private PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_month, container, false);

        calendarView = view.findViewById(R.id.calendar1);
        textView = view.findViewById(R.id.month_txt);

        timeBtn = view.findViewById(R.id.time_picker);
        selectedTime = view.findViewById(R.id.time_month);

        setBtn = view.findViewById(R.id.setBtn);
        cancelBtn = view.findViewById(R.id.cancelBtn);
        alarmManager = (AlarmManager) requireActivity().getSystemService(ALARM_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                startActivity(new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM));
            }
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month+=1;
                String date = month + "/" + dayOfMonth + "/" + year;
                textView.setText(date);
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        timePicker = new TimePicker(requireActivity());

        setAlarm();

        return view;
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedTime.setText(hourOfDay + ":" + minute);
            }
        },
        timePicker.getHour(),
        timePicker.getMinute(),false);
        timePickerDialog.show();
    }

    private void setAlarm() {
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                int hourOfDay = timePicker.getHour();
                int minute = timePicker.getMinute();

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);

                calendar.set(Calendar.DAY_OF_MONTH,(int) calendarView.getDate());

                Intent intent = new Intent(requireActivity(),AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(requireActivity(),0,intent,
                        PendingIntent.FLAG_CANCEL_CURRENT| PendingIntent.FLAG_MUTABLE);

                AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),pendingIntent);
                alarmManager.setAlarmClock(alarmClockInfo,pendingIntent);
                Toast.makeText(requireActivity(), "Alarm on", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","Alarm set");
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(requireActivity(),"Alarm off",Toast.LENGTH_SHORT).show();
                Log.d("MainActivity","Alarm cancled");
            }
        });
    }
}
