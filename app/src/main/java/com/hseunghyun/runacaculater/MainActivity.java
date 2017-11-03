package com.hseunghyun.runacaculater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.hseunghyun.runacaculater.model.Runa;
import com.hseunghyun.runacaculater.retrofit.RunaApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * EditText 를 이용한 날짜 입력.
     */
    private EditText mInputSolaYearEditText;
    private EditText mInputSolaMonthEditText;
    private EditText mInputSolaDayEditText;

    private RunaApi mRunaApiservice;

    //    private RunUtil mRunaUtil;
    private TextView outputRunResult;
    private TextView outputRunaResult;

    /**
     * datepicker 를 이용한 날짜 선택
     */
    private DatePicker mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * EditText 를 이용한 날짜 입력.
         */
        mInputSolaYearEditText = findViewById(R.id.input_sola_year);
        mInputSolaMonthEditText = findViewById(R.id.input_sola_month);
        mInputSolaDayEditText = findViewById(R.id.input_sola_day);
        /**
         * datepicker 를 이용한 날짜 선택
         */
        mDatePicker = findViewById(R.id.date_picker);


        outputRunResult = findViewById(R.id.output_run_result);
        outputRunaResult = findViewById(R.id.output_runa_result);

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

        /**
         * EditText 를 이용한 날짜 입력.
         */
        // 년 월 일 따로 담는다.
        String year = mInputSolaYearEditText.getText().toString();
        String month = null;
        if (mInputSolaMonthEditText.getText().toString().length() < 2) {
            month = "0" + mInputSolaMonthEditText.getText().toString();
        } else {
            month = mInputSolaMonthEditText.getText().toString();
        }
//        Toast.makeText(this, "달"+month, Toast.LENGTH_SHORT).show();
        String day = null;
        if (mInputSolaDayEditText.getText().toString().length() < 2) {
            day = "0" + mInputSolaDayEditText.getText().toString();
        } else {
            day = mInputSolaDayEditText.getText().toString();
        }

        /**
         * datepicker 를 이용한 날짜 선택
         */
        String year = String.valueOf(mDatePicker.getYear());

        // 달은 0~11이기 때문에 +1 해주고 월 일 0도 붙여 줘야 한다.
        String month = null;
        if (mDatePicker.getMonth() + 1 < 10) {
            month = "0" + String.valueOf(mDatePicker.getMonth() + 1);
        } else {
            month = String.valueOf(mDatePicker.getMonth());
        }

        String day = null;
        if (mDatePicker.getDayOfMonth() + 1 < 10) {
            day = "0" + String.valueOf(mDatePicker.getDayOfMonth() + 1);
        } else {
            day = String.valueOf(mDatePicker.getDayOfMonth());
        }

        mRunaApiservice.caculaterDay(year, month, day).enqueue(new Callback<Runa>() {
            @Override // 성공
            public void onResponse(Call<Runa> call, Response<Runa> response) {
                // respone 에 모든게 들어온다.
                Runa runa = response.body();
                assert runa != null;
                outputRunResult.setText(runa.getResponse().getBody().getItems().getItem().getLunLeapmonth() + " " +
                        runa.getResponse().getBody().getItems().getItem().getLunYear() + "년 " +
                        runa.getResponse().getBody().getItems().getItem().getLunMonth() + "월 " +
                        runa.getResponse().getBody().getItems().getItem().getLunDay() + "일");

//                Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();

                outputRunaResult.setText(runa.getResponse().getBody().getItems().getItem().getLunSecha() + "년  "
                        + runa.getResponse().getBody().getItems().getItem().getLunWolgeon() + "월  "
                        + runa.getResponse().getBody().getItems().getItem().getLunIljin() + "일");
            }

            @Override // 실패
            public void onFailure(Call<Runa> call, Throwable t) {
                if (t.getLocalizedMessage().equals("Unable to resolve host \"apis.data.go.kr\": No address associated with hostname")) {
                    outputRunResult.setText("인터넷접속이 되어 있는지 확인해주세요");

                } else if (t.getLocalizedMessage().equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 90 path $.response.body.items")) {
                    outputRunResult.setText("년도 월 일을 다시 한번 확인해 주세요");
                } else {
                    outputRunResult.setText(t.getLocalizedMessage());
                }
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
