package com.lanren.liangmall.aty.fenlei;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.adapter.NanZAdapter;
import com.lanren.liangmall.aty.AtyXiangQing;
import com.lanren.liangmall.entity.GoodsEntity;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AtyNvKu extends Activity {

	private int classify;
	private List<GoodsEntity> JsonList = new ArrayList<GoodsEntity>();
	private NanZAdapter NanAdapter;
	private ListView nvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_fenlei_nvku);
		nvList = (ListView) findViewById(R.id.fenlei_nvku);
		// ���ܷ���ҳ�����id����
		Bundle bundle = this.getIntent().getExtras();
		classify = bundle.getInt("lei");

		Log.v("TAG", "����ֵ��" + classify);

		JsonInfo();
		dianJi();
	}

	// ������Ʒ��JSON����
	public void JsonInfo() {
		Log.v("TAG", "��������");

		NetHttpData.getHttpDao().getFenLeiGoods(classify + "", new JsonHttpResponseHandler("utf-8") {
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Log.v("TAG", "��������ʧ��");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.v("TAG", "�������ӳɹ�");
				try {
					int lei = response.getInt("classify");
					Log.v("TAG", "��ȡ��������룺" + lei);
					JSONArray array = response.getJSONArray("result");
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						GoodsEntity ge = new GoodsEntity();
						ge.setSid(obj.optString("sid"));
						ge.setsClassIfy(obj.optString("sClassIfy"));
						ge.setsName(obj.optString("sName"));
						ge.setsPrice(obj.optString("sPrice"));
						ge.setsInventory(obj.optString("sInventory"));
						ge.setsAddr(obj.optString("sAddr"));
						ge.setsSeller(obj.optString("sSeller"));
						ge.setsSales(obj.optString("sSales"));
						String Dimg = obj.optString("sImgas");
						ge.setsDrawable(Dimg);
						ge.setsColor(obj.optString("sColor"));
						ge.setsType(obj.optString("sType"));
						Log.v("TAG", "��ȡ����JSON���ݣ�" + ge.toString());
						Log.v("TAG", "��ȡ����ͼƬ��" + Dimg.toString());
						JsonList.add(ge);
					}

					NanAdapter = new NanZAdapter(AtyNvKu.this, JsonList);
					nvList.setAdapter(NanAdapter);

				} catch (JSONException e) {
					Log.v("TAG", "��ȡJSON����ʧ��");
					e.printStackTrace();
				}
			}

		});
	}
	public void dianJi(){
		nvList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				GoodsEntity g = JsonList.get(position);
				Intent in = new Intent(AtyNvKu.this,AtyXiangQing.class);
				Bundle b = new Bundle();
				b.putString("img", g.getsDrawable());
				b.putString("name", g.getsName());
				b.putString("price", g.getsPrice());
				b.putString("sales", g.getsSales());
				b.putString("addr", g.getsAddr());
				in.putExtras(b);
				startActivity(in);
			}
		});
	}
}
