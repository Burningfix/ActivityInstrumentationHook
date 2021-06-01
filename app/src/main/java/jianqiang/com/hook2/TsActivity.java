package jianqiang.com.hook2;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class TsActivity extends AppCompatActivity {
    private static final String TAG = "sanbo.TsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Window window = getWindow();
            // 放在左上角
            window.setGravity(Gravity.START | Gravity.TOP);
            WindowManager.LayoutParams params = window.getAttributes();
            //设置宽高
            params.width = 1;
            params.height = 1;
            //设置起始坐标
            params.x = 0;
            params.y = 0;
            window.setAttributes(params);

            TextView tv = new TextView(this);
            tv.setText("没有声明的页面");
            setContentView(tv);
            Bundle b = getIntent().getBundleExtra("JUMP");
            String ac = b.getString("NA");
            logi("ac:" + ac);
            Toast.makeText(getApplicationContext(), "已经打开拦截的页面", Toast.LENGTH_LONG).show();
            SystemClock.sleep(2000);
            Intent intent = new Intent(Cx.mContext, Class.forName(ac));
            Cx.mContext.startActivity(intent);
        } catch (Throwable e) {
            logd(Log.getStackTraceString(e));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        logi("TS onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logi("TS onPause");
    }

    private void logi(String info) {
        Log.i(TAG, info);
    }

    private void logd(String info) {
        Log.d(TAG, info);
    }
}