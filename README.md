# InsertMonitor

Insert Monitor 是针对Android线上线下性能监控的基础库，支持自定义事件和自定义日志。同时框架本身支持以下事件和日志：

### 监控维度：

一、事件：

1）App启动：一系列时间点

2）Activity启动：一系列时间点

3）Fragment启动：一系列时间点


二、日志：

1、线上

1）基础性能：cpu、内存、流量

2）流畅度：sm值

2、线下

1）主线程卡顿定位

2）主线程耗时Message

3）Layout构建耗时

4）View绘制耗时

5）GC耗时

6）DB耗时检测

7）主线程DB检测

8）线程分析

9) v4Fragment监控

10） 网络监控

### 实现原理：

框架的基本实现原理是：AOP+Hack+普通采集

其中AOP有两种实现方式：

1、asm方式

2、aspectjx方式

### 接入方式：

一、asm方式：

    dependencies {
        classpath 'com.elvis.android:insert_monitor_plugin:1.0.3'
    }

    apply plugin: 'insert_monitor_plugin'

    dependencies {
        compile project(path: ':insert_monitor')
        compile project(path: ':insert_monitor_ui')
    }

二、aspectjx方式：

    dependencies {
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.0'
    }

    apply plugin: 'android-aspectjx'

    dependencies {
        compile project(':insert_monitor')
        compile project(':insert_monitor_aspectjx')
        compile project(':insert_monitor_ui')
    }

### 项目结构：


1、insert_monitor_plugin : 数据采集(asm的方式)

2、insert_monitor : 数据采集 + 数据初步处理

