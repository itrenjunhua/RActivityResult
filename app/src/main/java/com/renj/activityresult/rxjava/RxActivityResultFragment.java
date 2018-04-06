package com.renj.activityresult.rxjava;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.subjects.Subject;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   18:05
 * <p>
 * 描述：
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
}
