package com.switfpass.pay.lib;

import android.app.Activity;

/**
 * Created by Administrator on 2017/2/27.
 */

public class WXPay {
    private static volatile WXPay instance;
    public static synchronized WXPay getInstance()
    {
        if (instance == null)
            synchronized (WXPay.class)
            {
                if (instance == null)
                    instance = new WXPay();
            }
        return instance;
    }
    public void openWeChatPay(Activity activity,String merId, String merNo, String body, String describe,String attach, String prince,String notifyUrl,String appid) {
        new GetPrepayIdTask(activity,merId,merNo,body,describe,attach,prince,notifyUrl,appid).execute();
    }
}
