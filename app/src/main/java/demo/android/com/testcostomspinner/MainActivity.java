package demo.android.com.testcostomspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

import demo.android.com.testcostomspinner.CostomView.CheckView;
import demo.android.com.testcostomspinner.CostomView.MyCanvasText;
import demo.android.com.testcostomspinner.CostomView.PieData;
import demo.android.com.testcostomspinner.CostomView.PieView;

public class MainActivity extends AppCompatActivity {

    int i ;
    private Button btCheck;
    private Button btUnCheck;
    private CheckView checkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        1:canvas初探
//        setContentView(new MyCanvas(this));//简单的几何图案

        //2:Canvas画圆饼图
//        PieView view = new PieView(this);
//        setContentView(view); // 饼状图
//        addPieData(view);
//        view.setStartAngle(120);


        //3:Canvas之画布操作
//        MyCanvas_2 view = new MyCanvas_2(this);
//        setContentView(view); // 饼状图


//        4:Canvas之图片
//        MyCanvasPictureAndBitmap view = new MyCanvasPictureAndBitmap(this);
//        setContentView(view); // 饼状图

        //5:Canvas之图片的Bitmap  drawBitmap
//        setContentView(R.layout.activity_main); // 饼状图
//        checkView = (CheckView) findViewById(R.id.checkView);
//        btCheck = (Button) findViewById(R.id.bt_check);
//        btUnCheck = (Button) findViewById(R.id.bt_uncheck);
//        addClick();

        //6:Cnavas之绘制文字
        MyCanvasText view = new MyCanvasText(this);
        setContentView(view);

    }

    private void addClick() {
        btCheck.setOnClickListener(v ->checkView.check());
        btUnCheck.setOnClickListener(v ->checkView.unCheck());
    }

    private void addPieData(PieView view) {
        ArrayList<PieData> datas = new ArrayList<>();
        datas.add(new PieData("甲", 60));
        datas.add(new PieData("乙", 30));
        datas.add(new PieData("丙", 40));
        datas.add(new PieData("丁", 20));
        datas.add(new PieData("戊", 10));
        view.setData(datas);
    }

}
