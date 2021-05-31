package jianqiang.com.hook2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hookActivitymInstrumentation();

        Button tv = new Button(this);
        tv.setText("测试界面");
        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 替换页面打开，现在看每个版本都有
     */
    private void hookActivitymInstrumentation() {

        //find . -name "Activity.java" | xargs grep --color=auto -rnsw "private Instrumentation mInstrumentation;"
        //sdk/sources/android-15/android/app/Activity.java:656:    private Instrumentation mInstrumentation;
        //sdk/sources/android-16/android/app/Activity.java:669:    private Instrumentation mInstrumentation;
        //sdk/sources/android-17/android/app/Activity.java:671:    private Instrumentation mInstrumentation;
        //sdk/sources/android-18/android/app/Activity.java:671:    private Instrumentation mInstrumentation;
        //sdk/sources/android-19/android/app/Activity.java:674:    private Instrumentation mInstrumentation;
        //sdk/sources/android-20/android/app/Activity.java:675:    private Instrumentation mInstrumentation;
        //sdk/sources/android-21/android/app/Activity.java:690:    private Instrumentation mInstrumentation;
        //sdk/sources/android-22/android/app/Activity.java:690:    private Instrumentation mInstrumentation;
        //sdk/sources/android-23/android/app/Activity.java:702:    private Instrumentation mInstrumentation;
        //sdk/sources/android-24/android/app/Activity.java:737:    private Instrumentation mInstrumentation;
        //sdk/sources/android-25/android/app/Activity.java:737:    private Instrumentation mInstrumentation;
        //sdk/sources/android-26/android/app/Activity.java:746:    private Instrumentation mInstrumentation;
        //sdk/sources/android-27/android/app/Activity.java:747:    private Instrumentation mInstrumentation;
        //sdk/sources/android-28/android/app/Activity.java:771:    private Instrumentation mInstrumentation;
        //sdk/sources/android-29/android/app/Activity.java:791:    private Instrumentation mInstrumentation;
        //sdk/sources/android-30/android/app/Activity.java:795:    private Instrumentation mInstrumentation;
        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject(Activity.class, this, "mInstrumentation");
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);
        RefInvoke.setFieldObject(Activity.class, this, "mInstrumentation", evilInstrumentation);
    }
}