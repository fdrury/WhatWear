package com.example.administrator.whatwear;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class WhatIWoreActivity extends AppCompatActivity {

    private float temperature = -1; // temperature is returned in Kelvin so -1 is a good error value
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "&appid=f28f345df9148259a4960bfbd2904024";
    private String city = "Edmonton,CA";
    private Button saveButton;
    private Button cancelButton;
    private Button bodyTempButton;
    private Button legsTempButton;
    private ImageButton bodyImageButton;
    private ImageButton legsImageButton;
    private EditText editText;
    private int bodyTempIndex = 2;
    private int legsTempIndex = 2;
    private int bodyWearIndex = 0;
    private int legsWearIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_iwore);

        String urlString = BASE_URL + "Edmonton,CA" + API_KEY;
        try{
            JSONObject jsonObject = getJSONObjectFromURL(urlString);
            temperature = BigDecimal.valueOf(jsonObject.getDouble("temp")).floatValue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        saveButton = (Button)findViewById(R.id.buttonSave);
        cancelButton = (Button)findViewById(R.id.buttonCancel);
        bodyTempButton = (Button)findViewById(R.id.buttonBody);
        legsTempButton = (Button)findViewById(R.id.buttonLegs);
        bodyImageButton = (ImageButton)findViewById(R.id.imageButtonBody);
        legsImageButton = (ImageButton)findViewById(R.id.imageButtonLegs);
        EditText editText = (EditText)findViewById(R.id.editText);

        bodyTempButton.setBackgroundColor(Color.GREEN);
        legsTempButton.setBackgroundColor(Color.GREEN);

        final Resources res = getResources();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = BASE_URL + city + API_KEY;
                try{
                    JSONObject jsonObject = getJSONObjectFromURL(urlString);
                    temperature = BigDecimal.valueOf(jsonObject.getDouble("temp")).floatValue();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // TODO: actually save data
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bodyTempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyTempIndex++;
                if(bodyTempIndex > 4) {
                    bodyTempIndex = 0;
                }
                switch(bodyTempIndex) {
                    case 0:
                        bodyTempButton.setBackgroundColor(Color.MAGENTA);
                        bodyTempButton.setText("Cold");
                        break;
                    case 1:
                        bodyTempButton.setBackgroundColor(Color.BLUE);
                        bodyTempButton.setText("Cool");
                        break;
                    case 2:
                        bodyTempButton.setBackgroundColor(Color.GREEN);
                        bodyTempButton.setText("Perfect");
                        break;
                    case 3:
                        bodyTempButton.setBackgroundColor(Color.YELLOW);
                        bodyTempButton.setText("Warm");
                        break;
                    case 4:
                        bodyTempButton.setBackgroundColor(Color.RED);
                        bodyTempButton.setText("Hot");
                        break;
                }
            }
        });

        legsTempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                legsTempIndex++;
                if(legsTempIndex > 4) {
                    legsTempIndex = 0;
                }
                switch(legsTempIndex) {
                    case 0:
                        legsTempButton.setBackgroundColor(Color.MAGENTA);
                        legsTempButton.setText("Cold");
                        break;
                    case 1:
                        legsTempButton.setBackgroundColor(Color.BLUE);
                        legsTempButton.setText("Cool");
                        break;
                    case 2:
                        legsTempButton.setBackgroundColor(Color.GREEN);
                        legsTempButton.setText("Perfect");
                        break;
                    case 3:
                        legsTempButton.setBackgroundColor(Color.YELLOW);
                        legsTempButton.setText("Warm");
                        break;
                    case 4:
                        legsTempButton.setBackgroundColor(Color.RED);
                        legsTempButton.setText("Hot");
                        break;
                }
            }
        });

        bodyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyWearIndex++;
                if(bodyWearIndex > 4) {
                    bodyWearIndex = 0;
                }
                switch(bodyWearIndex) {
                    case 0:
                        bodyImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.jacket_thin, null));
                        break;
                    case 1:
                        bodyImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.jacket_thick, null));
                        break;
                    case 2:
                        bodyImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.sweater_thin, null));
                        break;
                    case 3:
                        bodyImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.sweater_thick, null));
                        break;
                    case 4:
                        bodyImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.shirt_long_sleeve, null));
                        break;
                }
            }
        });

        legsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                legsWearIndex++;
                if(legsWearIndex > 4) {
                    legsWearIndex = 0;
                }
                switch(legsWearIndex) {
                    case 0:
                        legsImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.pants_thin, null));
                        break;
                    case 1:
                        legsImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.pants_thick, null));
                        break;
                    case 2:
                        legsImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.shorts_short, null));
                        break;
                    case 3:
                        legsImageButton.setImageDrawable(ResourcesCompat.getDrawable(res, R.drawable.shorts_long, null));
                        break;
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                city = s.toString().replaceAll("\\s","");
            }
        });



        //JSONWeatherTask task = new JSONWeatherTask();
        //task.execute(new String[]{city});
    }

    private void updateTemperature(String city) {
        String urlString = BASE_URL + city + API_KEY;
        try{
            JSONObject jsonObject = getJSONObjectFromURL(urlString);
            temperature = BigDecimal.valueOf(jsonObject.getDouble("temp")).floatValue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
