package weixi.example.com.myquickindex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weixi.example.com.myquickindex.adapter.MyAdapter;
import weixi.example.com.myquickindex.bean.Haohan;
import weixi.example.com.myquickindex.ui.QuickIndexBar;
import weixi.example.com.myquickindex.utils.Cheeses;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Haohan> list;
    private TextView popIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        //存储好汉的集合
        list = new ArrayList<Haohan>();
        //对list进行填充和排序
        fillAndSortData(list);

        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
        //中间弹出的字母提示
        popIcon = (TextView) findViewById(R.id.popIcon);

        QuickIndexBar indexBar = (QuickIndexBar) findViewById(R.id.index);
        indexBar.setListener(new QuickIndexBar.onLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                //根据字母定位到listView中，找到集合中第一个以当前letter为名字的条目
                for (int i = 0; i < list.size(); i++) {
                    Haohan h=list.get(i);
                    if (TextUtils.equals(letter,h.getPinyin().charAt(0)+"")){
                        listView.setSelection(i);
                        showPopIcon(letter); //点击或移动时显示字母提示
                        break;
                    }
                }
            }
        });

    }

    private Handler mHandler=new Handler();

    private void showPopIcon(String letter) {
        popIcon.setVisibility(View.VISIBLE);
        popIcon.setText(letter);
        //用Handler做一个延时gone的操作
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popIcon.setVisibility(View.GONE);
            }
        },2000);
    }

    private void fillAndSortData(List<Haohan> list) {
        //填充数据
        for (String s : Cheeses.NAMES) {
            list.add(new Haohan(s));
        }
        //排序
        Collections.sort(list);
    }
}
