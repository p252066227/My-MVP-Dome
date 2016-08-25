package edu.feicui.myfirstmvp;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyPresenter {

    private MyView refreshView;
    private List<String> list;

    public MyPresenter(MyView refreshView) {
        this.refreshView = refreshView;
        list = new ArrayList<>();
    }

    public void refresh(){
        //显示刷新
        new Refresh().execute();
    }

    class Refresh extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < 20; i++) {
                list.add("刷新出来的数据"+i);
            }
            refreshView.setData(list);
            //隐藏刷新
            refreshView.hideRefresh();
        }
    }

}
