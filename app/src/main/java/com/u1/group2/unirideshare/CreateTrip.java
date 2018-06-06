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
    EditText address;
    Spinner daySelector;
    Spinner timeSelector;
    Spinner uniSelector;
    Spinner driverSelector;
    Spinner repeatingSelector;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        setTime = findViewById(R.id.timeText);

        address = findViewById(R.id.addressTextField);
        submitButton = findViewById(R.id.submitButton);
        daySelector = findViewById(R.id.daySelector);
        timeSelector = findViewById(R.id.timeSelector);
        uniSelector = findViewById(R.id.uniSelector);
        driverSelector = findViewById(R.id.driverSelector);
        repeatingSelector = findViewById(R.id.repeatingSelector);

        setTime.setOnClickListener(getTimeListener());
        submitButton.setOnClickListener(getButtonListener());

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
               if (!(address.getText().toString().equals(""))) {
                   if (driverSelector.getSelectedItem().toString().equals("Driver")) {
                        DriverTrip trip = new DriverTrip(
                                daySelector.getSelectedItem().toString(),
                                uniSelector.getSelectedItem().toString().equals("To Uni"),
                                timeSelector.getSelectedItem().toString().equals("Arrive By"),
                                setTime.getText().toString(),
                                repeatingSelector.getSelectedItem().toString().equals("Repeating"),
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
                               uniSelector.getSelectedItem().toString().equals("To Uni"),
                               timeSelector.getSelectedItem().toString().equals("Arrive By"),
                               setTime.getText().toString(),
                               repeatingSelector.getSelectedItem().toString().equals("Repeating"),
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
