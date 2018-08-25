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
		RenMen rm = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.reme_home_itme, null);
			rm = new RenMen();

			rm.img = (SmartImageView) convertView.findViewById(R.id.zhuye_img_remen);
			rm.goodsName = (TextView) convertView.findViewById(R.id.zhuye_tv_name);
			rm.dName = (TextView) convertView.findViewById(R.id.zhuye_tv_dname);
			rm.salesName = (TextView) convertView.findViewById(R.id.zhuye_tv_sales);
			convertView.setTag(rm);
		} else {
			rm = (RenMen) convertView.getTag();
		}
		GoodsEntity ge = JsonList.get(position);
		rm.img.setImageUrl(NetHttpData.dataIp + "/LiAng/images/" + ge.getsDrawable());
		rm.goodsName.setText(ge.getsName());
		rm.dName.setText(ge.getsSeller());
		rm.salesName.setText(ge.getsSales());

		return convertView;
	}

	class RenMen {
		public SmartImageView img;
		public TextView goodsName, salesName, dName;
	}

}
