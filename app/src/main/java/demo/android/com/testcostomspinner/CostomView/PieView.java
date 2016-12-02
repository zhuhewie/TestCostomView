package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-2.
 * 试着写<<安卓自定义View进阶-Canvas之绘制图形>>
 *     的圆饼图
 */

public class PieView extends View {
    //颜色表 (ARGB ,带Alpha通道)
    private int[] mColors = {0xffccff00, 0xff0000ff,
            0xff00ff00,0xffffff00,0xffff0000};
    //饼状图初始绘制角度
    private float mStarAngle = 0;
    //数据
    private ArrayList<PieData> mData;
    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint paint = new Paint();


    public PieView(Context context) {
        super(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        if (null == mData) return;

        float currentStartAngle = mStarAngle;    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2); //将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth,mHeight) / 2 * 0.8); // 饼状图半径
        RectF rectF = new RectF(-r,-r,r,r);     // 饼状图绘制区域

        for (int i = 0;i < mData.size();i++) {
            PieData pieData = mData.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(rectF,currentStartAngle,pieData.getAngle(),true,paint);
            currentStartAngle += pieData.getAngle();
        }
    }

    //设置起始角度
    public void setStartAngle(int mStarAngle) {
        this.mStarAngle = mStarAngle;
        invalidate(); //刷新
    }

    //设置数据
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();//刷新
    }

    //初始化数据
    private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0) return; //数据为空.,直接返回
        float sumValue = 0;

        for (int i = 0;i<mData.size();i++) {
            PieData pie = mData.get(i);

            sumValue += pie.getValue(); //计算数值的和

            int j = i % mColors.length;
            pie.setColor(mColors[j]);  //设置颜色
        }

        float sumAngle = 0;
        for (int i = 0;i<mData.size();i++) {
            PieData pie = mData.get(i);

            float percentage = pie.getValue() / sumValue ; //百分比
            float angle = percentage * 360;  //对应的角度

            pie.setPercentage(percentage);  //记录百分比
            pie.setAngle(angle);            //记录角度大小
            sumAngle += angle;

            Log.i("angle",pie.getAngle()+"");
        }
    }

}
