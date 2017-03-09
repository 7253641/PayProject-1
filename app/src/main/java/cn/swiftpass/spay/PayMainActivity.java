package cn.swiftpass.spay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.switfpass.pay.lib.WXPay;

public class PayMainActivity extends Activity {

    String TAG = "PayMainActivity";

    private EditText money, etbody, mchId, notifyUrl, orderNo, signKey, appId, seller_id, credit_pay;

    private Spinner spinnerPayType;

    private ArrayAdapter adapterPayType;// 新增一个适配器

    private int postion = 0;

    private boolean isWxBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_main);

        Button button = (Button) findViewById(R.id.submitPay);

        money = (EditText) findViewById(R.id.money);

        etbody = (EditText) findViewById(R.id.body);

        mchId = (EditText) findViewById(R.id.mchId);

        notifyUrl = (EditText) findViewById(R.id.notifyUrl);

        orderNo = (EditText) findViewById(R.id.orderNo);

        signKey = (EditText) findViewById(R.id.signKey);

        appId = (EditText) findViewById(R.id.appId);

        spinnerPayType = (Spinner) findViewById(R.id.spinnerPayType);

        seller_id = (EditText) findViewById(R.id.seller_id);

        credit_pay = (EditText) findViewById(R.id.credit_pay);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                WXPay.getInstance()
                        .openWeChatPay(PayMainActivity.this,
                                "test",
                                "1234567890",
                                "测试",
                                "测试商品",
                                "attach",
                                "1",
                                "www.xxxx.com",
                                "wx147f13ff660ef586");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // Toast.makeText(getApplicationContext(), "pay_result-->" +
        // data.getStringExtra("pay_result"), 0).show();
    }

}
