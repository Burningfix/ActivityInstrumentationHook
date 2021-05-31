# 《Android插件化开发指南》随书源码

本书勘误地址：https://www.cnblogs.com/Jax/p/9316422.html


## 做法

Hook `Activity` 的`mInstrumentation` 变量，重写Instrumentation的对象的`execStartActivity`方法，将其中的参数Intent的`ComponentName`修改成目标

* 图例

MainActivity-->SecondActivity
            |
            |
            修
            改
            |
            |
            --> TsActivity --可通过调整再打开SecondActivity-->SecondActivity

* 注意: 必须声明了的activity才能跳转其他页面。中转也需要上个页面

* 日志也看不到未声明的页面

```
1511-10937/system_process I/ActivityTaskManager: START u0 {act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] flg=0x10000000 cmp=jianqiang.com.hook2/.MainActivity} from uid 0
1511-1675/system_process I/ActivityTaskManager: Displayed jianqiang.com.hook2/.MainActivity: +212ms
31412-31412/jianqiang.com.hook2 D/sanbo.EvilInstrumenta: XXX到此一游! [false] target:jianqiang.com.hook2.MainActivity@3007957----- Intent { cmp=jianqiang.com.hook2/.SecondActivity }
31412-31412/jianqiang.com.hook2 D/sanbo.EvilInstrumenta: 让我们拦截一下 [true] target:jianqiang.com.hook2.MainActivity@3007957----- Intent { cmp=jianqiang.com.hook2/.TsActivity (has extras) }
1511-7337/system_process I/ActivityTaskManager: START u0 {cmp=jianqiang.com.hook2/.TsActivity (has extras)} from uid 10242
31412-31412/jianqiang.com.hook2 I/sanbo.TsActivity: ac:jianqiang.com.hook2.SecondActivity
31412-31412/jianqiang.com.hook2 D/sanbo.EvilInstrumenta: XXX到此一游! [true] target:jianqiang.com.hook2.MainActivity@3007957----- Intent { cmp=jianqiang.com.hook2/.SecondActivity }
31412-31412/jianqiang.com.hook2 D/sanbo.EvilInstrumenta: 让我们拦截一下 [true] target:jianqiang.com.hook2.MainActivity@3007957----- Intent { cmp=jianqiang.com.hook2/.SecondActivity }
1511-2318/system_process I/ActivityTaskManager: START u0 {cmp=jianqiang.com.hook2/.SecondActivity} from uid 10242
```