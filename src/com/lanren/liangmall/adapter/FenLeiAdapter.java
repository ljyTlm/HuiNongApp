package com.lanren.liangmall.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lanren.liangmall.R;
import com.lanren.liangmall.entity.FenLeiEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FenLeiAdapter extends BaseAdapter {

	private List<FenLeiEntity> list;
	private Context context;

	public FenLeiAdapter(Context context, List<FenLeiEntity> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		fenLei f = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_view_itme, null);

			f = new fenLei();
			f.img = (ImageView) convertView.findViewById(R.id.fenlei_img);
			f.name = (TextView) convertView.findViewById(R.id.fenlei_tv_name);
			convertView.setTag(f);
		} else {
			f = (fenLei) convertView.getTag();
		}

		FenLeiEntity fe = list.get(position);
		f.img.setImageResource(fe.getImages());
		f.name.setText(fe.getName());
		return convertView;
	}

	class fenLei {
		public ImageView img;
		public TextView name;
	}
}
