package jianqiang.com.hook2;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TsActivity extends AppCompatActivity {
    private static final String TAG = "sanbo.TsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            TextView tv = new TextView(this);
            tv.setText("没有声明的页面");
            setContentView(tv);
            Bundle b = getIntent().getBundleExtra("JUMP");
            String ac = b.getString("NA");
            logi("ac:" + ac);
            SystemClock.sleep(2000);
            Intent intent = new Intent(Cx.mContext, Class.forName(ac));
            Cx.mContext.startActivity(intent);
        } catch (Exception e) {
            logd(Log.getStackTraceString(e));
        }
    }

    private void logi(String info) {
        Log.i(TAG, info);
    }

    private void logd(String info) {
        Log.d(TAG, info);
    }
}