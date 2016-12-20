package com.test.height.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.height.R;
import com.test.height.bean.GridBean;

/**
 * Created by liuxinxin on 16/12/20.
 */
public class GridItemLayout extends LinearLayout {

    private Context context;
    private LayoutInflater mInflater;
    private RelativeLayout tagLayout;
    private TextView mTitle;
    private ImageView mSelectImg;

    public GridItemLayout(Context context) {
        super(context);
        this.context = context;
        mInflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        View view = mInflater.inflate(R.layout.item_grid_tag_layout, null);
        tagLayout = (RelativeLayout) view.findViewById(R.id.rl_tag_layout);
        mTitle = (TextView) view.findViewById(R.id.txt_title);
        mSelectImg = (ImageView) view.findViewById(R.id.img_selected);

        this.addView(view);
    }

    /**
     * 给当前Layout设置数据
     *
     * @param bean
     */
    public void setData(GridBean bean) {
        mTitle.setText(bean.name);
        if (bean.isSelected) {
            mSelectImg.setVisibility(View.VISIBLE);
			mTitle.setTextColor(context.getResources().getColor(
					R.color.WhiteColor));
        } else {
            mSelectImg.setVisibility(View.GONE);
			tagLayout.setBackgroundResource(R.drawable.yuanjiao_blue_rect_line);
			mTitle.setTextColor(context.getResources().getColor(
					R.color.activity_tag_color));
        }
    }

    /**
     * 根据是否选中刷新UI
     *
     * @param isSelected
     */
    public void resetTagSelect(boolean isSelected) {
        if (isSelected) {
            mSelectImg.setVisibility(View.VISIBLE);
			tagLayout.setBackgroundResource(R.drawable.yuanjiao_blue_rect_bg);
			mTitle.setTextColor(context.getResources().getColor(
					R.color.WhiteColor));
        } else {
            mSelectImg.setVisibility(View.GONE);
			tagLayout.setBackgroundResource(R.drawable.yuanjiao_blue_rect_line);
			mTitle.setTextColor(context.getResources().getColor(
					R.color.activity_tag_color));
        }
    }

}
