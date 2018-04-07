package com.renj.activityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.renj.activityresult.commonlytest.ListenerFirstActivity;
import com.renj.activityresult.rxtest.RxFirstActivity;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-08   0:55
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MainActivity extends AppCompatActivity {
    private Button btRxMethod, btListenerMethod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRxMethod = findViewById(R.id.bt_rx_method);
        btListenerMethod = findViewById(R.id.bt_listener_method);

        // 进入使用Rx的方式测试页面
        btRxMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxFirstActivity.class);
                startActivity(intent);
            }
        });

        // 进入使用监听的方式测试页面
        btListenerMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListenerFirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
