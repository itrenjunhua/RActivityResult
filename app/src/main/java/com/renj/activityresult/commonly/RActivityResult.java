package com.renj.activityresult.commonly;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;


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
        private IProxyFragment iProxyFragment;

        private Builder(@NonNull Activity activity) {
            iProxyFragment = getRActivityResultFragment(activity);
        }

        private Builder(@NonNull FragmentActivity fragmentActivity) {
            iProxyFragment = getRActivityResultV4Fragment(fragmentActivity);
        }

        private RActivityResultFragment getRActivityResultFragment(@NonNull Activity activity) {
            RActivityResultFragment rActivityResultFragment = findRActivityResultFragment(activity);
            boolean isNewInstance = rActivityResultFragment == null;
            if (isNewInstance) {
                rActivityResultFragment = new RActivityResultFragment();
                FragmentManager fragmentManager = activity.getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(rActivityResultFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return rActivityResultFragment;
        }

        private RActivityResultFragment findRActivityResultFragment(Activity activity) {
            return (RActivityResultFragment) activity.getFragmentManager().findFragmentByTag(TAG);
        }

        private RActivityResultV4Fragment getRActivityResultV4Fragment(@NonNull FragmentActivity fragmentActivity) {
            RActivityResultV4Fragment rActivityResultV4Fragment = findRActivityResultV4Fragment(fragmentActivity);
            boolean isNewInstance = rActivityResultV4Fragment == null;
            if (isNewInstance) {
                rActivityResultV4Fragment = new RActivityResultV4Fragment();
                android.support.v4.app.FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
                supportFragmentManager
                        .beginTransaction()
                        .add(rActivityResultV4Fragment, TAG)
                        .commitAllowingStateLoss();
                supportFragmentManager.executePendingTransactions();
            }
            return rActivityResultV4Fragment;
        }

        private RActivityResultV4Fragment findRActivityResultV4Fragment(FragmentActivity fragmentActivity) {
            return (RActivityResultV4Fragment) fragmentActivity.getSupportFragmentManager().findFragmentByTag(TAG);
        }

        public void startActivityForResult(@NonNull Intent intent, @NonNull RActivityResultListener rActivityResultListener) {
            iProxyFragment.setRActivityResultListener(rActivityResultListener);
            iProxyFragment.startActivityForResult(intent);
        }

        public void startActivityForResult(@NonNull RActivityRequest rActivityRequest, @NonNull RActivityResultListener rActivityResultListener) {
            iProxyFragment.setRActivityResultListener(rActivityResultListener);
            iProxyFragment.startActivityForResult(rActivityRequest);
        }
    }

    public abstract static class RActivityResultListener {
        public abstract void onResult(@NonNull RActivityResponse rActivityResponse);

        public void onComplete() {

        }
    }
}
