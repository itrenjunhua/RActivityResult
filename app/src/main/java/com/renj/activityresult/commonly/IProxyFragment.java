package com.renj.activityresult.commonly;

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
     * 调用 startActivityForResult() 方法
     *
     * @param rActivityRequest
     */
    void startActivityForResult(RActivityRequest rActivityRequest);
}
