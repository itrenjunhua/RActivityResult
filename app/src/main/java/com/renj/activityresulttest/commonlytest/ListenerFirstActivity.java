package com.renj.activityresulttest.commonlytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresulttest.R;
import com.renj.activityresult.commonly.RActivityRequest;
import com.renj.activityresult.commonly.RActivityResponse;
import com.renj.activityresult.commonly.RActivityResult;

public class ListenerFirstActivity extends AppCompatActivity {

    private Button btOpenSecondActivity, btOpenThreadActivity, btOpenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btOpenSecondActivity = findViewById(R.id.bt_open_second);
        btOpenThreadActivity = findViewById(R.id.bt_open_thread);
        btOpenFragment = findViewById(R.id.bt_open_fragment);

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListenerFirstActivity.this, ListenerSecondActivity.class);
                intent.putExtra("name", "从第一个页面打开第二个页面");
                RActivityResult.create(ListenerFirstActivity.this)
                        .startActivityForResult(new RActivityRequest(1, intent), new RActivityResult.RActivityResultListener() {
                            @Override
                            public void onResult(@NonNull RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(ListenerFirstActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete(boolean intentIsEmpty) {
                                // 当返回结果的 Intent 为null时，将不会调用 onResult() 方法，只会调用 onComplete() 方法；否则两个方法都会调用
                                if (intentIsEmpty)
                                    Toast.makeText(ListenerFirstActivity.this, "onComplete()方法intentIsEmpty为true：没有返回任何数据", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListenerFirstActivity.this, ListenerThreadActivity.class);
                intent.putExtra("name", "从第一个页面打开第三个页面");
                RActivityResult.create(ListenerFirstActivity.this)
                        // 使用简单的方式打开，不传递requestCode
                        .startActivityForResult(intent, new RActivityResult.RActivityResultListener() {
                            @Override
                            public void onResult(@NonNull RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(ListenerFirstActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开Fragment页面，测试Fragment
        btOpenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListenerFirstActivity.this, ListenerMyFragmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
