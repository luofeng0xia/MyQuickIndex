package weixi.example.com.myquickindex.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 自定义快速索引view
 * Created by weixi on 2016/7/14.
 */
public class QuickIndexBar extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    private Paint mPaint;
    private float cellHeight;
    private float cellWidth;
    private Rect bounds;
    private int index;

    public interface onLetterUpdateListener{
        void onLetterUpdate(String letter);
    }

    private onLetterUpdateListener listener;

    public onLetterUpdateListener getListener() {
        return listener;
    }

    public void setListener(onLetterUpdateListener listener) {
        this.listener = listener;
    }

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(38);
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < LETTERS.length; i++) {
            String text = LETTERS[i];
            //计算坐标
            int x = (int) (cellWidth / 2 - mPaint.measureText(text) / 2);
            //获取文字的高度
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            int y = (int) (cellHeight / 2 + bounds.height() / 2 + cellHeight * i);
//            Log.d("TAG", i + ": " + y);
            mPaint.setColor(index==i?Color.GRAY:Color.WHITE);
            canvas.drawText(text, x, y, mPaint);
        }
    }

    int lastPos = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        index = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                index = (int) (event.getY() / cellHeight);
                if (index >= 0 && index < LETTERS.length) {
                    if (lastPos != index) {
                        if (listener!=null) {
                            listener.onLetterUpdate(LETTERS[index]);
//                          Log.d("TAG", index + "");
                            lastPos = index;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                index = (int) (event.getY() / cellHeight);
                if (index >= 0 && index < LETTERS.length) {
                    if (lastPos != index) {
                        if (listener!=null) {
                            listener.onLetterUpdate(LETTERS[index]);
//                          Log.d("TAG", index + "");
                            lastPos = index;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                lastPos=-1;
                break;
        }
        invalidate();//强制重绘，onDraw方法被调用
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取每一个字母索引所占的空间大小
        cellWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }
}
