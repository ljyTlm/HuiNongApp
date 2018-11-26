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

	/** ͼƬװ��ģ��*/
	private ViewPager mViewPager;   
	/** ����װ��ģ�� */
	private TextView mIntroTv;
	/** ͼ�겼�� */
	private LinearLayout mDotLayout;
	/** ѭ����Ŀ�� */
	private ViewPagerAdapter mViewPagerAdapter; 
	/** װ��ѭ���б��ÿ������*/
	private List<ViewPagerEntity> list;
	/** ��ʱ�������� */
	private Handler mMyHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
			int currentPage = mViewPager.getCurrentItem() % list.size();
			mIntroTv.setText(list.get(currentPage).getIntro());
			for (int i = 0; i < mDotLayout.getChildCount(); i++) {
				mDotLayout.getChildAt(i).setEnabled(i == currentPage);// ����setEnabledΪtrue�Ļ�
				// ��ѡ��������ͻ��Ӧ��ʹ�ð�ɫ��ɫ
			}
			mMyHandler.sendEmptyMessageDelayed(0, 3000);
		}
	};
	
	/** ������ѯ�б� */
	private ListView newsListView;
	/** ��ѯ�б��ҳ�� */
	private int page = 1;
	/** ��ѯ�б�ģ�� */
	private NewsAdapter newsAdapter;
	/** װ��ÿ����ѯ���� */
	private List<NewsEntity> JsonList = new ArrayList<NewsEntity>();
	
	/** ҳ�洴��ʱ�������¼�*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_itme); //�ȳ�ʼ������ҳ�沼�� ����������ǽ�class���xml�ļ�����ӳ���ϵ
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mIntroTv = (TextView) findViewById(R.id.tv_intro);
		mDotLayout = (LinearLayout) findViewById(R.id.dot_layout);
		newsListView = (ListView) findViewById(R.id.zhuye_lv_remen);
		list = new ArrayList<ViewPagerEntity>();
		
		initData();
		initList();
		setOnClickListener();
	}
	
	/** ��ʼ��ѭ���б�����*/
	private void initData() {
		//ͼƬ���ִ���
		list.add(new ViewPagerEntity(R.drawable.img_home_banner1, "���������ַ���"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner2, "�ֹ������ʢ�Ϻ�����רҵ���� 20��"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner3, "�����������Ʋ����ֱ�ƴ����"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner5, "�ӱ���ɽ������ϲ�����"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner6, "������ġ���ũ������æ"));
		list.add(new ViewPagerEntity(R.drawable.img_home_banner7, "�½��׸�������ʹ�԰��԰"));
		
		//ͼ�괦��
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(8, 8); //����һ���Ӳ���
			if (i != 0) {
				params.leftMargin = 5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot); //ͨ��xml�ļ�����ͼ����ʽ
			mDotLayout.addView(view); //װ��ÿ��Сͼ��
		}
		
		mViewPagerAdapter = new ViewPagerAdapter(AtyHome.this, list);
		mViewPager.setAdapter(mViewPagerAdapter);  //��ʱ���Ϊ����������������

		// �ȳ�ʼ
		mViewPager.setCurrentItem(0);
		// 3�붨ʱ
		mMyHandler.sendEmptyMessageDelayed(0, 3000);
		// ����setEnabledΪtrue�Ļ�
		for (int i = 0; i < list.size(); i++) {
			mDotLayout.getChildAt(i).setEnabled(false);// ����setEnabledΪtrue�Ļ�
		}
		mDotLayout.getChildAt(0).setEnabled(true);// ����setEnabledΪtrue�Ļ�
		
	}
	
	/** ��ʼ��������Ѷ�б�*/
	public void initList() {
		
		NetHttpData.getHttpDao().getNews(new JsonHttpResponseHandler("utf-8"){
			
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(getApplicationContext(), "��Ǹ����������������", 1500);
				Log.v("TAG", "��������ʧ��");
				NewsEntity news = new NewsEntity();
				news.setTitle("��������ʧ�ܰ�");
				news.setImgName("error");
				news.setNewsUrl("https://www.baidu.com");
				JsonList.add(news);
				newsAdapter = new NewsAdapter(AtyHome.this, JsonList);
				newsListView.setAdapter(newsAdapter);
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.v("TAG", "�������ӳɹ�");
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
					Log.v("TAG", "��ȡJSON����ʧ��");
					e.printStackTrace();
				}
			}
			
		});
	}
	
	/** ͳһ���ü����¼�*/
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
