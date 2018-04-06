package com.renj.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresult.rxjava.RActivityRequest;
import com.renj.activityresult.rxjava.RActivityResponse;
import com.renj.activityresult.rxjava.RActivityResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private Button btOpenSecondActivity, btOpenThreadActivity, btOpenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOpenSecondActivity = findViewById(R.id.bt_open_second);
        btOpenThreadActivity = findViewById(R.id.bt_open_thread);
        btOpenFragment = findViewById(R.id.bt_open_fragment);

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "从第一个页面打开第二个页面");
                RActivityResult.create(MainActivity.this)
                        .startActivityForResult(new RActivityRequest(1, intent))
                        .subscribe(new Observer<RActivityResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(MainActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(MainActivity.this, "没有返回任何数据", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                intent.putExtra("name", "从第一个页面打开第三个页面");
                RActivityResult.create(MainActivity.this)
                        .startActivityForResult(new RActivityRequest(1, intent))
                        .subscribe(new Consumer<RActivityResponse>() {
                            @Override
                            public void accept(RActivityResponse rActivityResponse) throws Exception {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(MainActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开Fragment页面，测试Fragment
        btOpenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyFragmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
