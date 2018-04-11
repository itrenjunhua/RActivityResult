package com.renj.activityresulttest.rxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresulttest.R;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-05   22:46
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RxThreadActivity extends AppCompatActivity {
    private Button btClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(RxThreadActivity.this, "获取结果: " + name, Toast.LENGTH_SHORT).show();

        btClose = findViewById(R.id.bt_close);

        // 关闭当前Activity并设置返回值
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resultName", "从第三个页面返回");
                setResult(0, intent);
                finish();
            }
        });
    }
}
