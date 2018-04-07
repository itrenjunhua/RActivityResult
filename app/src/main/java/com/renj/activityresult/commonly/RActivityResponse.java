package com.renj.activityresult.commonly;

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
    public int requestCode;
    public int resultCode;
    public Intent responseIntent;

    public RActivityResponse(int requestCode, int resultCode, Intent responseIntent) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.responseIntent = responseIntent;
    }
}
