package com.renj.activityresult.commonlytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.renj.activityresult.R;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   20:58
 * <p>
 * 描述：作为Fragment的测试类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ListenerMyFragmentActivity extends FragmentActivity {
    private Button btV4Fragment, btAppFragment;
    private ListenerMyV4Fragment v4Fragment;
    private ListenerMyAppFragment appFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btV4Fragment = findViewById(R.id.bt_v4_fragment);
        btAppFragment = findViewById(R.id.bt_app_fragment);

        v4Fragment = new ListenerMyV4Fragment();
        appFragment = new ListenerMyAppFragment();

        // 默认显示v4包下的
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, v4Fragment);
        fragmentTransaction.commit();

        // 显示v4包下的Fragment
        btV4Fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_content, new ListenerMyV4Fragment());
                fragmentTransaction.commit();
            }
        });

        // 显示app包下的Fragment
        btAppFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.fl_content, appFragment);
                fragmentTransaction1.commit();
            }
        });
    }
}
