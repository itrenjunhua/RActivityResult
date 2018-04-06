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

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.bt_open_second);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "张三");
                RActivityResult.create(MainActivity.this)
                        .startActivityForResult(new RActivityRequest(1, intent))
                        .subscribe(new Consumer<RActivityResponse>() {
                            @Override
                            public void accept(RActivityResponse RActivityResponse) throws Exception {
                                String resultName = RActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(MainActivity.this, "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
