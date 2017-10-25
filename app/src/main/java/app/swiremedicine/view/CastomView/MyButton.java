package app.swiremedicine.view.CastomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class MyButton extends View {
    private Paint mPaint;
    private Paint mPaint1;
    public MyButton(Context context) {
        super(context,null);
        init();
    }

    public MyButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public MyButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor("#65939A"));
        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方点

        mPaint1=new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setDither(true);
        mPaint1.setColor(Color.parseColor("#00FFFF"));
        mPaint1.setStrokeCap(Paint.Cap.SQUARE);//方点

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=mPaint;
        Paint paint1=mPaint1;

        canvas.drawCircle(290,700,250,paint);
        canvas.drawRoundRect(90,500,490,900,100,100,paint1);
        paint1.reset();
        paint1.setTextSize(80);
        canvas.drawText("上班打卡",160,700,paint1);
    }
}
