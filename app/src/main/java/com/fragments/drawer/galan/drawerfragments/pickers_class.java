package com.fragments.drawer.galan.drawerfragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class pickers_class extends Fragment implements View.OnClickListener {

    private EditText dateEditText;
    private EditText timeEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.pickers, container, false);

        dateEditText = view.findViewById(R.id.date_editText);
        timeEditText = view.findViewById(R.id.time_editText);

        dateEditText.setOnClickListener(this);
        timeEditText.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.date_editText:
                DialogFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show( getFragmentManager(), "datePicker");
                break;


            case R.id.time_editText:
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getFragmentManager(), "timePicker");
                break;

        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            EditText editText = (EditText) getActivity().findViewById(R.id.time_editText);
            //editText.setText(time);
            String time = "";

            if(hourOfDay > 12){
                editText.setText((hourOfDay - 12) + ":" + minute + " PM");
                //time = (hourOfDay - 12) + ":" + minute + " PM";
            } else if(hourOfDay == 12){
                editText.setText(hourOfDay + ":" + minute + " PM");
                //time = hourOfDay + ":" + minute + " PM";
            } else if(hourOfDay == 0){
                editText.setText("12:" + minute + " AM");
                //time = "12:" + minute + " AM";
            } else {
                editText.setText(hourOfDay + ":" + minute + " AM");
                //time = hourOfDay + ":" + minute + " AM";
            }

            //Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();

        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            EditText editText = (EditText) getActivity().findViewById(R.id.date_editText);
            // Do something with the date chosen by the user
            month += 1; // increment month since month starts with 0
            editText.setText(year+"-"+month+"-"+dayOfMonth);
            //Toast.makeText(getActivity(), year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
        }
    }
}