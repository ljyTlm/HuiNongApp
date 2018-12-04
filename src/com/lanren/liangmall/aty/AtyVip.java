package com.lanren.liangmall.aty;

import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONObject;

import com.lanren.liangmall.MainActivity;
import com.lanren.liangmall.R;
import com.lanren.liangmall.dao.DataCar;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	private static TextView gift1;
	private static TextView gift2;
	private static TextView gift3;
	private static Button but1;
	private static Button but2;
	private static Button but3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_vip);
		score = (TextView)findViewById(R.id.txt_score);
		gift1 = (TextView)findViewById(R.id.txt_gift1);
		gift2 = (TextView)findViewById(R.id.txt_gift2);
		gift3 = (TextView)findViewById(R.id.txt_gift3);
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
	}

	private void getScore() {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String username = ps.getString("username", "");
		NetHttpData.getHttpDao().getVipScore(username, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyVip.this, "网络连接失败！"+errorResponse, 1).show();
				score.setText(0+"");
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("score");
				score.setText(status+"");
			}

		});
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String username = ps.getString("username", "");
		
	}
}
		
		
	

	

