package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
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
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

                }
            }
        };

    }
}
