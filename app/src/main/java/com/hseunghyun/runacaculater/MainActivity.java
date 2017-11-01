package com.hseunghyun.runacaculater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hseunghyun.runacaculater.model.Runa;
import com.hseunghyun.runacaculater.retrofit.RunUtil;
import com.hseunghyun.runacaculater.retrofit.RunaApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText mInputSolaYearEditText;
    private EditText mInputSolaMonthEditText;
    private EditText mInputSolaDayEditText;
    private RunaApi mRunaApiservice;
    private RunUtil mRunaUtil;
    private TextView outputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputSolaYearEditText = findViewById(R.id.input_sola_year);
        mInputSolaMonthEditText = findViewById(R.id.input_sola_month);
        mInputSolaDayEditText = findViewById(R.id.input_sola_day);
        outputResult = findViewById(R.id.output_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RunaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRunaApiservice = retrofit.create(RunaApi.class);

    }

    public void onClickedEnterButton(View view) {
        converter();
//        Toast.makeText(this, "잘 눌리지?", Toast.LENGTH_SHORT).show();
    }

    // 입력된 날짜 값을 변환해주는 기능을 만들자.(==>메소드)
    // 레트로핏 부르고 . api에 뭘로 쿼리 할꺼였는지 그 이름 적어주고. 인큐(new call...)
    public void converter() {

        String year = mInputSolaYearEditText.getText().toString();
        String month = null;
        if (mInputSolaMonthEditText.getText().toString().length() == 1) {
            month = "0" + mInputSolaMonthEditText.getText().toString();
        } else {
            month = mInputSolaMonthEditText.getText().toString();
        }
        String day = null;
        if (mInputSolaMonthEditText.getText().toString().length() == 1) {
            day = "0" + mInputSolaDayEditText.getText().toString();
        } else {
            day = mInputSolaDayEditText.getText().toString();
        }

        mRunaApiservice.caculaterDay(mInputSolaYearEditText.getText().toString(),
                mInputSolaMonthEditText.getText().toString(),
                mInputSolaDayEditText.getText().toString()).enqueue(new Callback<Runa>() {
            @Override // 성공
            public void onResponse(Call<Runa> call, Response<Runa> response) {
                // respone 에 모든게 들어온다.
                Runa runa = response.body();
                assert runa != null;
                outputResult.setText(runa.getResponse().getBody().getItems().getItem().getLunLeapmonth() + " " +
                        runa.getResponse().getBody().getItems().getItem().getLunYear() + "년" +
                        runa.getResponse().getBody().getItems().getItem().getLunMonth() + "월" +
                        runa.getResponse().getBody().getItems().getItem().getLunDay() + "일");

                Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
            }

            @Override // 실패
            public void onFailure(Call<Runa> call, Throwable t) {
                outputResult.setText("년도 월 일을 다시 한번 확인해 주세요");
//                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
