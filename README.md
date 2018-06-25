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




### 接入方式：

//TODO
//TODO
//TODO

### 项目结构：


1、insert_monitor_plugin : 数据采集(asm的方式)

2、insert_monitor : 数据采集 + 数据初步处理

