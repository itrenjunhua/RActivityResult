package com.renj.activityresult.commonly;

import android.content.Intent;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2018-04-05   17:52
 * <p>
 * 描述：请求数据封装类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RActivityRequest {
    /**
     * 请求码
     */
    public int requestCode;
    /**
     * 请求的 {@link Intent} 对象
     */
    public Intent requestIntent;

    public RActivityRequest(int requestCode, Intent requestIntent) {
        this.requestCode = requestCode;
        this.requestIntent = requestIntent;
    }
}
