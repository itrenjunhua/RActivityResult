package com.renj.activityresult.rxjava;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-05   18:26
 * <p>
 * 描述：对外提供的操作类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RActivityResult {
    private static final String TAG = "RActivityResult_Rx";

    private RActivityResult() {
    }

    public static Builder create(@NonNull Activity activity) {
        return new Builder(activity);
    }

    public static class Builder {
        private PublishSubject<RActivityResponse> subject;
        private RxActivityResultFragment rxActivityResultFragment;

        private Builder(@NonNull Activity activity) {
            rxActivityResultFragment = getRxActivityResultFragment(activity);
            subject = PublishSubject.<RActivityResponse>create();
            rxActivityResultFragment.setRActivityResponseSubject(subject);
        }

        private RxActivityResultFragment getRxActivityResultFragment(@NonNull Activity activity) {
            RxActivityResultFragment rxActivityResultFragment = findRxActivityResultFragment(activity);
            boolean isNewInstance = rxActivityResultFragment == null;
            if (isNewInstance) {
                rxActivityResultFragment = new RxActivityResultFragment();
                FragmentManager fragmentManager = activity.getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(rxActivityResultFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return rxActivityResultFragment;
        }

        private RxActivityResultFragment findRxActivityResultFragment(Activity activity) {
            return (RxActivityResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        }

        public Observable<RActivityResponse> startActivityForResult(@NonNull final RActivityRequest rActivityRequest) {
            rxActivityResultFragment.startActivityForResult(rActivityRequest.requestIntent, rActivityRequest.requestCode);
            return subject;
        }
    }
}
