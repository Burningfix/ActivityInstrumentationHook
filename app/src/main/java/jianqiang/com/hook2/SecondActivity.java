package jianqiang.com.hook2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("声明 要打开的页面");
        setContentView(tv);
        //setContentView(R.layout.activity_main);

    }
}
