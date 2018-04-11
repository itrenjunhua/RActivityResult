package com.renj.activityresulttest.commonlytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresulttest.R;
import com.renj.activityresult.commonly.RActivityResponse;
import com.renj.activityresult.commonly.RActivityResult;

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
public class ListenerSecondActivity extends AppCompatActivity {
    private Button btClose, btOpenThreadActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(ListenerSecondActivity.this, "获取结果: " + name, Toast.LENGTH_SHORT).show();

        btClose = findViewById(R.id.bt_close);
        btOpenThreadActivity = findViewById(R.id.bt_open_thread);

        // 关闭当前Activity并设置返回值
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("resultName", "从第二个页面返回");
                setResult(0, intent);
                finish();
            }
        });


        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListenerSecondActivity.this, ListenerThreadActivity.class);
                intent.putExtra("name", "从第二个页面打开第三个页面");
                RActivityResult.create(ListenerSecondActivity.this)
                        // 使用简单的方式打开，不传递requestCode
                        .startActivityForResult(intent, new RActivityResult.RActivityResultListener() {
                            @Override
                            public void onResult(@NonNull RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(ListenerSecondActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
