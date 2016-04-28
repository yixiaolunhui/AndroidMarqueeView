package com.dalong.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhouweilong on 16/4/28.
 */
public class MarqueeView  extends SurfaceView implements SurfaceHolder.Callback{


    private float mTextSize = 100; //字体大小

    private int mTextColor = Color.RED; //字体的颜色

    private int mBackgroundColor=Color.WHITE;//背景色

    private boolean mIsRepeat;//是否重复滚动

    private int mStartPoint;// 开始滚动的位置  0是从最左面开始    1是从最末尾开始

    private int mDirection;//滚动方向 0 向左滚动   1向右滚动

    private int mSpeed;//滚动速度

    private SurfaceHolder holder;

    private TextPaint mTextPaint;

    private MarqueeViewThread mThread;

    private String margueeString;

    private int textWidth=0,textHeight=0;

    private int ShadowColor=Color.BLACK;

    public int currentX=0;// 当前x的位置

    public int sepX=5;//每一步滚动的距离

    public MarqueeView(Context context) {
        this(context,null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView, defStyleAttr, 0);
        mTextColor = a.getColor(R.styleable.MarqueeView_textcolor, Color.RED);
        mTextSize = a.getDimension(R.styleable.MarqueeView_textSize, 48);
        mBackgroundColor=a.getColor(R.styleable.MarqueeView_marqueebackground,Color.BLACK);
        mIsRepeat=a.getBoolean(R.styleable.MarqueeView_isRepeat,false);
        mStartPoint=a.getInt(R.styleable.MarqueeView_startPoint,0);
        mDirection=a.getInt(R.styleable.MarqueeView_direction,0);
        mSpeed=a.getInt(R.styleable.MarqueeView_speed,20);
        a.recycle();

        holder = this.getHolder();
        holder.addCallback(this);
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    public void setText(String msg){
        if(!TextUtils.isEmpty(msg)){
            measurementsText(msg);
        }
    }
    protected void measurementsText(String msg) {
        margueeString=msg;
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setFakeBoldText(true);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        mTextPaint.setShadowLayer(5, 3, 3, ShadowColor);
        textWidth = (int)mTextPaint.measureText(margueeString);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        textHeight = (int) fontMetrics.bottom;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mThread!=null)
            mThread.isRun = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mThread!=null)
            mThread.isRun = false;
    }

    /**
     * 开始滚动
     */
    public  void startScroll(){
        if(mThread!=null&&mThread.isRun)
            return;
        mThread = new MarqueeViewThread(holder);//创建一个绘图线程
        mThread.start();
    }

    /**
     * 停止滚动
     */
    public  void stopScroll(){
        if(mThread!=null){
            mThread.isRun = false;
            mThread.interrupt();
        }

        mThread=null;
    }
    /**
     * 线程
     */
    class MarqueeViewThread extends Thread{

        private SurfaceHolder holder;

        public boolean isRun ;//是否在运行


        public  MarqueeViewThread(SurfaceHolder holder) {
            this.holder =holder;
            isRun = true;
        }

        public void onDraw() {
            try {
                synchronized (holder) {
                    if (TextUtils.isEmpty(margueeString)) {
                        Thread.sleep(1000);//睡眠时间为1秒
                        return;
                    }
                    Canvas canvas = holder.lockCanvas();
                    int paddingLeft = getPaddingLeft();
                    int paddingTop = getPaddingTop();
                    int paddingRight = getPaddingRight();
                    int paddingBottom = getPaddingBottom();

                    int contentWidth = getWidth() - paddingLeft - paddingRight;
                    int contentHeight = getHeight() - paddingTop - paddingBottom;

                    int centeYLine = paddingTop + contentHeight / 2;//中心线

                    if(mDirection==0) {//向左滚动
                        if(currentX <=-textWidth){
                            if(!mIsRepeat){//如果是不重复滚动
                                stopScroll();
                                return;
                            }
                            if(mStartPoint==0){//最初位置
                                currentX=0;
                            }else{//最后位置
                                currentX=contentWidth;
                            }

                        }else{
                            currentX-=sepX;
                        }
                    }else {//  向右滚动
                        if(currentX>=contentWidth){
                            if(mStartPoint==0){//最初位置
                                currentX=0;
                            }else{//最后位置
                                currentX=-textWidth;
                            }
                        }else{
                            currentX+=sepX;
                        }
                    }

                    canvas.drawColor(mBackgroundColor);
                    canvas.drawText(margueeString,currentX, centeYLine+dip2px(getContext(),textHeight)/2,mTextPaint);
                    holder.unlockCanvasAndPost(canvas);//结束锁定画图，并提交改变。
                    Thread.sleep(textWidth/margueeString.trim().length()/sepX);//睡眠时间为1秒
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (isRun) {
                onDraw();
            }

        }

    }


    /**
     * dip转换为px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
