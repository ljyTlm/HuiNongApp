package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.StoreAdapter;
import com.lanren.liangmall.entity.CommodityEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

public class AtyStore extends Activity{
	
	String username;
	ListView listView;
	List<CommodityEntity> list;
	StoreAdapter sAdapter;
	Integer index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_store);
		list = new ArrayList<CommodityEntity>();
		username = getUsername();
		listView = (ListView)findViewById(R.id.listView1);
		getData();
		setOnclik();
		
	}

	private void setOnclik() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				index = (Integer)arg1.getTag();
				new AlertDialog.Builder(AtyStore.this).setTitle("您确定要加入购物车嘛？")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                    	NetHttpData.getHttpDao().getStore(username, sAdapter.list.get(index).getId(), 1, new JsonHttpResponseHandler("utf-8"){
                    		@Override
                    		public void onFailure(int statusCode,Header[] headers, Throwable throwable,JSONObject errorResponse) {
                    			// TODO Auto-generated method stub
                    			super.onFailure(statusCode, headers, throwable, errorResponse);
                    			Toast.makeText(AtyStore.this, "抱歉此商品暂无货 请刷新!", Toast.LENGTH_LONG).show();
                    		}
                    		@Override
                			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                				// TODO Auto-generated method stub
                				super.onSuccess(statusCode, headers, response);
                				if (response.optBoolean(0)) {
                					list.remove(index);
                					sAdapter.notifyDataSetChanged();
                    				Toast.makeText(AtyStore.this, "添加成功！", Toast.LENGTH_LONG).show();
								}else {
									Toast.makeText(AtyStore.this, "添加失败！", Toast.LENGTH_LONG).show();
								}
                			}
                    	});
                    }
                }).setNegativeButton("取消",null).show();
			}
		});
		
	}

	private void getData() {
		// TODO Auto-generated method stub
		NetHttpData.getHttpDao().getStore(username, -1, 0, new JsonHttpResponseHandler("utf-8"){
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Toast.makeText(AtyStore.this, "很抱歉~~网络连接失败", Toast.LENGTH_LONG).show();
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				for (int i = 0; i < response.length(); i++) {
					CommodityEntity cEntity = new CommodityEntity();
					cEntity.setName(response.optJSONObject(i).optString("name"));
					cEntity.setSellername(response.optJSONObject(i).optString("sellername"));
					cEntity.setId(response.optJSONObject(i).optInt("id"));
					cEntity.setPrice(response.optJSONObject(i).optDouble("price"));
					cEntity.setStatus(response.optJSONObject(i).optInt("status"));
					list.add(cEntity);
				}
				sAdapter = new StoreAdapter(list, AtyStore.this);
				listView.setAdapter(sAdapter);
			}
		});
	}
	
	/**初始化用户姓名*/
	private String getUsername() {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
		String name = ps.getString("username", "");
		return name;
	}
}
