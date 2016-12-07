package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import demo.android.com.testcostomspinner.R;

/**
 * Created by Administrator on 2016-12-6.
 * 安卓自定义View进阶-Canvas之图片的综合示例
 * 利用drawBitmap第三种方法制作的一个简单示例：
 */

public class CheckView extends View {

    private static final int ANIM_NULL = 0;
    private static final int ANIM_CHECK = 1;
    private static final int ANIM_UNCHECK = 2;

    private Context context;
    private int mWidth,mHeigh;//宽高
    private Handler handler;

    private Paint paint;
    private Bitmap okBitmap;

    private int animCurrentPage = -1;//当前页码
    private int animMaxPage = 13;//总页数
    private int animDuration = 500;//动画时常
    private int animState = ANIM_NULL;//动画状态

    private boolean isCheck = false;//是否只选中状态

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigh = h;
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setColor(0xffff6666);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.check_mark);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0 ,animDuration / animMaxPage);
                    Log.e("当前页数","Count:" + animCurrentPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动坐标系
        canvas.translate(mWidth/2,mHeigh/2);

        //绘制背景圆形
        canvas.drawCircle(0,0,240,paint);

        //得出图像边长
        int sideLength = okBitmap.getHeight();

        //得到图像选区(src) 和 实际绘制位置(dst)
        Rect src = new Rect(sideLength*animCurrentPage,0,sideLength*(animCurrentPage+1),sideLength);
        Rect dst = new Rect(-200,-200,200,200);

        //绘制
        canvas.drawBitmap(okBitmap,src,dst,null);
    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck) return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        handler.sendEmptyMessageDelayed(0,animDuration/animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || !isCheck) return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        handler.sendEmptyMessageDelayed(0,animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration<=0) return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形的颜色
     * @param color
     */
    public void setBackgroundColors(int color) {
        paint.setColor(color);
    }
}
