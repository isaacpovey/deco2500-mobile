package com.u1.group2.unirideshare;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.u1.group2.unirideshare.datamodels.DriverTrip;
import com.u1.group2.unirideshare.datamodels.RiderTrip;
import com.u1.group2.unirideshare.datamodels.User;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateTrip extends AppCompatActivity {

    TextView setTime;
    CheckBox toUniCheckBox;
    CheckBox fromUniCheckBox;
    CheckBox driverCheckBox;
    CheckBox passengerCheckBox;
    CheckBox onceCheckBox;
    CheckBox weeklyCheckBox;
    EditText address;
    Spinner daySelector;
    Spinner timeSelector;
    Button submitButton;


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
        address = findViewById(R.id.addressTextField);
        submitButton = findViewById(R.id.submitButton);
        daySelector = findViewById(R.id.daySelector);
        timeSelector = findViewById(R.id.timeSelector);

        setCheckBoxListener(toUniCheckBox, fromUniCheckBox);
        setCheckBoxListener(driverCheckBox, passengerCheckBox);
        setCheckBoxListener(onceCheckBox, weeklyCheckBox);
        setTime.setOnClickListener(getTimeListener());
        submitButton.setOnClickListener(getButtonListener());

    }

    protected void setCheckBoxListener(CheckBox checkBox1, CheckBox checkBox2) {
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
                        setTime.setText(selectedHour + ":" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    protected boolean checkFormFilled() {
        return (toUniCheckBox.isChecked() || fromUniCheckBox.isChecked()) &&
                (driverCheckBox.isChecked() || passengerCheckBox.isChecked()) &&
                (weeklyCheckBox.isChecked() || onceCheckBox.isChecked()) &&
                !(address.getText().toString().equals(""));
    }

    protected AlertDialog generateNotFilledOutPopup() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateTrip.this);
        builder1.setMessage("Please fill out all forms.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder1.create();
    }

    protected View.OnClickListener getButtonListener() {

        return new View.OnClickListener() {
           @Override
           public void onClick(View view){
               if (checkFormFilled()) {
                   if (driverCheckBox.isChecked()) {
                        DriverTrip trip = new DriverTrip(
                                daySelector.getSelectedItem().toString(),
                                toUniCheckBox.isChecked(),
                                timeSelector.getSelectedItem().toString().equals("Arrive By"),
                                setTime.getText().toString(),
                                weeklyCheckBox.isChecked(),
                                address.getText().toString(),
                                new ArrayList<User>(Arrays.asList(new User("Paul", "Paul", "paul.paul@gmail.com", "45 Windsor Rd Kelvin Grove 4059", "UQ", 19, 5, "254TFG"),
                                        new User("John", "John", "john.john@gmail.com", "389 Newmarket Rd Newmarket 4051", "UQ", 20, 3, "324AGD"))
                        ));
                        Intent intent = new Intent(CreateTrip.this, Dashboard.class    );
                        intent.putExtra("DRIVER_TRIP", trip);
                        startActivity(intent);
                   } else {
                       RiderTrip trip = new RiderTrip(
                               daySelector.getSelectedItem().toString(),
                               toUniCheckBox.isChecked(),
                               timeSelector.getSelectedItem().toString().equals("Arrive By"),
                               setTime.getText().toString(),
                               weeklyCheckBox.isChecked(),
                               address.getText().toString()

                       );
                       Intent intent = new Intent(CreateTrip.this, DriverSelector.class);
                       intent.putExtra("RIDER_TRIP", trip);
                       startActivity(intent);
                   }
               } else {
                   generateNotFilledOutPopup().show();
               }
            }
        };
    }



}
