package com.hseunghyun.runacaculater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        // 스플래쉬 인텐트로 넘겨서 다음화면으로 가게 하기.
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
