package com.lanren.liangmall.adapter;

import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.CommodityEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoreAdapter extends BaseAdapter {
	
	public List<CommodityEntity> list;
	public Context context;
	
	public StoreAdapter(List<CommodityEntity> list, Context context) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = (View)LayoutInflater.from(context).inflate(R.layout.aty_store_item, null);
		TextView tView1 = (TextView)view.findViewById(R.id.txt_name);
		TextView tView2 = (TextView)view.findViewById(R.id.txt_sellername);
		TextView tView3 = (TextView)view.findViewById(R.id.txt_price);
		tView1.setText(list.get(arg0).getName());
		tView2.setText(list.get(arg0).getSellername());
		tView3.setText(list.get(arg0).getPrice().toString());
		view.setTag(1, list.get(arg0).getId());
		view.setTag(2, arg0);
		return view;
	}

}
