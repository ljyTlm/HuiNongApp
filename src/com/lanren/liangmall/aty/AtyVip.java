package com.lanren.liangmall.aty;

import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lanren.liangmall.MainActivity;
import com.lanren.liangmall.R;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import android.R.integer;
import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AtyVip extends FragmentActivity implements OnClickListener{
	
	private static TextView score;
	private static TextView i_gift1;
	private static TextView i_gift2;
	private static TextView i_gift3;
	private static TextView t_gift1;
	private static TextView t_gift2;
	private static TextView t_gift3;
	private static Button but1;
	private static Button but2;
	private static Button but3;
	private static int vipScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_vip);
		score = (TextView)findViewById(R.id.txt_score);
		i_gift1 = (TextView)findViewById(R.id.img_gift1);
		i_gift2 = (TextView)findViewById(R.id.img_gift2);
		i_gift3 = (TextView)findViewById(R.id.img_gift3);
		t_gift1 = (TextView)findViewById(R.id.txt_gift1);
		t_gift2 = (TextView)findViewById(R.id.txt_gift2);
		t_gift3 = (TextView)findViewById(R.id.txt_gift3);
		but1 = (Button)findViewById(R.id.get_gift1);
		but2 = (Button)findViewById(R.id.get_gift2);
		but3 = (Button)findViewById(R.id.get_gift3);
		but1.setOnClickListener(this);
		but2.setOnClickListener(this);
		but3.setOnClickListener(this);
		init();
	}
		
	private void init() {
		// TODO Auto-generated method stub
		getScore();
		getGifts();
	}

	private void getGifts() {
		// TODO Auto-generated method stub
		NetHttpData.getHttpDao().getVipGifts(new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyVip.this, "网络连接失败！"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@SuppressLint("NewApi") @Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					JSONArray jsonArray = response.getJSONArray("gifts");
					setData(jsonArray.getJSONObject(0), t_gift1); 
					setData(jsonArray.getJSONObject(1), t_gift2); 
					setData(jsonArray.getJSONObject(2), t_gift3); 
				} catch (Exception e) {
					// TODO: handle exception
				}
				i_gift1.setBackground(getResources().getDrawable(R.drawable.ic_ricecoker));
				i_gift2.setBackground(getResources().getDrawable(R.drawable.ic_bedthree));
				i_gift3.setBackground(getResources().getDrawable(R.drawable.ic_xiaomi));
			}

			private void setData(JSONObject jsonObject, TextView t_gift1) {
				// TODO Auto-generated method stub
				String name = jsonObject.optString("name");
				Integer giftScore = jsonObject.optInt("score");
				t_gift1.setText(name+" 所需积分:"+giftScore);
			}

		});
	}

	private void getScore() {
		// TODO Auto-generated method stub
		NetHttpData.getHttpDao().getVipScore(getUsername(), new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyVip.this, "网络连接失败！"+errorResponse, 1).show();
				score.setText(0+"");
				vipScore = 0;
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				vipScore = response.optInt("score");
				score.setText(vipScore+"");
			}

		});
	}

	private String getUsername() {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String username = ps.getString("username", "");
		return username;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String username = ps.getString("username", "");
		int id = 0;
		if (view.equals(but1)) {
			id = 1;
		}
		if (view.equals(but2)) {
			id = 2;
		}
		if (view.equals(but3)) {
			id = 3;
		}
		NetHttpData.getHttpDao().getGiftByScore(id, username, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyVip.this, "网络连接失败！"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("status");
				switch (status) {
				case 0:
					Toast.makeText(AtyVip.this, "库存不足！", Toast.LENGTH_LONG).show();
					break;
				case 1:
					Toast.makeText(AtyVip.this, "积分不足", Toast.LENGTH_LONG).show();
					break;
				case 2:
					getScore();
					Toast.makeText(AtyVip.this, "兑换成功， 请等待系统通知！", Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
			}

		});
	}
}
		
		
	

	

