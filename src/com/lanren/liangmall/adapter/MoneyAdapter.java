package com.lanren.liangmall.adapter;


import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.ls.LSInput;

import com.lanren.liangmall.entity.ConsumptionEntity;
import com.lanren.liangmall.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MoneyAdapter extends BaseAdapter{

	private List<ConsumptionEntity> list;
	private Context context;
	private LayoutInflater mInflater;//布局装载器对象
	
	public MoneyAdapter(Context context, List<ConsumptionEntity> list) {
		this.list = list;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}
	
	
	@Override
	public int getCount() {
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
		View view = mInflater.inflate(R.layout.item_money, null);
		TextView name = (TextView)view.findViewById(R.id.txt_ctnname);
		TextView prict = (TextView)view.findViewById(R.id.txt_price);
		TextView date = (TextView)view.findViewById(R.id.txt_date);
		name.setText(list.get(arg0).getCtnname().toString());
		if ("充值".equals(list.get(arg0).getCtnname())) {
			prict.setText("+"+list.get(arg0).getMoney().toString());
		}else {
			prict.setText("-"+list.get(arg0).getMoney().toString());
		}
		date.setText(list.get(arg0).getDate().toString());
		return view;
	}
}
 