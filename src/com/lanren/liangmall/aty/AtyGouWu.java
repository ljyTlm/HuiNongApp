package com.lanren.liangmall.aty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.GouWuAdapter;
import com.lanren.liangmall.entity.CommodityEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class AtyGouWu extends Activity {
	
	final Integer getdata = 0;
	final Integer buydata = 1;
	final Integer deletedata = 2;
	
	private String username;
	private ListView listView;
	private GouWuAdapter adapter;
	private List<CommodityEntity> list;
	private AlertDialog.Builder builder;
	private String itemId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gouwuche_itme);
		list = new ArrayList<CommodityEntity>();
		listView = (ListView) findViewById(R.id.gouwu_listview);
		username = getUsername();
		if ("".equals(username)) {
			Toast.makeText(AtyGouWu.this, "������½", Toast.LENGTH_LONG);
			return;
		}
		dialog();
		setSaveData();
		setOnclik();
//		�����ǲ��Դ��� ��ʵ�������������Է�����
//		CommodityEntity commodityEntity = new CommodityEntity();
//		commodityEntity.setName("����");
//		commodityEntity.setPrice(10);
//		list.add(commodityEntity);
//		CommodityEntity commodityEntity2 = new CommodityEntity();
//		commodityEntity2.setName("ceshi");
//		commodityEntity2.setPrice(20);
//		list.add(commodityEntity2);
//		adapter = new GouWuAdapter(AtyGouWu.this, list);
//		listView.setAdapter(adapter);
	}
	
	/**ͳһ���ü����¼�*/
	private void setOnclik() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(AtyGouWu.this, arg1.getTag().toString(), Toast.LENGTH_SHORT).show();
				itemId = arg1.getTag().toString();
				builder.show();
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
	
	/**listView ���������*/
	private void dialog() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(AtyGouWu.this);
        builder.setIcon(R.drawable.ic_select);
        builder.setTitle("what are you do?"); 
        //    ָ�������б����ʾ����
        final String[] cities = {"                  ����", "                  ɾ��"};
        //    ����һ���������б�ѡ����
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override 
            public void onClick(DialogInterface dialog, int which)
            {
               
                Integer operation = null;
                if (which == 0) {
					operation = buydata;
				}else {
					operation = deletedata;
				}
                NetHttpData.getHttpDao().getDataCar(username, operation, itemId, new JsonHttpResponseHandler(){
                	@Override
                	public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                		// TODO Auto-generated method stub
                		super.onFailure(statusCode, headers, responseString, throwable);
                		
                	}
                	@Override
                	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                		// TODO Auto-generated method stub
                		if ("success".equals(response.opt("status"))) {
                			int id = Integer.valueOf(itemId);
							list.remove(id);
							adapter.notifyDataSetChanged();
							Toast.makeText(AtyGouWu.this, "�����ɹ���", Toast.LENGTH_SHORT).show();
						}else {
							Toast.makeText(AtyGouWu.this, "����ʧ����ˢ�º����ԣ�", Toast.LENGTH_LONG).show();
						}
                	}
                });
            }
        });
	}

	/**���湺�ﳵ������*/
	private void setSaveData(){
		NetHttpData.getHttpDao().getDataCar(username, getdata, "",new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyGouWu.this, "��������ʧ�ܣ�"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					JSONArray jsonArray = response.getJSONArray("dataCar");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject json = jsonArray.getJSONObject(i);
						CommodityEntity commodityEntity = new CommodityEntity();
						commodityEntity.setId(json.optInt("id"));
						commodityEntity.setName(json.optString("name"));
						commodityEntity.setPrice(json.optInt("price"));
						list.add(commodityEntity);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				adapter = new GouWuAdapter(AtyGouWu.this, list);
				listView.setAdapter(adapter);
			}
			
		});
	}
	
	
}
