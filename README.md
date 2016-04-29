# AndroidMarqueeVie 跑马灯
   1、可以自定义字体颜色，字体大小。</p>
   2、可以设置是否重复滚动。</p>
   3、可以设置背景颜色。</p>
   4、可以设置滚动方向。</p>
   5、可以设置滚动速度。</p>
   6、可以设置滚动的位置。</p>
   7、可以停止和开始滚动。</p>
#效果图

设置1：设置不可重复滚动，滚动完毕不在滚动（方向向左）
         <com.dalong.marqueeview.MarqueeView
                android:id="@+id/mMarqueeView"
                app:textcolor="@color/colorAccent" //滚动字体的颜色
                app:textSize="16sp" //滚动字体的大小
                app:startPoint="end"//滚动起点
                app:isRepeat="false"//是否重复滚动
                app:direction="left"//滚动方向
                app:speed="50"//滚动速度
                app:marqueebackground="@color/colorPrimaryDark"//滚动背景颜色
                android:layout_width="match_parent"
                android:layout_height="50dp" />

![image](https://github.com/dalong982242260/AndroidMarqueeView/blob/master/gif/marqueeview1.gif?raw=true)


设置2：设置不可重复滚动，滚动完毕不在滚动 （方向向右）
         <com.dalong.marqueeview.MarqueeView
                android:id="@+id/mMarqueeView"
                app:textcolor="@color/colorAccent" //滚动字体的颜色
                app:textSize="16sp" //滚动字体的大小
                app:startPoint="end"//滚动起点
                app:isRepeat="false"//是否重复滚动
                app:direction="right"//滚动方向
                app:speed="50"//滚动速度
                app:marqueebackground="@color/colorPrimaryDark"//滚动背景颜色
                android:layout_width="match_parent"
                android:layout_height="50dp" />

![image](https://github.com/dalong982242260/AndroidMarqueeView/blob/master/gif/marqueeview2.gif?raw=true)

设置3：设置可重复滚动，滚动完毕再次滚动 （方向向左）
         <com.dalong.marqueeview.MarqueeView
                android:id="@+id/mMarqueeView"
                app:textcolor="@color/colorAccent" //滚动字体的颜色
                app:textSize="16sp" //滚动字体的大小
                app:startPoint="end"//滚动起点
                app:isRepeat="true"//是否重复滚动
                app:direction="left"//滚动方向
                app:speed="50"//滚动速度
                app:marqueebackground="@color/colorPrimaryDark"//滚动背景颜色
                android:layout_width="match_parent"
                android:layout_height="50dp" />

![image](https://github.com/dalong982242260/AndroidMarqueeView/blob/master/gif/marqueeview3.gif?raw=true)


设置4：设置可重复滚动，滚动完毕再次滚动 （方向向右）
         <com.dalong.marqueeview.MarqueeView
                android:id="@+id/mMarqueeView"
                app:textcolor="@color/colorAccent" //滚动字体的颜色
                app:textSize="16sp" //滚动字体的大小
                app:startPoint="end"//滚动起点
                app:isRepeat="true"//是否重复滚动
                app:direction="right"//滚动方向
                app:speed="50"//滚动速度
                app:marqueebackground="@color/colorPrimaryDark"//滚动背景颜色
                android:layout_width="match_parent"
                android:layout_height="50dp" />

![image](https://github.com/dalong982242260/AndroidMarqueeView/blob/master/gif/marqueeview4.gif?raw=true)



设置5：设置速度
         <com.dalong.marqueeview.MarqueeView
                android:id="@+id/mMarqueeView"
                app:textcolor="@color/colorAccent" //滚动字体的颜色
                app:textSize="16sp" //滚动字体的大小
                app:startPoint="end"//滚动起点
                app:isRepeat="true"//是否重复滚动
                app:direction="right"//滚动方向
                app:speed="20"//滚动速度
                app:marqueebackground="@color/colorPrimaryDark"//滚动背景颜色
                android:layout_width="match_parent"
                android:layout_height="50dp" />

![image](https://github.com/dalong982242260/AndroidMarqueeView/blob/master/gif/marqueeview5.gif?raw=true)