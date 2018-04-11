package com.renj.activityresult.rxjava;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;

import io.reactivex.subjects.Subject;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   18:05
 * <p>
 * 描述：继承至 {@link Fragment} 类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RxActivityResultFragment extends Fragment implements IProxyFragment {
    private static final String TAG = RxActivityResultFragment.class.getName();
    private Subject<RActivityResponse> subject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (subject == null) {
            Log.i(TAG, "没有设置subject !!!");
            return;
        }
        if (data != null)
            subject.onNext(new RActivityResponse(requestCode, resultCode, data));
        subject.onComplete();
    }

    @Override
    public void setRActivityResponseSubject(@NonNull Subject<RActivityResponse> subject) {
        this.subject = subject;
    }

    @Override
    public void startActivityForResult(RActivityRequest rActivityRequest) {
        startActivityForResult(rActivityRequest.requestIntent, rActivityRequest.requestCode);
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent) {
        // 使用 intent.hashCode() 方式报错：java.lang.IllegalArgumentException: Can only use lower 16 bits for requestCode
        // startActivityForResult(intent, intent.hashCode());
        Random random = new Random();
        int requestCode = random.nextInt(12500) + random.nextInt(25000);
        startActivityForResult(intent, requestCode);
    }
}
