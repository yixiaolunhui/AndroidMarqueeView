package com.dalong.marqueeview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.TextView;


public class HorizontalMarqueeView extends HorizontalScrollView {
    private int andTextColor;
    private int andTextSize;
    private int andTextBbackgroundColor;
    private boolean andTextAnimUp;
    private boolean andTextAnimDown;
    private boolean andTextAnimLeft;
    private boolean andTextAnimRight;
    private int aniDuration;
    private String andTextDesc;

    private TextView andTextView;

    public HorizontalMarqueeView(Context context) {
        this(context, null);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AndTextViewLayout, defStyleAttr, R.style.def_and_text_layout);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.AndTextViewLayout_and_text_color) {
                andTextColor = typedArray.getColor(attr, 0);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_size) {
                andTextSize = typedArray.getInteger(attr, 0);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_background_color) {
                andTextBbackgroundColor = typedArray.getColor(attr, 0);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_anim_duration_second) {
                aniDuration = typedArray.getInteger(attr, 0) * 1000;

            } else if (attr == R.styleable.AndTextViewLayout_and_text_anim_up) {
                andTextAnimUp = typedArray.getBoolean(attr, false);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_anim_down) {
                andTextAnimDown = typedArray.getBoolean(attr, false);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_anim_left) {
                andTextAnimLeft = typedArray.getBoolean(attr, false);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_anim_right) {
                andTextAnimRight = typedArray.getBoolean(attr, true);

            } else if (attr == R.styleable.AndTextViewLayout_and_text_desc) {
                andTextDesc = typedArray.getString(attr);

            }
        }
        typedArray.recycle();
        initAndTextView();
    }

    private void initAndTextView() {
        setHorizontalScrollBarEnabled(false);

        andTextView = new TextView(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        andTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, andTextSize);
        andTextView.setTextColor(andTextColor);
        andTextView.setText(andTextDesc);
        andTextView.setMaxLines(1);
        andTextView.setBackgroundColor(andTextBbackgroundColor);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        andTextView.setLayoutParams(layoutParams);

        addView(andTextView);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    public void setAnimDuration(int duration) {
        this.aniDuration = duration * 1000;
    }

    public void setMarqueeTv(String tv) {
        if (andTextView != null) {
            andTextView.setText(tv);
        }
    }


    public void startAndTextAnim() {
        if (getWidth() != 0 && getHeight() != 0) {
            ObjectAnimator objectAnimator = creatCurrentAnimation();
            objectAnimator.setDuration(aniDuration);
            objectAnimator.setInterpolator(new LinearInterpolator());
            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
            objectAnimator.start();
        }
    }

    private ObjectAnimator creatCurrentAnimation() {
        ObjectAnimator objectAnimator = null;
        if (andTextAnimRight) {
            objectAnimator = ObjectAnimator.ofFloat(andTextView, "translationX", -andTextView.getMeasuredWidth(), getWidth());
        } else if (andTextAnimLeft) {
            objectAnimator = ObjectAnimator.ofFloat(andTextView, "translationX", getWidth(), -andTextView.getMeasuredWidth());
        } else if (andTextAnimUp) {
            objectAnimator = ObjectAnimator.ofFloat(andTextView, "translationY", -getHeight(), getHeight() + andTextView.getMeasuredHeight());
        } else if (andTextAnimDown) {
            objectAnimator = ObjectAnimator.ofFloat(andTextView, "translationY", getHeight() + andTextView.getMeasuredHeight(), -getHeight());
        }
        return objectAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startAndTextAnim();
    }
}
