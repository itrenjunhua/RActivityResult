package com.renj.activityresult.rxjava;

import android.content.Intent;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-05   17:53
 * <p>
 * 描述：返回结果数据封装类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RActivityResponse {
    /**
     * 打开时传递的请求码，用于区分不同的请求。<br/>
     * 当调用的是 {@link RActivityResult.Builder#startActivityForResult(Intent)} 方法打开新界面时不需要做判断
     */
    public int requestCode;
    /**
     * 结果码，新打开界面调用 {@link android.app.Activity#setResult(int)}/{@link android.app.Activity#setResult(int, Intent)} 方法设置返回结果时传递的 int 类型的值
     */
    public int resultCode;
    /**
     * 结果 {@link Intent} 对象，新打开界面调用 {@link android.app.Activity#setResult(int, Intent)} 方法设置返回结果时传递的 {@link Intent} 对象
     */
    public Intent responseIntent;

    public RActivityResponse(int requestCode, int resultCode, Intent responseIntent) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.responseIntent = responseIntent;
    }
}
