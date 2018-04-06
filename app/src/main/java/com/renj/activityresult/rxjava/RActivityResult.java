package com.renj.activityresult.rxjava;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

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

    @NonNull
    public static Builder create(@NonNull Activity activity) {
        if (activity instanceof FragmentActivity) {
            return new Builder((FragmentActivity) activity);
        } else if (activity instanceof Activity) {
            return new Builder(activity);
        } else {
            throw new IllegalArgumentException("Activity 参数异常!!!");
        }
    }

    public static class Builder {
        private PublishSubject<RActivityResponse> subject;
        private IProxyFragment iProxyFragment;

        private Builder(@NonNull Activity activity) {
            iProxyFragment = getRxActivityResultFragment(activity);
            subject = PublishSubject.<RActivityResponse>create();
            iProxyFragment.setRActivityResponseSubject(subject);
        }

        private Builder(@NonNull FragmentActivity fragmentActivity) {
            iProxyFragment = getRxActivityResultV4Fragment(fragmentActivity);
            subject = PublishSubject.<RActivityResponse>create();
            iProxyFragment.setRActivityResponseSubject(subject);
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

        private RxActivityResultV4Fragment getRxActivityResultV4Fragment(@NonNull FragmentActivity fragmentActivity) {
            RxActivityResultV4Fragment rxActivityResultV4Fragment = findRxActivityResultV4Fragment(fragmentActivity);
            boolean isNewInstance = rxActivityResultV4Fragment == null;
            if (isNewInstance) {
                rxActivityResultV4Fragment = new RxActivityResultV4Fragment();
                android.support.v4.app.FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
                supportFragmentManager
                        .beginTransaction()
                        .add(rxActivityResultV4Fragment, TAG)
                        .commitAllowingStateLoss();
                supportFragmentManager.executePendingTransactions();
            }
            return rxActivityResultV4Fragment;
        }

        private RxActivityResultV4Fragment findRxActivityResultV4Fragment(FragmentActivity fragmentActivity) {
            return (RxActivityResultV4Fragment) fragmentActivity.getSupportFragmentManager().findFragmentByTag(TAG);
        }

        public Observable<RActivityResponse> startActivityForResult(@NonNull RActivityRequest rActivityRequest) {
            iProxyFragment.startActivityForResult(rActivityRequest);
            return subject;
        }
    }
}
