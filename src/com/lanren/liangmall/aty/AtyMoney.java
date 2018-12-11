package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.MoneyAdapter;
import com.lanren.liangmall.entity.ConsumptionEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AtyMoney extends FragmentActivity implements OnClickListener{

	private String username;
	private TextView money;
	private Button but;
	private ListView listView;
	private List<ConsumptionEntity> list;
	private MoneyAdapter adapter;
	
	final Integer MONRY = 0;
	final Integer RECORE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_money);
		money = (TextView)findViewById(R.id.txt_money);
		but = (Button)findViewById(R.id.but_money);
		listView = (ListView)findViewById(R.id.listView_ctn);
		list = new ArrayList<ConsumptionEntity>();
		username = getUsername();
		
		init();
		setOnClick();
		
//		ConsumptionEntity ctn = new ConsumptionEntity();
//		ctn.setCommodityId(2);
//		ctn.setCtnname("ceshi");
//		ctn.setDate("2018");
//		ctn.setId(3);
//		ctn.setStatus(0);
//		ctn.setUsername("lijinyu");
//		list.add(ctn);
//		adapter = new MoneyAdapter(AtyMoney.this, list);
//		listView.setAdapter(adapter);
		
	}
	
	private void setOnClick() {
		// TODO Auto-generated method stub
		but.setOnClickListener(this);
	}

	/**初始化用户姓名*/
	private String getUsername() {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String name = ps.getString("username", "");
		return name;
	}

	private void init() {
		// TODO Auto-generated method stub
		NetHttpData.getHttpDao().getMoney(username, MONRY, new JsonHttpResponseHandler(){
			@Override
			public void onFailure(int statusCode, Header[] headers,String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseString, throwable);
				Toast.makeText(AtyMoney.this, "网络连接失败", Toast.LENGTH_LONG);
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				// TODO Auto-generated method stub
				money.setText(response.optString("money"));
			}
		});
		NetHttpData.getHttpDao().getMoney(username, RECORE, new JsonHttpResponseHandler(){
			@Override
			public void onFailure(int statusCode, Header[] headers,String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseString, throwable);
				Toast.makeText(AtyMoney.this, "网络连接失败", Toast.LENGTH_LONG);
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
				// TODO Auto-generated method stub
				for (int i = 0; i < response.length(); i++) {
					ConsumptionEntity ctn = new ConsumptionEntity();
					ctn.setCommodityId(response.optJSONObject(i).optInt("cdyid"));
					ctn.setCtnname(response.optJSONObject(i).optString("ctnname"));
					ctn.setDate(response.optJSONObject(i).optString("date"));
					ctn.setId(response.optJSONObject(i).optInt("id"));
					ctn.setStatus(response.optJSONObject(i).optInt("status"));
					ctn.setUsername(response.optJSONObject(i).optString("username"));
					ctn.setMoney(response.optJSONObject(i).optDouble("money"));
					list.add(ctn);
				}
				adapter = new MoneyAdapter(AtyMoney.this, list);
				listView.setAdapter(adapter);
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(AtyMoney.this, AtyWebView.class);
		intent.putExtra("url", "https://www.baidu.com");
		startActivity(intent);
	}

}
