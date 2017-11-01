package com.hseunghyun.runacaculater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.hseunghyun.runacaculater.retrofit.RunaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText mInputSolaYearEditText;
    private EditText mInputSolaMonthEditText;
    private EditText mInputSolaDayEditText;
    private RunaApi mRunaApiservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputSolaYearEditText = findViewById(R.id.input_sola_year);
        mInputSolaMonthEditText = findViewById(R.id.input_sola_month);
        mInputSolaDayEditText = findViewById(R.id.input_sola_day);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RunaApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        mRunaApiservice = retrofit.create(RunaApi.class);
    }

}
