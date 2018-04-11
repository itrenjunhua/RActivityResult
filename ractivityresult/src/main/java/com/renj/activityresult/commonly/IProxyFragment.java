package com.renj.activityresult.commonly;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-06   19:42
 * <p>
 * 描述：代理Fragment接口
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IProxyFragment {
    /**
     * 设置监听
     *
     * @param rActivityResultListener
     */
    void setRActivityResultListener(@NonNull RActivityResult.RActivityResultListener rActivityResultListener);

    /**
     * 调用 startActivityForResult() 方法，传递 {@link RActivityRequest} 封装对象，包含 {@link Intent} 对象 和 requestCode
     *
     * @param rActivityRequest {@link RActivityRequest} 对象
     */
    void startActivityForResult(@NonNull RActivityRequest rActivityRequest);

    /**
     * 调用 startActivityForResult() 方法，传递 {@link Intent} 对象
     *
     * @param intent {@link Intent} 对象
     */
    void startActivityForResult(@NonNull Intent intent);
}
