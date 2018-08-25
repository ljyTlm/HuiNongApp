package com.lanren.liangmall.aty;

import java.util.HashMap;

import com.lanren.liangmall.MainActivity;
import com.lanren.liangmall.R;
import com.lanren.liangmall.dao.DataCar;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AtyXiangQing extends FragmentActivity{
	
	public boolean str;
	public String sts="1";
	private SmartImageView img;
	private ImageView add,mai;
	private TextView Name,Price,Addr,Sales;
	private String sImg,sName,sPrice,sAddr,sSales;

	
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_xiangqing);
		quUser();
		Log.v("TAG", "���ﳵ״̬�룺"+str);
		Intent i = getIntent();
		Bundle b = i.getExtras();		
		sImg = b.getString("img");
		sName = b.getString("name");
		sPrice = b.getString("price");
		sAddr = b.getString("addr");
		sSales = b.getString("sales");
		
		getFindId();
		
		img.setImageUrl(NetHttpData.dataIp+"/LiAng/images/"+sImg);
		Name.setText(sName);
		Price.setText(sPrice+" Ԫ");
		Addr.setText(sAddr);
		Sales.setText(sSales);
		
		
	}
		@Override
		protected void onRestart() {
			super.onRestart();
			quUser();
		}
		public void getFindId(){
			img = (SmartImageView) findViewById(R.id.xiang_iv_baby);
			Name = (TextView) findViewById(R.id.xiang_txt_name);
			Price = (TextView) findViewById(R.id.xiang_txt_price);
			Addr = (TextView) findViewById(R.id.xiang_txt_addr);
			Sales = (TextView) findViewById(R.id.xiang_txt_sales);
			
			add = (ImageView) findViewById(R.id.xiang_put_in);
			mai = (ImageView) findViewById(R.id.xiang_buy_now);
		}
		
		public void MyOnClick(View v){
			if (v.getId()==R.id.xiang_put_in) {
				if (str) {			
					Intent i = new Intent(AtyXiangQing.this,MainActivity.class);
					startActivity(i);
					getGoods();
					saveGoods();
					Toast.makeText(AtyXiangQing.this, "�ɹ����빺�ﳵ", 1).show();
				}else {
					Log.v("TAG", "���¼");
					Intent i = new Intent(AtyXiangQing.this,AtyLogin.class);
					startActivity(i);
			}
			}else if (v.getId()==R.id.xiang_buy_now) {
				Toast.makeText(AtyXiangQing.this, "�˷���δ����", 1).show();
			}
			
			
		}
		

       //��ȡ��¼״̬��
		public void quUser(){
			SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
			str = ps.getBoolean("isLogin", false);
		}
		
		//��ȡ��Ʒ����
		public void getGoods(){
			HashMap<String, Object> allHashMap=new HashMap<String,Object>();
			allHashMap.put("images", sImg);
			allHashMap.put("name", sName);
			allHashMap.put("price", sPrice);
			allHashMap.put("id", DataCar.arrayList_cart_id+1);
			
			DataCar.arrayList_cart.add(allHashMap);
			Log.v("TAG", "������Ʒ����HashMap��"+allHashMap.toString());
		}
		
		//������Ʒ����
		public void saveGoods(){
			SharedPreferences ps = getSharedPreferences("isGoods", Context.MODE_PRIVATE);
			Editor ed = ps.edit();
			ed.putInt("ArrayCart_size", DataCar.arrayList_cart.size());
			for (int i = 0; i < DataCar.arrayList_cart.size(); i++) {
				ed.remove("ArrayCart_img_"+i);
				ed.remove("ArrayCart_name_"+i);
				ed.remove("ArrayCart_price_"+i);
				ed.putString("ArrayCart_img_"+i, DataCar.arrayList_cart.get(i).get("images").toString());
				ed.putString("ArrayCart_img_"+i, DataCar.arrayList_cart.get(i).get("name").toString());
				ed.putString("ArrayCart_img_"+i, DataCar.arrayList_cart.get(i).get("price").toString());
			}
			Log.v("TAG", "������Ʒ���ݣ�"+ps.toString());
		}
					
		}
		
		
	

	

