package com.example.paymentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MonthActivity extends AppCompatActivity {
    private TextView entryTime, exitTime;
    private String currentDate;
    private ProgressBar progressBar;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        CalendarView calendarView = findViewById(R.id.calendarView);

        entryTime = findViewById(R.id.entryDataTime);
        exitTime = findViewById(R.id.exitDataTime);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);


        Button back_btn = findViewById(R.id.go_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

// Set the desired year and month (0-based for months: January is 0, February is 1, and so on)
        Intent intent = getIntent();
        if (!intent.getStringExtra("month").equals("current")) {
            String month = intent.getStringExtra("month");

            // Set the desired year and month (0-based for months: January is 0, February is 1, and so on)
              // Replace with the desired year
            int monthValue = getMonthValue(month); // Helper method to get the month value (0-based)

            // Set the date to the first day of the desired month
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);

            calendar.set(year, monthValue, 1);

//            String date =

            long millis = calendar.getTimeInMillis();
            Date date = new Date(millis);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            currentDate = dateFormat.format(date);
            dataGive();
            // Set the date on the CalendarView
            calendarView.setDate(millis);
            Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show();
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            currentDate = dateFormat.format(calendar.getTime());
            dataGive();
            long millis = calendar.getTimeInMillis();
            calendarView.setDate(millis);

        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Month is zero-based, so we add 1 to get the correct month
                progressBar.setVisibility(View.VISIBLE);
                currentDate = String.format("%02d-%02d-%d", dayOfMonth, (month + 1), year);
                dataGive();

                // You can perform actions with the selected date here
                // For example, show events for the selected date, etc.
                Toast.makeText(MonthActivity.this, "Selected Date: " + currentDate, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void dataGive() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ashok Mondal");

            DatabaseReference entryTimeRef = reference.child(user.getUid()).child(currentDate).child("Entry Time");
            entryTimeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String entry_time = snapshot.getValue(String.class);
                        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
                        try {
                            Date entryDateTime = time.parse(entry_time);
                            entryTime.setText(entry_time);

                            DatabaseReference exitTimeRef = reference.child(user.getUid()).child(currentDate).child("Exit Time");
                            exitTimeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String exit_time = snapshot.getValue(String.class);
                                        try {
                                            Date exitDateTime = time.parse(exit_time);
                                            long workingMilliseconds = exitDateTime.getTime() - entryDateTime.getTime();
                                            long workingHours = workingMilliseconds / (60 * 60 * 1000);
                                            long workingMinutes = (workingMilliseconds / (60 * 1000)) % 60;
                                            String workingHoursMinutes = String.format("%02d:%02d", workingHours, workingMinutes);
                                            TextView overTime = findViewById(R.id.overDataTime);
                                            overTime.setText(workingHoursMinutes);
                                            exitTime.setText(exit_time);
                                            progressBar.setVisibility(View.GONE);
                                        } catch (ParseException e) {
                                            exitTime.setText("Invalid exit time format");
                                        }
                                    } else {
                                        exitTime.setText("No Data");
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    exitTime.setText("No Data");
                                    progressBar.setVisibility(View.GONE);

                                }
                            });
                        } catch (ParseException e) {
                            entryTime.setText("Invalid entry time format");
                            progressBar.setVisibility(View.GONE);

                        }
                    } else {
                        entryTime.setText("No Data");
                        exitTime.setText("No Data");
                        TextView overTime = findViewById(R.id.overDataTime);
                        overTime.setText("00:00");
                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    entryTime.setText("No Data");
                }
            });
        } else {
            entryTime.setText("No Data");
            exitTime.setText("No Data");
        }
    }


    private int getMonthValue(String month) {
        switch (month) {
            case "JANUARY":
                return Calendar.JANUARY;
            case "FEBRUARY":
                return Calendar.FEBRUARY;
            // Add cases for other months as needed
            case "MARCH":
                return Calendar.MARCH;
            case "APRIL":
                return Calendar.APRIL;
            case "MAY":
                return Calendar.MAY;
            case "JUNE":
                return Calendar.JUNE;
            case "JULY":
                return Calendar.JULY;
            case "AUGUST":
                return Calendar.AUGUST;
            case "SEPTEMBER":
                return Calendar.SEPTEMBER;
            case "OCTOBER":
                return Calendar.OCTOBER;
            case "NOVEMBER":
                return Calendar.NOVEMBER;
            case "DECEMBER":
                return Calendar.DECEMBER;
            default:
                return Calendar.JANUARY; // Default to January
        }
    }


}