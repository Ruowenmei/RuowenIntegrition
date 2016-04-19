package com.ruowen.listviewdemo.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruowen.listviewdemo.R;
import com.ruowen.listviewdemo.bean.HotTitleBean;
import com.ruowen.listviewdemo.bean.TopTitleBean;
import com.ruowen.listviewdemo.bean.base.BaseBean;
import com.ruowen.listviewdemo.bean.JobGuessLikeBean;
import com.ruowen.listviewdemo.bean.base.TypeEnum;
import com.ruowen.listviewdemo.util.DpUtils;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private List<BaseBean> dataList = null;
    private int type;
    private int blueColor;
    private int yellowColor;
    
    public ListViewAdapter(Context mContext, List<BaseBean> dataList) {
        super();
        this.inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.dataList = dataList;
        blueColor = mContext.getResources().getColor(R.color.like_item_welfare_blue);
        yellowColor = mContext.getResources().getColor(R.color.like_item_welfare_yellow);
    }

    @Override
    public int getCount() {
        if (null == dataList){
            return 0;
        }

        return dataList.size();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        type = getItemViewType(position);
        switch (type) {
            case TypeEnum.DIVIDER:{
                if (null == convertView) {
                    convertView = inflater.inflate(R.layout.divider, null);
                }
            }
            break;
            case TypeEnum.HOT_TITLE:{
                HotTitleHolder hotTitleHolder;
                if (null == convertView){
                    hotTitleHolder = new HotTitleHolder();
                    convertView = inflater.inflate(R.layout.hot_title, null);
                    hotTitleHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(hotTitleHolder);

                }else {
                    hotTitleHolder = (HotTitleHolder)convertView.getTag();
                }

                HotTitleBean hotTitleBean = (HotTitleBean)dataList.get(position);
                hotTitleHolder.tvTitle.setText(hotTitleBean.getHotTitle());
            }
            break;
            case TypeEnum.HOT_ITEM: {
                HotItemHolder hotItemHolder;
                if (null == convertView) {
                    hotItemHolder = new HotItemHolder();
                    convertView = inflater.inflate(R.layout.hot_item, null);
                    hotItemHolder.tvJobName = (TextView) convertView.findViewById(R.id.tv_job_name);
                    hotItemHolder.tvDes = (TextView) convertView.findViewById(R.id.tv_description);
                    hotItemHolder.tvSalary = (TextView) convertView.findViewById(R.id.tv_salary);
                    hotItemHolder.tvYuan = (TextView) convertView.findViewById(R.id.tv_yuan);
                    hotItemHolder.llWelfare = (LinearLayout) convertView.findViewById(R.id.ll_welfare);
                    convertView.setTag(hotItemHolder);
                }else{
                    hotItemHolder = (HotItemHolder) convertView.getTag();
                }

                final JobGuessLikeBean jobGuessLikeBean = (JobGuessLikeBean) dataList.get(position);
                hotItemHolder.tvJobName.setText(jobGuessLikeBean.getTitle());
                if (jobGuessLikeBean.getLocation().length() > 14) {
                    hotItemHolder.tvDes.setText(jobGuessLikeBean.getLocation().substring(0, 13) + "...");
                } else {
                    hotItemHolder.tvDes.setText(jobGuessLikeBean.getLocation());
                }
                hotItemHolder.tvSalary.setText(jobGuessLikeBean.getLabel());
                addWelfare(hotItemHolder.llWelfare, jobGuessLikeBean);

                if ("面议".equals(jobGuessLikeBean.getLabel())) {
                    hotItemHolder.tvYuan.setVisibility(View.GONE);
                } else {
                    hotItemHolder.tvYuan.setVisibility(View.VISIBLE);
                }
            }
            break;
            case TypeEnum.TOP_TITLE:{
                TopTitleHolder topTitleHolder;
                if (null == convertView){
                    topTitleHolder = new TopTitleHolder();
                    convertView = inflater.inflate(R.layout.top_title, null);
                    topTitleHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(topTitleHolder);

                }else {
                    topTitleHolder = (TopTitleHolder) convertView.getTag();
                }

                TopTitleBean topTitleBean = (TopTitleBean) dataList.get(position);
                topTitleHolder.tvTitle.setText(topTitleBean.getTopTitle());
            }
            break;
        }

        return convertView;
    }

    /**
     * 添加福利
     */
    private void addWelfare(final LinearLayout llWelfare, JobGuessLikeBean jobGuessLikeBean){
        llWelfare.removeAllViews();

        int count = 0;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, DpUtils.dip2px(mContext, 3), 0);

        List<String> recReason = jobGuessLikeBean.getRecReason();
        if (null != recReason) {
            int iReasonLen = recReason.size();
            for (int i = 0; i < iReasonLen; i++) {
                if (count < 3) {
                    TextView tvView = (TextView) inflater.inflate(R.layout.hot_item_label, null);
                    String reason = dealString(recReason.get(i));
                    tvView.setText(reason);
                    tvView.setTextColor(blueColor);
                    tvView.setBackgroundResource(R.drawable.guess_like_item_label_bg);
                    GradientDrawable gd = (GradientDrawable) tvView.getBackground();
                    gd.setStroke(1, blueColor);//动态设置shape stroke的宽度和颜色
                    tvView.setLayoutParams(lp);
                    llWelfare.addView(tvView);
                    count++;
                }else if ((3==count) && (4==iReasonLen)){
                    TextView tvView = (TextView) inflater.inflate(R.layout.hot_item_label, null);
                    String reason = dealString(recReason.get(i));
                    tvView.setText(reason);
                    tvView.setTextColor(blueColor);
                    tvView.setBackgroundResource(R.drawable.guess_like_item_label_bg);
                    GradientDrawable gd = (GradientDrawable) tvView.getBackground();
                    gd.setStroke(1, blueColor);//动态设置shape stroke的宽度和颜色
                    tvView.setLayoutParams(lp);
                    llWelfare.addView(tvView);
                    return;
                }else if ((3==count) && (iReasonLen>4)){
                    ImageView ivView = new ImageView(mContext);
                    ivView.setBackgroundResource(R.drawable.ellipsis);
                    ivView.setBaselineAlignBottom(true);
                    llWelfare.addView(ivView);
                    return;
                }
            }
        }

        List<String> welfares = jobGuessLikeBean.getWelfares();
        if (null != welfares) {
            int iWelfare = welfares.size();
            for (int i = 0; i < iWelfare; i++) {
                if (count < 3) {
                    TextView tvView = (TextView) inflater.inflate(R.layout.hot_item_label, null);
                    String welfare = dealString(welfares.get(i));
                    tvView.setText(welfare);
                    tvView.setTextColor(yellowColor);
                    tvView.setBackgroundResource(R.drawable.guess_like_item_label_bg);
                    GradientDrawable gd = (GradientDrawable) tvView.getBackground();
                    gd.setStroke(1, yellowColor);//动态设置shape stroke的宽度和颜色
                    tvView.setLayoutParams(lp);
                    llWelfare.addView(tvView);
                    count++;
                }else if((3==count) && (1==(iWelfare-i))){
                    TextView tvView = (TextView) inflater.inflate(R.layout.hot_item_label, null);
                    String welfare = dealString(welfares.get(i));
                    tvView.setText(welfare);
                    tvView.setTextColor(yellowColor);
                    tvView.setBackgroundResource(R.drawable.guess_like_item_label_bg);
                    GradientDrawable gd = (GradientDrawable) tvView.getBackground();
                    gd.setStroke(1, yellowColor);//动态设置shape stroke的宽度和颜色
                    tvView.setLayoutParams(lp);
                    llWelfare.addView(tvView);
                    return;
                }else if ((3==count) && (iWelfare-i>1)){
                    ImageView ivView = new ImageView(mContext);
                    ivView.setBackgroundResource(R.drawable.ellipsis);
                    ivView.setBaselineAlignBottom(true);
                    llWelfare.addView(ivView);
                    return;
                }
            }
        }
    }

    /**
     * 处理字符串，如果长度大于4，截成4
     * @param s
     */
    private String dealString(String s){
        String newS = s;

        int iLen = s.length();
        if (iLen > 4){
            newS = s.substring(0, 3)+"...";
        }

        return newS;
    }

    class HotTitleHolder {
        TextView tvTitle;
    }

    class HotItemHolder {
        TextView tvJobName;
        TextView tvDes;
        TextView tvSalary;
        TextView tvYuan;
        LinearLayout llWelfare;
    }

    class TopTitleHolder {
        TextView tvTitle;
    }
}
