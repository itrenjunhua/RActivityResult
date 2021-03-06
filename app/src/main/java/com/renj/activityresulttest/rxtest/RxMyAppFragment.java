package com.renj.activityresulttest.rxtest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.renj.activityresulttest.R;
import com.renj.activityresult.rxjava.RActivityRequest;
import com.renj.activityresult.rxjava.RActivityResponse;
import com.renj.activityresult.rxjava.RActivityResult;

import io.reactivex.functions.Consumer;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   21:00
 * <p>
 * 描述：继承至app支持包的Fragment
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RxMyAppFragment extends Fragment {
    private Button btOpenSecondActivity, btOpenThreadActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, null);
        btOpenSecondActivity = view.findViewById(R.id.bt_open_second);
        btOpenThreadActivity = view.findViewById(R.id.bt_open_thread);

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RxSecondActivity.class);
                intent.putExtra("name", "从app包下Fragment页面打开第二个页面");
                RActivityResult.create(getActivity())
                        .startActivityForResult(new RActivityRequest(1, intent))
                        .subscribe(new Consumer<RActivityResponse>() {
                            @Override
                            public void accept(RActivityResponse rActivityResponse) throws Exception {
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
                Intent intent = new Intent(getActivity(), RxThreadActivity.class);
                intent.putExtra("name", "从app包下Fragment页面打开第三个页面");
                RActivityResult.create(getActivity())
                        // 使用简单的方式打开，不传递requestCode
                        .startActivityForResult(intent)
                        .subscribe(new Consumer<RActivityResponse>() {
                            @Override
                            public void accept(RActivityResponse rActivityResponse) throws Exception {
                                String resultName = rActivityResponse.responseIntent.getStringExtra("resultName");
                                Toast.makeText(getActivity(), "返回结果: " + resultName, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }
}
