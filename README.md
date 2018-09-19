AudioCutView
============

## 目录

* [功能介绍](#功能介绍)
* [效果图](#效果图)
* [使用](#使用)

## 功能介绍
比较简单的控件，随机生成的数据，类似音频的波纹，也可以自己获取音频波纹的数据设置进去，左右滑动截取需要的部分。



## 效果图
![1](audiocutview.gif)

### 使用说明

> 布局文件

```xml
  <com.kkcast.audiocutview.AudioCutView
      android:id="@+id/audio_cut_view"
      android:layout_marginLeft="@dimen/my20dp"
      android:layout_marginRight="@dimen/my20dp"
      android:layout_centerInParent="true"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      />
```

> Java代码

```Java

 AudioCutView audio_cut_view = findViewById(R.id.audio_cut_view);
 
 //设置视频占音频的比例
 audio_cut_view.setVideoProgressWidth(0.3f);
 
 //设置当前的位置
 audio_cut_view.setCurrentProgress(progress);
 
 //设置最大proress
 audio_cut_view.setMaxProgress(100);
 
```

## 详细用法请查看 [AudioCutView](https://github.com/kkcast/AudioCutView)

## 关于我
<a  href="https://blog.csdn.net/kkcast" target="_blank">kkcast的博客</a> 

<a href="mailto:kkcast7@gmail.com" target="_blank">kkcast7@gmail.com</a>


