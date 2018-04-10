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
    private static final String TAG = "RActivityResult_Commonly";

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

        //------------------------ app包 ---------------------//
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

        //------------------------ v4包 ---------------------//
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

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数只传递 {@link Intent} 对象，在监听的回调中不需要对 requestCode 进行判断
         *
         * @param intent                  {@link Intent} 对象
         * @param rActivityResultListener 回调监听 {@link RActivityResultListener} 对象
         */
        public void startActivityForResult(@NonNull Intent intent, @NonNull RActivityResultListener rActivityResultListener) {
            iProxyFragment.setRActivityResultListener(rActivityResultListener);
            iProxyFragment.startActivityForResult(intent);
        }

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数传递{@link RActivityRequest}对象，在监听的回调中根据需要对 requestCode 进行判断，一般情况下都不需要
         *
         * @param rActivityRequest        {@link RActivityRequest} 对象
         * @param rActivityResultListener 回调监听 {@link RActivityResultListener} 对象
         */
        public void startActivityForResult(@NonNull RActivityRequest rActivityRequest, @NonNull RActivityResultListener rActivityResultListener) {
            iProxyFragment.setRActivityResultListener(rActivityResultListener);
            iProxyFragment.startActivityForResult(rActivityRequest);
        }
    }

    /**
     * 返回结果之后的回调监听
     */
    public abstract static class RActivityResultListener {
        /**
         * {@link Intent} 不为 {@code null} 的时候才会回调的方法
         *
         * @param rActivityResponse {@link RActivityResponse} 对象，回调了这个方法 {@link RActivityResponse#responseIntent} 不会为 {@code null} 了
         */
        public abstract void onResult(@NonNull RActivityResponse rActivityResponse);

        /**
         * 一定会回调的方法，表示完成。这个方法主要是在不需要传递数据，只需要新打开的界面关闭了然后做其他操作时重写即可
         *
         * @param intentIsEmpty {@link RActivityResponse#responseIntent} 是否为 {@code null}，true：为{@code null}；false：非 {@code null}
         */
        public void onComplete(boolean intentIsEmpty) {

        }
    }
}
