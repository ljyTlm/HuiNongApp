package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.FenLeiAdapter;
import com.lanren.liangmall.entity.FenLeiEntity;
import com.lanren.liangmall.net.NetHttpData;

import android.R.integer;
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
	
	final String[] txt = new String[]{"最新农业产品行情","在线农业产品交易","更多功能敬请期待"};
	final String [] url = {NetHttpData.dataIp+"/HuiNong/home/data.html", "",NetHttpData.dataIp+"/HuiNong/home/help.html"};
	final Class<?>[] cls = {AtyWebView.class, AtyStore.class, AtyWebView.class};
	final int[] images = new int[] { R.drawable.hangqing, R.drawable.jiaoyi, R.drawable.zixun,R.drawable.jishu};
	private ListView fListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_fenlei);
		
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
				Intent intent = new Intent(AtyFenLei.this, cls[position]);
				intent.putExtra("url", url[position]);
				startActivity(intent);
			}
		});
	}
	
	
}
