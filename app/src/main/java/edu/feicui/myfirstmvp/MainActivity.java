package edu.feicui.myfirstmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity implements MyView{

    private ListView listView;
    private ArrayList<String> data;
    // 刷新
    private PtrClassicFrameLayout ptrFrameLayout;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始添加数据
        initData();
        //初始化布局
        initView();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        ptrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptrClassicFrameLayout);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        //下拉刷新的监听
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            //刷新
            @Override public void onRefreshBegin(PtrFrameLayout frame) {
                new MyPresenter(MainActivity.this).refresh();
            }
        });

    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("测试数据"+i);
        }
    }


    @Override
    public void setData(List<String> list) {
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {
        ptrFrameLayout.refreshComplete();
    }
}
