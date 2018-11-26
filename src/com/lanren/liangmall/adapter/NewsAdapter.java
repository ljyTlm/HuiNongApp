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

	 private List<NewsEntity> newsList;//����Դ
	 private LayoutInflater mInflater;//����װ��������
	 
	 // ͨ�����췽��������Դ��������������������
	 // context:Ҫʹ�õ�ǰ��Adapter�Ľ������
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
		//�������ļ�ת��ΪView����
        View view = mInflater.inflate(R.layout.news_home_itme,null);

        /**
         * �ҵ�item�����ļ��ж�Ӧ�Ŀؼ� 
         */
        SmartImageView imageView = (SmartImageView) view.findViewById(R.id.news_imge);
        TextView titleTextView = (TextView) view.findViewById(R.id.news_title);

        //��ȡ��Ӧ������ItemBean����
        NewsEntity news = newsList.get(position);

        /**
         * ���ÿؼ��Ķ�Ӧ����ֵ 
         */
        imageView.setImageUrl(NetHttpData.dataIp+ "/HuiNong/imgs/" + news.getImgName());
        titleTextView.setText(news.getTitle());

        return view;
	}

}
