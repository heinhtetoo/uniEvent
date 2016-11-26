package com.sd.heinhtetoo.mytestapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator's user on 26-Nov-16.
 */

public class AddEventDialogFragment extends DialogFragment implements View.OnClickListener, View.OnFocusChangeListener {

    Button btnSubmit;

    EditText etName;
    EditText etEventDate;
    EditText etEventTime;
    EditText etPlace;
    EditText etDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_new_event, container, false);

        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        etName = (EditText) view.findViewById(R.id.et_event_name);
        etName.setOnClickListener(this);

        etDescription = (EditText) view.findViewById(R.id.et_description);
        etDescription.setOnClickListener(this);

        etPlace = (EditText) view.findViewById(R.id.et_place);
        etPlace.setOnClickListener(this);

        etEventDate = (EditText) view.findViewById(R.id.et_event_date);
        etEventDate.setOnClickListener(this);
        etEventDate.setOnFocusChangeListener(this);

        etEventTime = (EditText) view.findViewById(R.id.et_event_time);
        etEventTime.setOnClickListener(this);
        etEventTime.setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.et_event_time:
                showTimePickerDialog();
                break;

            case R.id.et_event_date:
                showDatePickerDialog();
                break;

            case R.id.btn_submit:
                processData();
                break;
        }
    }

    private void processData() {
        String name = etName.getText().toString();
        String eventDate = etEventDate.getText().toString();
        String eventTime = etEventTime.getText().toString();
        String place = etPlace.getText().toString();
        String description = etDescription.getText().toString();

        if(name.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || place.isEmpty() || description.isEmpty())
        {
            Toast.makeText(getContext(),"Complete The Form",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
        }

    }

    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Month starts from zero
                month += 1;
                etEventDate.setText(year + "-" + month + "-" + day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String shour = "" + hour;
                String smin = "" + min;
                if (hour < 10) {
                    shour = "0" + hour;
                }
                if (min < 10) {
                    smin = "0" + min;
                }
                etEventTime.setText(shour + ":" + smin);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id) {
            case R.id.et_event_time:
                if (b) {
                    showTimePickerDialog();
                }
                break;

            case R.id.et_event_date:
                if (b) {
                    showDatePickerDialog();
                }
                break;
        }
    }


}
