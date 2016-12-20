package com.test.height;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test.height.bean.GridBean;
import com.test.height.views.GridItemLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ll_grid)
    LinearLayout gridLayout;
    @Bind(R.id.tag_desc)
    TextView tvTagDesc;

    /** 上次选中索引 */
    private int lastTagPosition = -1;
    /** 活动标签的列数 */
    private int activityTagColoumn = 2;

    private ArrayList<GridBean> gridBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    private void init(){
        gridBeanList = new ArrayList<GridBean>();

        GridBean bean = new GridBean();
        bean.name = "超级长的标题栏目一啊啊啊啊";
        bean.desc = "超级长的标题栏目一描述信息";
        gridBeanList.add(bean);
        GridBean bean1 = new GridBean();
        bean1.name = "正常的标题栏目二";
        bean1.desc = "正常的标题栏目一描述信息";
        gridBeanList.add(bean1);
        GridBean bean2 = new GridBean();
        bean2.name = "标题栏目三";
        bean2.desc = "标题栏目三的描述信息哟";
        gridBeanList.add(bean2);
        GridBean bean3 = new GridBean();
        bean3.name = "标题栏目四有还有呢";
        bean3.desc = "标题栏目四的描述信息哟";
        gridBeanList.add(bean3);
        GridBean bean4 = new GridBean();
        bean4.name = "标题栏目五";
        bean4.desc = "标题栏目五的描述信息哟";
        gridBeanList.add(bean4);
        GridBean bean5 = new GridBean();
        bean5.name = "标题栏目六六六大顺";
        bean5.desc = "标题栏目六六六大顺的描述信息哟";
        gridBeanList.add(bean5);
        GridBean bean6 = new GridBean();
        bean6.name = "标题栏目7⃣️戚戚凄凄";
        bean6.desc = "标题栏目7⃣️的描述信息哟凄凄惨惨戚戚";
        gridBeanList.add(bean6);

        setActivityTagDatas();

    }

    private void setActivityTagDatas() {
        gridLayout.removeAllViews();
        int row = 0;// 计算需要行数
        if ((gridBeanList.size() % activityTagColoumn) == 0) {
            row = gridBeanList.size() / activityTagColoumn;
        } else {
            row = (gridBeanList.size() / activityTagColoumn) + 1;
        }

        for (int i = 0; i < row; i++) {
            LinearLayout rowLayout = new LinearLayout(MainActivity.this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setWeightSum(activityTagColoumn);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            rowLayout.setLayoutParams(params);

            addActivityTag(i, rowLayout);

            gridLayout.addView(rowLayout);
        }
    }

    /**
     * 添加每行数据
     * @param row 行
     * @param layout
     */
    private void addActivityTag(int row, LinearLayout layout) {
        for (int i = 0; i < activityTagColoumn; i++) {
            final int position = (row * activityTagColoumn + i);
            if (position < gridBeanList.size()) {
                GridItemLayout tagLayout = new GridItemLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                params.bottomMargin = 10;
                tagLayout.setData(gridBeanList.get(position));
                tagLayout.setLayoutParams(params);
                tagLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridBeanList.get(position).isSelected = !gridBeanList.get(position).isSelected;

                        if (lastTagPosition != -1 && lastTagPosition != position) {
                            gridBeanList.get(lastTagPosition).isSelected = false;
                        }
                        resetTagSelected(position);

                        if (gridBeanList.get(position).isSelected) {
                            if (tvTagDesc.getVisibility() == View.INVISIBLE) {
                                tvTagDesc.setVisibility(View.VISIBLE);
                            }
                            if (!TextUtils.isEmpty(gridBeanList.get(position).desc)) {
                                tvTagDesc.setText(gridBeanList.get(position).desc);
                            } else {
                                tvTagDesc.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            tvTagDesc.setVisibility(View.INVISIBLE);
                        }

                        lastTagPosition = position;
                    }
                });
                layout.addView(tagLayout);
            }
        }
    }

    /**
     * 重置选中状态
     */
    private void resetTagSelected(final int selectIndex) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            LinearLayout rowLayout = (LinearLayout) gridLayout.getChildAt(i);
            for (int k = 0; k < rowLayout.getChildCount(); k++) {
                final GridItemLayout tagLayout = (GridItemLayout) rowLayout
                        .getChildAt(k);
                if ((i * activityTagColoumn + k) == selectIndex) {
                    Log.d("LLL", "selectIndex="+selectIndex);
                    tagLayout.resetTagSelect(gridBeanList.get(selectIndex).isSelected);
                } else {
                    tagLayout.resetTagSelect(false);
                }
            }
        }
    }

}
