package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016-12-7.
 * .绘制文字
 * 制文字部分大致可以分为三类
 * 第一类只能指定文本基线位置(基线x默认在字符串左侧，基线y默认在字符串下方)。
 * 第二类可以分别指定每个文字的位置。
 * 第三类是指定一个路径，根据路径绘制文字。
 * // 第一类
 * public void drawText (String text, float x, float y, Paint paint)
 * public void drawText (String text, int start, int end, float x, float y, Paint paint)
 * public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
 * public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
 * <p>
 * // 第二类
 * public void drawPosText (String text, float[] pos, Paint paint)
 * public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
 * <p>
 * // 第三类
 * public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
 * public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
 */
public class MyCanvasText extends View {

    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint paint = new Paint();
    private Context context;

    public MyCanvasText(Context context) {
        super(context);
        init(context);
    }

    public MyCanvasText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCanvasText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init(Context context) {
        this.context = context;
        paint.setAntiAlias(true); //消除锯齿
        paint.setColor(Color.RED);//文字的颜色
        paint.setStyle(Paint.Style.FILL); //设置样式
        paint.setTextSize(20);  //设置字体大小
    }

    //要绘制的文本
    String str =
            "陌生人我也为你祝福\n" +
                    "愿你有一个灿烂的前程\n" +
                    "愿你有情人终成眷属\n" +
                    "愿你在尘世获得幸福\n" +
                    "我只愿面朝大海，春暖花开";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawText(canvas);//第一类 drawText
        drawPosText(canvas);//第二类
    }

    /**
     * 第一类drawText
     * 可以指定文本开始的位置，可以截取文本中部分内容进行绘制。
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        //方法1:
        //参数分别为
        // str:绘制的String;
        // 200:基线x(String图片的左侧那条线),距离坐标原轴x的距离;
        // 500:基线y(String图片的底部那条线)距离坐标轴y轴的距离;
        // paint:画笔
        //canvas.drawText(str,100,300,paint);

        //方法2:
        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        // 使用start和end指定的区间是前闭后开的，即包含start指定的下标，
        // 而不包含end指定的下标，故[1,3)最后获取到的下标只有 下标0 和 下标4 的字符，就是"陌生人我".
        //canvas.drawText(str,0,4,80,300,paint);

        //方法3:
        //将String转换成数组
        char[] charStr = str.toCharArray();

        //参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(charStr, 3, 3, 100, 300, paint);//我也为
    }

    /**
     * 第二类
     * 缺点
     * 1:必须指定所有字符位置，否则直接crash掉，反人类设计.数组的长度必须大于等于String的size.
     * 2:性能不佳，在大量使用的时候可能导致卡顿
     * 3:不支持emoji等特殊字符，不支持字形组合与分解
     *
     * @param canvas
     */
    private void drawPosText(Canvas canvas) {

        /**
         * 方法一
         *
         *
         * 3:不支持emoji等特殊字符，不支持字形组合与分解
         */
//        canvas.drawPosText("陌生人我也为你祝福愿", new float[]{
//                100, 100,   // 第一个字符位置
//                100, 200,   // 第二个字符位置
//                100, 300,   //...
//                100, 400,
//                100, 500,
//                100, 600,
//                100, 700,
//                100, 800,
//                100, 900,
//                200, 100,
//
//        }, paint);

        /**
         * 方法二
         * 参数(要绘制的数组,数组开始下标,截取长度,每个文字显示的位置,画笔)
         */
        canvas.drawPosText(str.toCharArray(), 3, 5, new float[]{
                100, 100,   // 第一个字符位置
                100, 200,   // 第二个字符位置
                100, 300,   //...
                100, 400,
                100, 500,
        }, paint);

    }
}
