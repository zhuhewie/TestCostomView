package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016-12-23.
 * Path之基本操作
 */

public class MyCanvasPath1 extends View {
    private int width;
    private int height;
    private Paint mPaint = new Paint();
    public MyCanvasPath1(Context context) {
        super(context);
    }

    public MyCanvasPath1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCanvasPath1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(width /2 ,height/2);
        Path path = new Path();

        //指从某个点到参数坐标点之间连一条线
        //这里的某个点就是上次操作结束的点，如果没有进行过操作则默认点为坐标原点
        path.lineTo(100,100);
        path.lineTo(200,0);

        canvas.drawPath(path,mPaint);
    }
}
