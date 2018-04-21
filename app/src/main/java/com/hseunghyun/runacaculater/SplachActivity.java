package com.hseunghyun.runacaculater;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        TextView splashTextView = findViewById(R.id.splash_text_view);
        TextView splashTextView2 = findViewById(R.id.splash_text_view2);

        //인터넷 접속 여부 판단
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // 인터넷 연결이 되어 있으면
        if (mobile.isConnected() || wifi.isConnected()) {
            // 스플래쉬 인텐트로 넘겨서 다음화면으로 가게 하기.
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();

        } else {
            // 인터넷 연결이 안되어 있으면
            Toast.makeText(this, "네트워크 상태가 좋지 않습니다.", Toast.LENGTH_SHORT).show();
            splashTextView.setText("서버 점검중 / 네트워크 오류");
            splashTextView2.setText("계속 같은 문제가 생긴다면 문의해주세요.");
            Toast.makeText(this, "네트워크 상태가 좋지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
