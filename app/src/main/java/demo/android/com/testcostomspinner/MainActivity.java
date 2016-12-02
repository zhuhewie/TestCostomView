package demo.android.com.testcostomspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import demo.android.com.testcostomspinner.CostomView.PieData;
import demo.android.com.testcostomspinner.CostomView.PieView;

public class MainActivity extends AppCompatActivity {

    int i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyCanvas(this));//简单的几何图案
        PieView view = new PieView(this);
        setContentView(view); // 饼状图
        addPieData(view);
        view.setStartAngle(120);

//        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                i = i+1;
//            }
//        });
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
