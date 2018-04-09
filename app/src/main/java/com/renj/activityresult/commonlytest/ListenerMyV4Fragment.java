package com.renj.activityresult.commonlytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresult.R;
import com.renj.activityresult.commonly.RActivityRequest;
import com.renj.activityresult.commonly.RActivityResponse;
import com.renj.activityresult.commonly.RActivityResult;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   21:00
 * <p>
 * 描述：继承至v4支持包的Fragment
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class ListenerMyV4Fragment extends Fragment {
    private Button btOpenSecondActivity, btOpenThreadActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v4, null);
        btOpenSecondActivity = view.findViewById(R.id.bt_open_second);
        btOpenThreadActivity = view.findViewById(R.id.bt_open_thread);

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListenerSecondActivity.class);
                intent.putExtra("name", "从v4包下Fragment页面打开第二个页面");
                RActivityResult.create(getActivity())
                        .startActivityForResult(new RActivityRequest(1, intent), new RActivityResult.RActivityResultListener() {
                            @Override
                            public void onResult(@NonNull RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(getActivity(), "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListenerThreadActivity.class);
                intent.putExtra("name", "从v4包下Fragment页面打开第三个页面");
                RActivityResult.create(getActivity())
                        // 使用简单的方式打开，不传递requestCode
                        .startActivityForResult( intent, new RActivityResult.RActivityResultListener() {
                            @Override
                            public void onResult(@NonNull RActivityResponse rActivityResponse) {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(getActivity(), "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }
}
