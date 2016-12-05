package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016-12-5.
 */

public class MyCanvas_2 extends View {

    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint paint = new Paint();

    public MyCanvas_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCanvas_2(Context context) {
        super(context);
    }

    public MyCanvas_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        testTranslate(canvas);//坐标原点修改
//        testScale(canvas);//缩放
//        testRotate(canvas);//旋转
        testSkew(canvas);//错切
    }


    /**
     * translate是坐标系的移动，可以为图形绘制选择一个合适的坐标系。
     * 请注意，位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
     *
     * @param canvas
     */
    private void testTranslate(Canvas canvas) {
        // 在坐标原点绘制一个红色的圆形
        paint.setColor(Color.RED);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, paint);

        // 在坐标原点绘制一个蓝色圆形
        paint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, paint);
    }

    /**
     * 测试缩放
     * 两种方法 scale (float sx, float sy)
     * scale (float sx, float sy, float px, float py)
     * 这两个方法中前两个参数是相同的分别为x轴和y轴的缩放比例。
     * 而第二种方法比前一种多了两个参数，用来控制缩放中心位置的
     * 注意:缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴
     */
    private void testScale(Canvas canvas) {
        //坐标原点移动到画布中心
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(-400, -400, 400, 400); //矩形区域

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN); //绿色
        paint.setStrokeWidth(10);
        canvas.drawRect(rect, paint);

//        canvas.scale(0.5f,0.5f);// 画布缩放
//        canvas.scale(0.5f, 0.5f, 200, 0);// 画布缩放  <-- 缩放中心向右偏移了200个单位

//        当缩放比例为负数的时候会根据缩放中心轴进行翻转
//        canvas.scale(-0.5f, -0.5f);// 画布缩放
//        canvas.scale(-0.5f, -0.5f, 200, 0);// 画布缩放旋转  <-- 缩放中心向右偏移了200个单位
        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, paint);
        }


        paint.setColor(Color.BLACK); //黑色矩形
        canvas.drawRect(rect, paint);
    }

    /**
     * 旋转:两种方法
     * rotate (float degrees)
     * rotate (float degrees, float px, float py)
     * 旋转也是可叠加的
     *
     * @param canvas
     */
    private void testRotate(Canvas canvas) {
        //坐标原点移动到画布中心
        canvas.translate(mWidth / 2, mHeight / 2);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(0, -300, 300, 0); //矩形区域

        paint.setColor(Color.BLUE);    //绘制黑色矩形
        canvas.drawRect(rectF, paint);

        canvas.rotate(180); //旋转180度 <--默认旋转中心为原点-->
//        canvas.rotate(180,150,0); //旋转180度 <--旋转中心为右移动150,移动的是坐标原点-->


        paint.setColor(Color.BLACK);//旋转后的矩形
        canvas.drawRect(rectF, paint);

        canvas.drawCircle(0, 0, 500, paint); //画两个圆
        canvas.drawCircle(0, 0, 520, paint);

        for (int i = 0; i <= 360; i += 10) {
            canvas.drawLine(0, 520, 0, 500, paint);
            canvas.rotate(10);//旋转10°
        }
    }

    /**
     * 错切 :skew这里翻译为错切，错切是特殊类型的线性变换
     * 只提供了一种方法
     * public void skew (float sx, float sy)
     * float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
     * float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
     * <p>
     * X = x + sx * y
     * Y = sy * x + y
     *
     * @param canvas
     */
    private void testSkew(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);


        RectF rectF = new RectF(0, 0, 200, 300);//矩形区域
        paint.setColor(Color.BLACK); //绘制黑色矩形
        canvas.drawRect(rectF,paint);

        canvas.skew(1,0); //水平错切 <--45度
        canvas.skew(0,1);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF,paint);
    }
}
