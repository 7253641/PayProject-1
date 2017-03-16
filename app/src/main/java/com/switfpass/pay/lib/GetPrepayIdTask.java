package com.switfpass.pay.lib;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.switfpass.pay.MainApplication;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/20.
 */

public class GetPrepayIdTask extends AsyncTask<Void, Void, String> {
    private String TAG = "GetPrepayIdTask:";

    private ProgressDialog dialog;

    private Activity activity;
    private String url = "http://139.129.241.116:8080/rmpay/placeOrder";
    private Map<String, String> param;
    private String data, resultCode, tokenId;
    private int state = 400;
    private String mAppid;

    public GetPrepayIdTask(Activity activity, String merId, String merNo, String body, String describe, String attach, String prince, String notifyUrl, String appid) {
        this.activity = activity;
        this.mAppid = appid;
        param = new HashMap<String, String>();
        param.put("merId", merId);
        param.put("merNo", merNo);
        param.put("body", body);
        param.put("describe", describe);
        param.put("attach", attach);
        param.put("prince", prince);
        param.put("notifyUrl", notifyUrl);
        param.put("appId", appid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (!httpConnection()) {
            httpConnection();
        }
        return resultCode;
    }

    private boolean httpConnection() {
        String buf = HttpTool.getInstance().post(url, param);
        if (buf == null || buf.equals("")) {
            Log.e(TAG, "errorMSG:response = null");
            return false;
        }
        try {
            JSONObject jsonContent = new JSONObject(buf);
            state = jsonContent.getInt("state");
            if (state == 400) {
                Log.d(TAG, "state=" + state);
                return false;
            }
            data = jsonContent.getString("data");
            JSONObject jsonObject = new JSONObject(data);
            resultCode = jsonObject.getString("resultCode");
            tokenId = jsonObject.getString("tokenId");
            Log.d(TAG, "returnMSG:" + data);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        if (resultCode == null || "".equals(resultCode)) {
            return false;
        } else if (tokenId == null || "".equals(tokenId)) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在加载", false, false);
    }

    @Override
    protected void onPostExecute(String result) {
        if (dialog != null) {
            dialog.dismiss();
        }
        if (result == null) {
            Log.d(TAG, "errorMSG:result = null");
            Toast.makeText(activity, "加载失败", Toast.LENGTH_LONG).show();
        } else {
            if ("0".equalsIgnoreCase(result)) // 成功
            {
                RequestMsg msg = new RequestMsg();
                msg.setTokenId(tokenId);
                msg.setTradeType(MainApplication.WX_APP_TYPE);
                msg.setAppId(mAppid);
                PayPlugin.unifiedAppPay(activity, msg);
            } else {
                Log.d(TAG, "errorMSG:result fail");
                Toast.makeText(activity, "加载失败", Toast.LENGTH_LONG).show();
            }
        }
    }
}