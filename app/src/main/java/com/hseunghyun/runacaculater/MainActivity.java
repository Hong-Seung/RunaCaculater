package com.hseunghyun.runacaculater;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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

//    /**
//     * EditText 를 이용한 날짜 입력.
//     */
//    private EditText mInputSolaYearEditText;
//    private EditText mInputSolaMonthEditText;
//    private EditText mInputSolaDayEditText;

    private RunaApi mRunaApiservice;

    //    private RunUtil mRunaUtil;
    private TextView outputRunResult;
    private TextView outputRunaResult;

//    /**
//     * datepicker 를 이용한 날짜 선택
//     */
//    private DatePicker mDatePicker;

    /**
     * datepicker 다이얼로그
     */
    private TextView mDatepickerTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /**
//         * EditText 를 이용한 날짜 입력.
//         */
//        mInputSolaYearEditText = findViewById(R.id.input_sola_year);
//        mInputSolaMonthEditText = findViewById(R.id.input_sola_month);
//        mInputSolaDayEditText = findViewById(R.id.input_sola_day);
//        /**
//         * datepicker 를 이용한 날짜 선택
//         */
//        mDatePicker = findViewById(R.id.date_picker);
        /**
         * datepicker 다이얼로그
         */
        mDatepickerTextview = findViewById(R.id.datepicker_textview);


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

//        /**
//         * EditText 를 이용한 날짜 입력.
//         */
//        // 년 월 일 따로 담는다.
//        String year = mInputSolaYearEditText.getText().toString();
//        String month = null;
//        if (mInputSolaMonthEditText.getText().toString().length() < 2) {
//            month = "0" + mInputSolaMonthEditText.getText().toString();
//        } else {
//            month = mInputSolaMonthEditText.getText().toString();
//        }
////        Toast.makeText(this, "달"+month, Toast.LENGTH_SHORT).show();
//        String day = null;
//        if (mInputSolaDayEditText.getText().toString().length() < 2) {
//            day = "0" + mInputSolaDayEditText.getText().toString();
//        } else {
//            day = mInputSolaDayEditText.getText().toString();
//        }
//
//        /**
//         * datepicker 를 이용한 날짜 선택
//         */
//        String year = String.valueOf(mDatePicker.getYear());
//
//        // 달은 0~11이기 때문에 +1 해주고 월 일 0도 붙여 줘야 한다.
//        String month = null;
//        if (mDatePicker.getMonth() + 1 < 10) {
//            month = "0" + String.valueOf(mDatePicker.getMonth() + 1);
//        } else {
//            month = String.valueOf(mDatePicker.getMonth());
//        }
//
//        String day = null;
//        if (mDatePicker.getDayOfMonth() < 10) {
//            day = "0" + String.valueOf(mDatePicker.getDayOfMonth());
//        } else {
//            day = String.valueOf(mDatePicker.getDayOfMonth());
//        }

        String year = String.valueOf(mYear);
        String month = null;
        if (mMonth + 1 < 10) {
            month = "0" + String.valueOf(mMonth + 1);
        } else {
            month = String.valueOf(mMonth + 1);
        }
        String day = null;
        if (mDay < 10) {
            day = "0" + String.valueOf(mDay);
        } else {
            day = String.valueOf(mDay);
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
                // 널포인트될수 있어서 지움.
//                if (t.getLocalizedMessage().equals("Unable to resolve host \"apis.data.go.kr\": No address associated with hostname")) {
//                    outputRunResult.setText("인터넷접속이 되어 있는지 확인해주세요");
//
//                } else if (t.getLocalizedMessage().equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 90 path $.response.body.items")) {
//                    outputRunResult.setText("년도 월 일을 다시 한번 확인해 주세요");
//                } else {
//                    outputRunResult.setText(t.getLocalizedMessage());
//                }
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * datepicker 다이얼로그 (5.0 이상은 달력으로 나온다.(선택을 할 수 가 없다..ㅠㅠ)
     */
    private int mYear;
    private int mMonth;
    private int mDay;

    public void onClickedDateSelectButton(View view) {
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        DatePicker datePicker = (DatePicker)inflater.inflate(R.layout.datepicker,null);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, mDateSetListener, mYear, mMonth, mDay);
        // 21 부턴 못씀.
//        datePickerDialog.getDatePicker().setCalendarViewShown(false);
//        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();

    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //사용자가 입력한 값을 가져온뒤
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;

            //텍스트뷰의 값을 업데이트함
            UpdateNow();
        }
    };


    //텍스트뷰의 값을 업데이트 하는 메소드

    void UpdateNow() {
        mDatepickerTextview.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
    }

}
