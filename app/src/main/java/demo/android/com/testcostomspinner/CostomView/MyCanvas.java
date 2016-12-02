package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016-12-2.
 */

public class MyCanvas extends View {
    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1:设置画布的背景色
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        //2:定义矩形为空心
//        paint.setStyle(Paint.Style.STROKE);//空心
//        paint.setStyle(Paint.Style.FILL);//实心
        paint.setStrokeCap(Paint.Cap.SQUARE);//

        //消除锯齿
        paint.setAntiAlias(true);
        //3:设置画笔的 颜色
        paint.setColor(Color.GREEN);
        //4:设置外边框宽度 5个像素值
        paint.setStrokeWidth(10);

//        //三角形
//        Path path = new Path();
//        path.moveTo(100,100);
//        path.lineTo(70,300);
//        path.lineTo(40,100);
//        path.close();
//        canvas.drawPath(path,paint);

//        //画一个圆
//        canvas.drawCircle(40,30,20,paint);
//        //画一个正方形
//        canvas.drawRect(20,70,70,120,paint);
//        //画一个长方形
        canvas.drawRect(20,150,100,200,paint);
//        //画一个椭圆
//        RectF rf = new RectF(20,220,100,270);
//        canvas.drawOval(rf,paint);

//        //定义图形为实心图案
//        paint.setStyle(Paint.Style.FILL);
//        Paint p2 = new Paint();
//
//        p2.setAntiAlias(true);//消除锯齿
//
//        p2.setColor(Color.RED);//设置画笔颜色
//
//        canvas.drawCircle(200,30,20,p2); // 圆
//        canvas.drawRect(200,100,270,170,p2);//正方形
//        canvas.drawRect(200,180,300,230,p2);
//        RectF rf2 = new RectF(200,240,300,300);
//        canvas.drawOval(rf2,p2);


    }
}
