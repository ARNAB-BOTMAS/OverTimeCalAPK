package com.example.paymentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = findViewById(R.id.current_date);
        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "current");
                authUserData(intent);
            }
        });

        jan = findViewById(R.id.jan_button);
        feb = findViewById(R.id.feb_button);
        mar = findViewById(R.id.mar_button);
        apr = findViewById(R.id.apr_button);
        may = findViewById(R.id.may_button);
        jun = findViewById(R.id.jun_button);
        jul = findViewById(R.id.jul_button);
        aug = findViewById(R.id.aug_button);
        sep = findViewById(R.id.sep_button);
        oct = findViewById(R.id.oct_button);
        nov = findViewById(R.id.nov_button);
        dec = findViewById(R.id.dec_button);

        jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "JANUARY");

                authUserData(intent);

            }
        });

        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "FEBRUARY");
                authUserData(intent);

            }
        });

        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "MARCH");
                authUserData(intent);

            }
        });

        apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "APRIL");
                authUserData(intent);
            }
        });

        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "MAY");
                authUserData(intent);
            }
        });

        jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "JUNE");
                authUserData(intent);
            }
        });

        jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "JULY");
                authUserData(intent);
            }
        });

        aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "AUGUST");
                authUserData(intent);
            }
        });

        sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "SEPTEMBER");
                authUserData(intent);
            }
        });

        oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "OCTOBER");
                authUserData(intent);
            }
        });

        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "NOVEMBER");
                authUserData(intent);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthActivity.class);
                intent.putExtra("month", "DECEMBER");
                authUserData(intent);
            }
        });
    }

    private void authUserData(Intent intent) {
        startActivity(intent);
    }
}