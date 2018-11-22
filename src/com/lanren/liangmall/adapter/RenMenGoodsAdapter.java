package com.lanren.liangmall.adapter;

import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.GoodsEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RenMenGoodsAdapter extends BaseAdapter {

	private List<GoodsEntity> JsonList;
	private Context context;

	public RenMenGoodsAdapter(Context context, List<GoodsEntity> JsonList) {
		this.context = context;
		this.JsonList = JsonList;
	}

	@Override
	public int getCount() {
		return JsonList.size();
	}

	@Override
	public Object getItem(int position) {
		return JsonList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		

		return convertView;
	}

	class RenMen {
		public SmartImageView img;
		public TextView goodsName, salesName, dName;
	}

}
