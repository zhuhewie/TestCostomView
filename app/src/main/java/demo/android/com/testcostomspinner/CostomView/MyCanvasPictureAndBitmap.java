package demo.android.com.testcostomspinner.CostomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import demo.android.com.testcostomspinner.R;

/**
 * Created by Administrator on 2016-12-6.
 * 安卓自定义View进阶-Canvas之图片文字
 */

public class MyCanvasPictureAndBitmap extends View {

    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint paint = new Paint();
    // 1.创建Picture
    private Picture mPicture = new Picture();
    private Context context;

    public MyCanvasPictureAndBitmap(Context context) {
        super(context);
        this.context = context;
        recording();

    }

    public MyCanvasPictureAndBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    public MyCanvasPictureAndBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    /**
     * 录制内容的方法
     * beginRecording (int width, int height)
     * endRecording ()
     * beginRecording 和 endRecording 是成对使用的，一个开始录制，一个是结束录制，两者之间的操作将会存储在Picture中。
     */
    private void recording() {
        Canvas canvas = mPicture.beginRecording(500, 500);
        Paint mpaint = new Paint();
        mpaint.setColor(Color.BLUE);
        mpaint.setStyle(Paint.Style.FILL_AND_STROKE);


        //在Canvas中具体操作
        //位移
        canvas.translate(200, 200);

        canvas.drawCircle(0, 0, 100, mpaint);

        mPicture.endRecording();

        //将picture中的内容绘制在画布上
//        mPicture.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        pictureDraw(canvas);
//        canvasDrawPicture(canvas);
//        pictureDrawable(canvas);
        drawBitmap(canvas);
    }
/////////////////////将Picture中的内容绘制出来可以有以下几种方法///////////////////////////
    //1:使用Picture提供的draw方法绘制。
    //2:使用Canvas提供的drawPicture方法绘制。
    //3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
///////////////////////////////////////////////////////////////////////////////////////////////////////
//    __________________________________________________________________________________________________
//    |主要区别           |分类                |               简介                                     |
//    |__________________|____________________|________________________________________________________|
//    |是否对Canvas有影响	 |1有影响,2,3不影响	. |此处指绘制完成后是否会影响Canvas的状态(Matrix clip等)      |
//    |__________________|____________________|________________________________________________________|
//    |可操作性强弱	     |1可操作性较弱        |                                                        |
//    |                  |2,3可操作性较强	      |此处的可操作性可以简单理解为对绘制结果可控程度              |
//    |__________________|____________________|________________________________________________________|

    /**
     * 1:使用Picture提供的draw方法绘制
     * 将picture绘制出来的方法
     * 这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。
     *
     * @param canvas
     */
    private void pictureDraw(Canvas canvas) {
        //将picture中的内容绘制在画布上
        mPicture.draw(canvas);
    }

    /**
     * 2.使用Canvas提供的drawPicture方法绘制
     * drawPicture有三种方法
     * public void drawPicture (Picture picture)
     * public void drawPicture (Picture picture, Rect dst)
     * public void drawPicture (Picture picture, RectF dst)
     * Canvas的drawPicture不会影响Canvas状态
     *
     * @param canvas
     */
    private void canvasDrawPicture(Canvas canvas) {
        //mPicture会随着RectF大小,放大缩小,平铺在RectF的整个区域,图形会变形.
        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), mPicture.getHeight() + 200));
    }

    /**
     * 3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制
     * PS:此处setBounds是设置在画布上的绘制区域，并非根据该区域进行缩放，也不是剪裁Picture，每次都从Picture的左上角开始绘制。
     *
     * @param canvas
     */
    private void pictureDrawable(Canvas canvas) {
//      包装成Drawable
        PictureDrawable pDrawable = new PictureDrawable(mPicture);
        //设置绘制区域,注意此处所绘制的实际内容不会缩放
        pDrawable.setBounds(0, 0, 250, mPicture.getHeight());
        //绘制
        pDrawable.draw(canvas);
    }


/////////////////////////////////(2)drawBitmap////////////////////////////////////////////////////////////////////////
//    __________________________________________________________________________________________________
//    |序号              |获取方式              |               备注                                      |
//    |__________________|_____________________|_________________________________________________________|
//    |1                 |通过Bitmap创建        |复制一个已有的Bitmap(新Bitmap状态和原有的一致)             |
//    |                  |                     |或者 创建一个空白的Bitmap(内容可改变)                      |
//    |__________________|_____________________|________________________________________________________|
//    |2                 |通过BitmapDrawable获取|从资源文件 内存卡 网络等地方获取一张图片并转换              |
//    |                  |                     |为内容不可变的Bitmap                                      |
//    |__________________|_____________________|_________________________________________________________|
//    |3                 |通过BitmapFactory获取 |从资源文件 内存卡 网络等地方获取一张图片并转换为             |
//    |                  |                     |内容不可变的Bitmap                                        |
//    |__________________|_____________________|_________________________________________________________|

    /**
     * 将Bitmap绘制到画布上
     * 第一种
     * public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)
     * 两个参数(matrix, paint)是在绘制的时候对图片进行一些改变，
     * 如果只是需要将图片内容绘制出来只需要如下操作就可以了
     * canvas.drawBitmap(bitmap,new Matrix(),new Paint());
     * <p>
     * 第二种
     * public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
     * 在绘制时指定了图片左上角的坐标(距离坐标原点的距离)：
     * 注意：此处指定的是与坐标原点的距离，并非是与屏幕顶部和左侧的距离,
     * 虽然默认状态下两者是重合的，但是也请注意分别两者的不同
     * <p>
     * 第三种
     * public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
     * 多了两个矩形区域(src,dst)
     * Rect src:指定绘制图片的区域
     * Rect dst 或RectF dst:指定图片在屏幕上显示(绘制)的区域
     *
     * @param canvas
     */
    private void drawBitmap(Canvas canvas) {
        //通过BitmapFactory从不同位置获取Bitmap的方法:
        //1:资源文件(drawable/mipmap/raw):
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_canvas);
        //2:资源文件(assets):
//        Bitmap bitmap=null;
//        try {
//            InputStream is = mContext.getAssets().open("bitmap.png");
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 3:内存卡文件:
        // Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");
        //4:网络文件:
        //// 此处省略了获取网络输入流的代码
        //Bitmap bitmap = BitmapFactory.decodeStream(is);
        //is.close();

//        将Bitmap绘制到画布上,三种方法
        /**
         * 将Bitmap绘制到画布上 方法1:*/
//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());

        /**将Bitmap绘制到画布上 方法2:200,500距离坐标原点的距离*/
        //canvas.drawBitmap(bitmap,200,500,new Paint());

        /**
         * 将Bitmap绘制到画布上 方法3:
         */
        //将画布坐标系移动到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);
        //指定图片绘制的区域(左上角的四分之一)
        Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()/2);

        //指定图片在屏幕上的显示的区域,图片宽高会根据指定的区域自动进行缩放
        Rect dst = new Rect(0,0,200,400);

        //绘制图片
        canvas.drawBitmap(bitmap,src,dst,null);
    }
}
