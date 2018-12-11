package com.lanren.liangmall.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.CommodityEntity;
import com.lanren.liangmall.entity.ConsumptionEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class GouWuAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater mInflater;//����װ��������
	private List<ConsumptionEntity> list;
	/**
	 * Adapter���췽��
	 * 
	 */
	

	/**
	 * ��ʼ��Popupwindow
	 * 
	 * @param inflater
	 */
	private void initPopupWindow(LayoutInflater inflater2) {
		// TODO Auto-generated method stub
		
	}

	public GouWuAdapter (Context context, List<ConsumptionEntity> list) {
		mInflater = LayoutInflater.from(context);
		this.list = list;
	}
	/**
	 * ��ȡ����
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/**
	 * ��ȡItem����
	 */
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	/**
	 * ��ȡItem��ID
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = mInflater.inflate(R.layout.itme_car,null);
		TextView name = (TextView) view.findViewById(R.id.aty_gouwu_tv_name);
		TextView price = (TextView) view.findViewById(R.id.aty_gouwu_tv_price);
		view.setTag(position);
	    name.setText(list.get(position).getCtnname().toString());
	    price.setText(list.get(position).getMoney().toString());
		return view;
	}
}
