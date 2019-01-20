package com.example.administrator.whatwear;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class WhatIWoreActivity extends AppCompatActivity {

    float temperature = -1; // temperature is returned in Kelvin so -1 is a good error value
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "&appid=f28f345df9148259a4960bfbd2904024";

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

        EditText editText = (EditText)findViewById(R.id.editText);
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
                String urlString = BASE_URL + s.toString().replaceAll("\\s","") + API_KEY;
                try{
                    JSONObject jsonObject = getJSONObjectFromURL(urlString);
                    temperature = BigDecimal.valueOf(jsonObject.getDouble("temp")).floatValue();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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


    /*private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = (new WeatherHttpClient()).getWeatherData(params[0]);

            try {
                weather = JSONWeatherParser.getWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            Button locationButton = (Button)findViewById(R.id.locationButton);

            locationButton.setText(weather.location.getCity() + "," + weather.location.getCountry());
            temperature = weather.temperature.getTemp();

        }
    }*/

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
