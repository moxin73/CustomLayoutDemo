package com.test.height.views;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

/***
 * 不滑动的GridView，被嵌套时使用
 *
 * @author Administrator
 */
public class NotScollGridView extends GridView {
    public NotScollGridView(android.content.Context context,
                            android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        Log.e("NotScollGridView", expandSpec + "");
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        getParent().requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

}