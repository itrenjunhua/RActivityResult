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
public class ActivityResponse {
    public int requestCode;
    public Intent responseIntent;

    public ActivityResponse(int requestCode, Intent responseIntent) {
        this.requestCode = requestCode;
        this.responseIntent = responseIntent;
    }
}
