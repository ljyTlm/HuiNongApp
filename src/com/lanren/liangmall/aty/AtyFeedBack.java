package com.lanren.liangmall.aty;

import org.apache.http.Header;
import org.json.JSONObject;

import com.lanren.liangmall.R;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtyFeedBack extends FragmentActivity {

	private static EditText editText1;
	private static EditText editText2;
	private static Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_feedback);
		editText1 = (EditText)findViewById(R.id.t_feedback1);
		editText2 = (EditText)findViewById(R.id.t_feedback2);
		button = (Button)findViewById(R.id.b_feedback);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(AtyFeedBack.this, "内容不能为空", 1);
				String title = editText1.getText().toString();
				String text = editText2.getText().toString();
				if ("".equals(text) || "".equals(title) || "标题".equals(title)) {
					Toast.makeText(AtyFeedBack.this, "内容不能为空", 1).show();
					return;
				}
				SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
				String username = ps.getString("username", "");
				NetHttpData.getHttpDao().postFeedBack(username, text, title, new JsonHttpResponseHandler("utf-8") {

					@Override
					public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
						Toast.makeText(AtyFeedBack.this, "网络连接失败！"+errorResponse, 1).show();
						super.onFailure(statusCode, headers, throwable, errorResponse);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
						Toast.makeText(AtyFeedBack.this, "反馈成功~~~~", 1).show();
					}

				});
			}
		});
	}
	
}
