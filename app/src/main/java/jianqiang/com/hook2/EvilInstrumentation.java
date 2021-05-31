package jianqiang.com.hook2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @author weishu
 * @date 16/1/28
 */
public class EvilInstrumentation extends Instrumentation {

    private static final String TAG = "sanbo.EvilInstrumenta";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public EvilInstrumentation(Instrumentation base) {
        mBase = base;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        logd("XXX到此一游! target:" + target + "----- " + intent);

        // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
        Class[] p1 = {Context.class, IBinder.class,
                IBinder.class, Activity.class,
                Intent.class, int.class, Bundle.class};

        // 能拦截到，拦截替换Intent
//         Object[] v1 = {who, contextThread, token, target,
//                        intent, requestCode, options};

        ComponentName cn = intent.getComponent();
        logi("cn.getPackageName().equals(\"jianqiang.com.hook2\"): " + cn.getPackageName().equals("jianqiang.com.hook2"));
        logi(" cn.getClassName().equals(\"jianqiang.com.hook2.SecondActivity\"): " + cn.getClassName().equals("jianqiang.com.hook2.SecondActivity"));
        if (cn.getPackageName().equals("jianqiang.com.hook2") && cn.getClassName().equals("jianqiang.com.hook2.SecondActivity")) {
            cn = new ComponentName(who, "jianqiang.com.hook2.TsActivity");
            intent.setComponent(cn);
        }
        logd("让我们拦截一下 target:" + target + "----- " + intent);
        Object[] v1 = {who, contextThread, token, target,
                intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(
                mBase, "execStartActivity", p1, v1);
    }

    private void logi(String info) {
        Log.i(TAG, info);
    }

    private void logd(String info) {
        Log.d(TAG, info);
    }
}

