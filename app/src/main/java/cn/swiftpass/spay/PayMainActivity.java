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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_main);

        Button button = (Button) findViewById(R.id.submitPay);

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
    }

}
