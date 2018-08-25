package com.lanren.liangmall.adapter;

import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.ViewPagerEntity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter{

	private List<ViewPagerEntity> list;
	private Context homeFragment;
	
	public ViewPagerAdapter(Context homeFragment,List<ViewPagerEntity> list){
		this.homeFragment=homeFragment;
		this.list=list;
	}
	
	/**
	 * ���ض���page
	 */
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	/**
	 * �жϵ�ǰ����view�Ȳ��Ƚ����Ķ���
	 * 
	 * true: ��ʾ��ȥ������ʹ�û��� false:ȥ���´��� view�� ��ǰ������view
	 * object����Ҫ������´�����view����instantiateItem��������
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	/**
	 * ������BaseAdapger��getView���� ���˽��������ø�view ����������3�����棬����ҪviewHolder
	 */

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = View.inflate(homeFragment, com.lanren.liangmall.R.layout.adapter_ad, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		
		ViewPagerEntity ad = list.get(position % list.size());
		imageView.setImageResource(ad.getIconResId());
		
		container.addView(view);// һ�������٣���view���뵽viewPager��
		return view;
	}

	/**
	 * ����page position�� ��ǰ��Ҫ���ĵڼ���page object:��ǰ��Ҫ���ĵ�page
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	
	
	

	
}
