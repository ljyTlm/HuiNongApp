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
	 * 返回多少page
	 */
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	/**
	 * 判断当前滑动view等不等进来的对象
	 * 
	 * true: 表示不去创建，使用缓存 false:去重新创建 view： 当前滑动的view
	 * object：将要进入的新创建的view，由instantiateItem方法创建
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	/**
	 * 类似于BaseAdapger的getView方法 用了将数据设置给view 由于它最多就3个界面，不需要viewHolder
	 */

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = View.inflate(homeFragment, com.lanren.liangmall.R.layout.adapter_ad, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image);
		
		ViewPagerEntity ad = list.get(position % list.size());
		imageView.setImageResource(ad.getIconResId());
		
		container.addView(view);// 一定不能少，将view加入到viewPager中
		return view;
	}

	/**
	 * 销毁page position： 当前需要消耗第几个page object:当前需要消耗的page
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	
	
	

	
}
