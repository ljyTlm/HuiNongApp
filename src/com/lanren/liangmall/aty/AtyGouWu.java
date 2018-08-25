package com.lanren.liangmall.aty;

import java.util.HashMap;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.GouWuAdapter;
import com.lanren.liangmall.dao.DataCar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class AtyGouWu extends Activity {
	
	private ListView listView;
	private GouWuAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gouwuche_itme);
		setSaveData();
		
		listView = (ListView) findViewById(R.id.gouwu_listview);
		if (DataCar.arrayList_cart!=null&&DataCar.arrayList_cart.size()!=0) {
			adapter = new GouWuAdapter(AtyGouWu.this, DataCar.arrayList_cart);
			listView.setAdapter(adapter);
		}
	}
	
	/**保存购物车的数据*/
	private void setSaveData(){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		SharedPreferences ps = getSharedPreferences("isGoods", Context.MODE_PRIVATE);
		int size = ps.getInt("ArrayCart_size", 0);
		for (int i = 0; i < size; i++) {
			hashMap.put("images", ps.getString("ArrayCart_img_"+i, ""));
			hashMap.put("name", ps.getString("ArrayCart_name_"+i, ""));
			hashMap.put("price", ps.getString("ArrayCart_price_"+i, ""));
			DataCar.arrayList_cart.add(hashMap);
		}
		Log.v("TAG", "获取保存购物车的数据："+hashMap.toString());
	}
	
	
}
