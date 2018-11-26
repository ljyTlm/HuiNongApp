package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.NewsAdapter;
import com.lanren.liangmall.adapter.RenMenGoodsAdapter;
import com.lanren.liangmall.adapter.ViewPagerAdapter;
import com.lanren.liangmall.aty.fenlei.AtyNvKu;
import com.lanren.liangmall.entity.GoodsEntity;
import com.lanren.liangmall.entity.NewsEntity;
import com.lanren.liangmall.entity.ViewPagerEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class AtyHome extends Activity {

	/** 图片装载模块*/
	private ViewPager mViewPager;   
	/** 文字装载模块 */
	private TextView mIntroTv;
	/** 图标布局 */
	private LinearLayout mDotLayout;
	/** 循环栏目区 */
	private ViewPagerAdapter mViewPagerAdapter; 
	/** 装载循环列表的每个对象*/
	private List<ViewPagerEntity> list;
	/** 定时任务处理器 */
	private Handler mMyHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
			int currentPage = mViewPager.getCurrentItem() % list.size();
			mIntroTv.setText(list.get(currentPage).getIntro());
			for (int i = 0; i < mDotLayout.getChildCount(); i++) {
				mDotLayout.getChildAt(i).setEnabled(i == currentPage);// 设置setEnabled为true的话
				// 在选择器里面就会对应的使用白色颜色
			}
			mMyHandler.sendEmptyMessageDelayed(0, 3000);
		}
	};
	
	/** 最新咨询列表 */
	private ListView newsListView;
	/** 咨询列表的页码 */
	private int page = 1;
	/** 咨询列表模块 */
	private NewsAdapter newsAdapter;
	/** 装载每条咨询对象 */
	private List<NewsEntity> JsonList = new ArrayList<NewsEntity>();
	
	/** 页面创建时发生的事件*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_itme); //先初始化整个页面布局 这个东西就是将class类和xml文件建立映射关系
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mIntroTv = (TextView) findViewById(R.id.tv_intro);
		mDotLayout = (LinearLayout) findViewById(R.id.dot_layout);
		newsListView = (ListView) findViewById(R.id.zhuye_lv_remen);
		list = new ArrayList<ViewPagerEntity>();
		
		initData();
		initList();
		setOnClickListener();
	}
	
	/** 初始化循环列表数据*/
	private void initData() {
		//图片文字处理
		list.add(new ViewPagerEntity(R.drawable.img_home_banner1, "金秋娃娃乐丰收"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner2, "种龟大王“盛老汉”：专业养龟 20年"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner3, "福建福鼎：制茶能手比拼技艺"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner5, "河北唐山：生姜喜获丰收"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner6, "“北大荒”大农机抢收忙"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner7, "新疆首个世界地质公园开园"));
		
		//图标处理
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(8, 8); //这是一个子布局
			if (i != 0) {
				params.leftMargin = 5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot); //通过xml文件设置图标样式
			mDotLayout.addView(view); //装载每个小图标
		}
		
		mViewPagerAdapter = new ViewPagerAdapter(AtyHome.this, list);
		mViewPager.setAdapter(mViewPagerAdapter);  //暂时理解为设置他的适配器吧

		// 先初始
		mViewPager.setCurrentItem(0);
		// 3秒定时
		mMyHandler.sendEmptyMessageDelayed(0, 3000);
		// 设置setEnabled为true的话
		for (int i = 0; i < list.size(); i++) {
			mDotLayout.getChildAt(i).setEnabled(false);// 设置setEnabled为true的话
		}
		mDotLayout.getChildAt(0).setEnabled(true);// 设置setEnabled为true的话
		
	}
	
	/** 初始化最新资讯列表*/
	public void initList() {
		
		NetHttpData.getHttpDao().getNews(new JsonHttpResponseHandler("utf-8"){
			
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(getApplicationContext(), "抱歉！请重新连接网络", 1500);
				Log.v("TAG", "网络连接失败");
				NewsEntity news = new NewsEntity();
				news.setTitle("网络连接失败啊");
				news.setImgName("error");
				news.setNewsUrl("https://www.baidu.com");
				JsonList.add(news);
				newsAdapter = new NewsAdapter(AtyHome.this, JsonList);
				newsListView.setAdapter(newsAdapter);
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.v("TAG", "网络连接成功");
				try {
					JSONArray array =response.getJSONArray("result");
					for (int i = 0; i < array.length(); i++) {
						JSONObject datas = array.getJSONObject(i);
						NewsEntity news = new NewsEntity();
						news.setTitle(datas.optString("newsTitle"));
						news.setImgName(datas.optString("imgName"));
						news.setNewsUrl(datas.optString("newsUrl"));
						JsonList.add(news);
					}
					newsAdapter = new NewsAdapter(AtyHome.this, JsonList);
					newsListView.setAdapter(newsAdapter);
					
				} catch (JSONException e) {
					Log.v("TAG", "获取JSON数据失败");
					e.printStackTrace();
				}
			}
			
		});
	}
	
	/** 统一设置监听事件*/
	public void setOnClickListener(){
		mViewPager.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		newsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				NewsEntity news = JsonList.get(position);
				Intent intent = new Intent(AtyHome.this, AtyWebView.class);
				intent.putExtra("url", news.getNewsUrl());
				startActivity(intent);
			}
		});
	}
	

}
