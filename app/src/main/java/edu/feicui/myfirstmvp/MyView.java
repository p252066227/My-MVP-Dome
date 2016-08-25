package edu.feicui.myfirstmvp;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public interface MyView {

    void setData(List<String> list);

    void showRefresh();

    void hideRefresh();

}
