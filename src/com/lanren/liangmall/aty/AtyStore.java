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
				new AlertDialog.Builder(AtyStore.this).setTitle("��ȷ��Ҫ���빺�ﳵ�")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //����ȷ��������¼�
                    	NetHttpData.getHttpDao().getStore(username, sAdapter.list.get(index).getId(), 1, new JsonHttpResponseHandler("utf-8"){
                    		@Override
                    		public void onFailure(int statusCode,Header[] headers, Throwable throwable,JSONObject errorResponse) {
                    			// TODO Auto-generated method stub
                    			super.onFailure(statusCode, headers, throwable, errorResponse);
                    			Toast.makeText(AtyStore.this, "��Ǹ����Ʒ���޻� ��ˢ��!", Toast.LENGTH_LONG).show();
                    		}
                    		@Override
                			public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                				// TODO Auto-generated method stub
                				super.onSuccess(statusCode, headers, response);
                				if (response.optBoolean(0)) {
                					list.remove(index);
                					sAdapter.notifyDataSetChanged();
                    				Toast.makeText(AtyStore.this, "��ӳɹ���", Toast.LENGTH_LONG).show();
								}else {
									Toast.makeText(AtyStore.this, "���ʧ�ܣ�", Toast.LENGTH_LONG).show();
								}
                			}
                    	});
                    }
                }).setNegativeButton("ȡ��",null).show();
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
				Toast.makeText(AtyStore.this, "�ܱ�Ǹ~~��������ʧ��", Toast.LENGTH_LONG).show();
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
	
	/**��ʼ���û�����*/
	private String getUsername() {
		// TODO Auto-generated method stub
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //���ݴ洢
		String name = ps.getString("username", "");
		return name;
	}
}
