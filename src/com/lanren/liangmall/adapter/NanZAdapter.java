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

public class NanZAdapter extends BaseAdapter{
	
	private List<GoodsEntity> JsonList;
	private Context context;
	public NanZAdapter(Context context,List<GoodsEntity> JsonList){
		this.context=context;
		this.JsonList=JsonList;
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
		NanZ n = null;
		if (convertView==null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.aty_getvip, null);
		    n = new NanZ();
		    
//		    n.img = (SmartImageView) convertView.findViewById(R.id.aty_fenlei_img_remen);
//		    n.goodsName = (TextView) convertView.findViewById(R.id.aty_fenlei_tv_name);
//		    n.dName = (TextView) convertView.findViewById(R.id.aty_fenlei_tv_dname);
//		    n.salesName = (TextView) convertView.findViewById(R.id.aty_fenlei_tv_sales);
		    convertView.setTag(n);
		}else {
			n = (NanZ) convertView.getTag();
						
		}
		GoodsEntity ge = JsonList.get(position);
		n.img.setImageUrl(NetHttpData.dataIp+"/LiAng/images/"+ge.getsDrawable());
		n.goodsName.setText(ge.getsName());
		n.dName.setText(ge.getsSeller());
		n.salesName.setText(ge.getsSales());
		return convertView;
	}
	
	class NanZ{
		public SmartImageView img ;
		public TextView goodsName,salesName,dName;
	}

}
