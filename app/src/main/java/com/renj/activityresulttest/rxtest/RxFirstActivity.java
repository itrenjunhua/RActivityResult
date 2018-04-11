package com.renj.activityresulttest.rxtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresulttest.R;
import com.renj.activityresult.rxjava.RActivityRequest;
import com.renj.activityresult.rxjava.RActivityResponse;
import com.renj.activityresult.rxjava.RActivityResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxFirstActivity extends AppCompatActivity {

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
                Intent intent = new Intent(RxFirstActivity.this, RxSecondActivity.class);
                intent.putExtra("name", "从第一个页面打开第二个页面");
                RActivityResult.create(RxFirstActivity.this)
                        .startActivityForResult(new RActivityRequest(1, intent))
                        .subscribe(new Observer<RActivityResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(RxFirstActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                // 当返回结果的 Intent 为null时，将不会调用 onNext() 方法，只会调用 onComplete() 方法；否则两个方法都会调用
                                // Toast.makeText(RxFirstActivity.this, "调用了onComplete()方法", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxFirstActivity.this, RxThreadActivity.class);
                intent.putExtra("name", "从第一个页面打开第三个页面");
                RActivityResult.create(RxFirstActivity.this)
                        // 使用简单的方式打开，不传递requestCode
                        .startActivityForResult(intent)
                        .subscribe(new Consumer<RActivityResponse>() {
                            @Override
                            public void accept(RActivityResponse rActivityResponse) throws Exception {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(RxFirstActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开Fragment页面，测试Fragment
        btOpenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxFirstActivity.this, RxMyFragmentActivity.class);
                startActivity(intent);
            }
        });
    }
}
