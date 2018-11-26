package com.lanren.liangmall.adapter;

import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.NewsEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	 private List<NewsEntity> newsList;//数据源
	 private LayoutInflater mInflater;//布局装载器对象
	 
	 // 通过构造方法将数据源与数据适配器关联起来
	 // context:要使用当前的Adapter的界面对象
	 public NewsAdapter (Context context, List<NewsEntity> list) {
        newsList = list;
        mInflater = LayoutInflater.from(context);
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		//将布局文件转化为View对象
        View view = mInflater.inflate(R.layout.news_home_itme,null);

        /**
         * 找到item布局文件中对应的控件 
         */
        SmartImageView imageView = (SmartImageView) view.findViewById(R.id.news_imge);
        TextView titleTextView = (TextView) view.findViewById(R.id.news_title);

        //获取相应索引的ItemBean对象
        NewsEntity news = newsList.get(position);

        /**
         * 设置控件的对应属性值 
         */
        imageView.setImageUrl(NetHttpData.dataIp+ "/HuiNong/imgs/" + news.getImgName());
        titleTextView.setTextSize(18);
        titleTextView.setText(news.getTitle());

        return view;
	}

}
