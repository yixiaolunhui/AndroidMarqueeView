package com.dalong.androidmarqueeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dalong.marqueeview.HorizontalMarqueeView;
import com.dalong.marqueeview.MarqueeView;

public class MainActivity extends AppCompatActivity {

    private MarqueeView mMarqueeView;
    private HorizontalMarqueeView mMarqueeViewV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMarqueeView = (MarqueeView) findViewById(R.id.mMarqueeView);
        mMarqueeViewV2 = (HorizontalMarqueeView) findViewById(R.id.horizontal_marqueeview);
        mMarqueeView.setText("依据赫兹接触强度计算理论，着重研究了圆柱滚子轴承内、外圈及滚动体的接触应力");
        mMarqueeViewV2.setMarqueeTv("依据赫兹接触强度计算理论，着重研究了圆柱滚子轴承内、外圈及滚动体的接触应力");
        mMarqueeViewV2.setAnimDuration(4);
        mMarqueeViewV2.startAndTextAnim();

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMarqueeView.startScroll();
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMarqueeView.stopScroll();
            }
        });

        mMarqueeView.setOnMargueeListener(new MarqueeView.OnMargueeListener() {
            @Override
            public void onRollOver() {
                Toast.makeText(MainActivity.this, "滚动完毕", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMarqueeView.startScroll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMarqueeView.stopScroll();
    }
}
