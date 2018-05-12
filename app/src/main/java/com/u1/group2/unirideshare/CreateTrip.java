package com.u1.group2.unirideshare;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreateTrip extends AppCompatActivity {

    TextView setTime;
    CheckBox toUniCheckBox;
    CheckBox fromUniCheckBox;
    CheckBox driverCheckBox;
    CheckBox passengerCheckBox;
    CheckBox onceCheckBox;
    CheckBox weeklyCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        setTime = findViewById(R.id.timeText);
        toUniCheckBox = findViewById(R.id.toUniCheckBox);
        fromUniCheckBox = findViewById(R.id.fromUniCheckBox);
        driverCheckBox = findViewById(R.id.driverCheckBox);
        passengerCheckBox = findViewById(R.id.passengerCheckBox);
        onceCheckBox = findViewById(R.id.onceCheckBox);
        weeklyCheckBox = findViewById(R.id.weeklyCheckBox);

        setCheckBoxListener(toUniCheckBox, fromUniCheckBox);
        setCheckBoxListener(driverCheckBox, passengerCheckBox);
        setCheckBoxListener(onceCheckBox, weeklyCheckBox);
        setTime.setOnClickListener(getTimeListener());
    }

    protected void setCheckBoxListener(CheckBox checkBox1, CheckBox checkBox2){
        checkBox1.setOnCheckedChangeListener(getCheckBoxListener(checkBox2));
        checkBox2.setOnCheckedChangeListener(getCheckBoxListener(checkBox1));
    }

    protected CompoundButton.OnCheckedChangeListener getCheckBoxListener(CheckBox toToggle) {
        final CheckBox checkbox = toToggle;
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean checked) {
                 if (checked) {
                     checkbox.setChecked(false);
                 }
            }
        };
    }

    protected View.OnClickListener getTimeListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        setTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }



}
