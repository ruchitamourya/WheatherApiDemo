package com.example.ruchi.wheatherapidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btn_search;
    private EditText edt_city;
    private TextView text_city_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search = findViewById(R.id.btn_search);
        edt_city = findViewById(R.id.edit_city);
        text_city_weather = findViewById(R.id.tv_weather);
        Log.d(TAG, "onCreate() called");
        btn_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_search:
                showWeather();
                Log.d(TAG, "showWeather() called");
        }
    }

    private void showWeather() {
        DataService dataService = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        String city = edt_city.getText().toString();
        Call<JsonObject> call = dataService.getWeather(city, "92a9cc9197d296129c735e9e4236e645");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "url->" + call.request().url().toString());
                if (response.body() != null) {
                    String string = response.body().toString();
                    text_city_weather.setText(response.body().toString());
                    Log.d(TAG, "city weather");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "throwable message->" + t.getMessage());
            }
        });
    }
}

