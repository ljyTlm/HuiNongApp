package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.FenLeiAdapter;
import com.lanren.liangmall.aty.fenlei.AtyNanKu;
import com.lanren.liangmall.aty.fenlei.AtyNanXie;
import com.lanren.liangmall.aty.fenlei.AtyNanZhuang;
import com.lanren.liangmall.aty.fenlei.AtyNvKu;
import com.lanren.liangmall.aty.fenlei.AtyNvXie;
import com.lanren.liangmall.aty.fenlei.AtyNvZhuang;
import com.lanren.liangmall.entity.FenLeiEntity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AtyFenLei extends Activity {
	
	private ListView fListView;
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
		
			super.handleMessage(msg);
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fenlei_itme);
		String[] txt = new String[]{"时尚男装","潮流女装","韩版男裤","休闲女裤","潮男皮靴","时尚中筒靴"};

		int[] images = new int[] { R.drawable.heiyurongfu, R.drawable.zhenzhishan, 
				R.drawable.nanniuzai,R.drawable.qianbiku, R.drawable.heipixue, R.drawable.zhongtongxue };
		//将数据放到集合中去
		List<FenLeiEntity> list = new  ArrayList<FenLeiEntity>();		
		for (int i = 0; i < txt.length; i++) {
			FenLeiEntity fe = new FenLeiEntity();
			fe.setImages(images[i]);
			fe.setName(txt[i]);
			list.add(fe);
		}
		Log.v("TAG", list.toString());
		//调用适配器展示数据
		FenLeiAdapter fa = new FenLeiAdapter(this, list);
		fListView = (ListView) findViewById(R.id.fenlei_lv);
		fListView.setAdapter(fa);
		fListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.v("TAG", "itme位置："+position);
				if (position==0) {
					Intent i = new Intent(AtyFenLei.this,AtyNanZhuang.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}else if (position==1) {
					Intent i = new Intent(AtyFenLei.this,AtyNvZhuang.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}else if (position==2) {
					Intent i = new Intent(AtyFenLei.this,AtyNanKu.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}else if (position==3) {
					Intent i = new Intent(AtyFenLei.this,AtyNvKu.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}else if (position==4) {
					Intent i = new Intent(AtyFenLei.this,AtyNanXie.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}else if (position==5) {
					Intent i = new Intent(AtyFenLei.this,AtyNvXie.class);
					Bundle bundle = new Bundle();
					bundle.putInt("lei", position+1);
					i.putExtras(bundle);
					startActivity(i);
				}
			}
		});
	}
	
	
}
