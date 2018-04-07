package com.renj.activityresult.commonly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

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
public class RActivityResultV4Fragment extends Fragment implements IProxyFragment {
    private static final String TAG = RActivityResultV4Fragment.class.getName();
    private RActivityResult.RActivityResultListener rActivityResultListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (rActivityResultListener == null) {
            Log.i(TAG, "没有设置 RActivityResult.RActivityResultListener !!!");
            return;
        }

        if (data != null)
            rActivityResultListener.onResult(new RActivityResponse(requestCode, resultCode, data));
        rActivityResultListener.onComplete();
    }

    @Override
    public void setRActivityResultListener(@NonNull RActivityResult.RActivityResultListener rActivityResultListener) {
        this.rActivityResultListener = rActivityResultListener;
    }

    @Override
    public void startActivityForResult(RActivityRequest rActivityRequest) {
        startActivityForResult(rActivityRequest.requestIntent, rActivityRequest.requestCode);
    }
}
