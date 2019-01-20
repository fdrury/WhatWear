package com.example.administrator.whatwear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button whatToWearButton = (Button)findViewById(R.id.whatToWearButton);
        Button whatIWoreButton = (Button)findViewById(R.id.whatIWoreButton);

        whatToWearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WhatToWearActivity.class);
                startActivity(intent);
            }
        });

        whatIWoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WhatIWoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
