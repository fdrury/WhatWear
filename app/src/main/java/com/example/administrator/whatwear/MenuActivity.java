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
                intent.putExtra("EVALUATION_TYPE", "JERSEY");
                intent.putExtra("EVALUATOR_ID", getIntent().getStringExtra("EVALUATOR_ID"));
                intent.putExtra("TRYOUT_ID", getIntent().getStringExtra("TRYOUT_ID"));
                startActivity(intent);
            }
        });

        whatIWoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WhatIWoreActivity.class);
                intent.putExtra("EVALUATION_TYPE", "TIMED");
                intent.putExtra("EVALUATOR_ID", getIntent().getStringExtra("EVALUATOR_ID"));
                intent.putExtra("TRYOUT_ID", getIntent().getStringExtra("TRYOUT_ID"));
                startActivity(intent);
            }
        });
    }
}
