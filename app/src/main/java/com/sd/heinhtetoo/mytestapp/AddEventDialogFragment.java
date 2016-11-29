package com.sd.heinhtetoo.mytestapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.facebook.Profile;
import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModel;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator's user on 26-Nov-16.
 */

public class AddEventDialogFragment extends DialogFragment implements View.OnClickListener, View.OnFocusChangeListener {

    private static final String SIMPLE_DATE_FORMATE ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" ;
    Button btnSubmit;

    EditText etName;
    EditText etEventDate;
    EditText etEventTime;
    EditText etPlace;
    EditText etDescription;
    private String smin;
    private String shour;

    private int gmin;
    private int ghour;
    private int gday;
    private int gyear;
    private int gmonth;

    TextInputLayout tilName;
    TextInputLayout tilEventDate;
    TextInputLayout tilEventTime;
    TextInputLayout tilPlace;
    TextInputLayout tilDescription;

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

        tilName = (TextInputLayout) view.findViewById(R.id.til_event_name);
        tilEventDate = (TextInputLayout) view.findViewById(R.id.til_event_date);
        tilEventTime = (TextInputLayout) view.findViewById(R.id.til_event_time);
        tilPlace = (TextInputLayout) view.findViewById(R.id.til_place);
        tilDescription = (TextInputLayout) view.findViewById(R.id.til_description);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    tilName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etEventDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    tilEventDate.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etEventTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    tilEventTime.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    tilPlace.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0){
                    tilDescription.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
            if(name.isEmpty()) {
                tilName.setErrorEnabled(true);
                tilName.setError("You need to enter a name");
            }else{

                //tilName.setErrorEnabled(false);
            }
            if(eventDate.isEmpty()){
                tilEventDate.setErrorEnabled(true);
                tilEventDate.setError("You need to enter event date");
            }else{
                tilName.setErrorEnabled(false);
            }
            if(eventTime.isEmpty()){
                tilEventTime.setErrorEnabled(true);
                tilEventTime.setError("You need to enter event time");
            }else{
                tilName.setErrorEnabled(false);
            }
            if(place.isEmpty()){
                tilPlace.setErrorEnabled(true);
                tilPlace.setError("You need to enter a place");
            }else{
                tilName.setErrorEnabled(false);
            }
            if(description.isEmpty()){
                tilDescription.setErrorEnabled(true);
                tilDescription.setError("You need to enter description about the event");
            }else{
                tilName.setErrorEnabled(false);
            }
        }else
        {
            String username = Profile.getCurrentProfile().getName();
            String fid = Profile.getCurrentProfile().getId();
            EventModelImpl.getInstance().postEvent(new Event("",name,place,eventTime,eventDate,getPublishedDate(),description,username,fid));
            Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
            this.dismiss();
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
                gyear = year;
                gday = day;
                gmonth = month + 1;
                etEventDate.setText(year + "-" + gmonth + "-" + day);
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
                ghour = hour;
                gmin = min;

                shour = "" + hour;
                smin = "" + min;
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

    public String getPublishedDate(){
        SimpleDateFormat format = new SimpleDateFormat(SIMPLE_DATE_FORMATE);

        Calendar c = Calendar.getInstance();
        c.set(gyear, gmonth, gday, ghour, gmin);
        format.setTimeZone( c.getTimeZone());
        String publishedDate = format.format(c.getTime());
        return publishedDate;
    }


}
