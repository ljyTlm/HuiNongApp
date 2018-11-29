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

public class AtyGetVip extends FragmentActivity implements OnClickListener{
	
	private static TextView code;
	private static Button but;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_getvip);
		code = (TextView)findViewById(R.id.vipcode);
		but = (Button)findViewById(R.id.getvip_but);
		but.setOnClickListener(this);
	}
		
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String codestr = code.getText().toString();
		NetHttpData.getHttpDao().getVip(codestr, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyGetVip.this, "ÍøÂçÁ¬½ÓÊ§°Ü£¡"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("success");
				if (status == 1) {
					Toast.makeText(AtyGetVip.this, "¹§Ï²Äú¶Ò»»³É¹¦£¡ÇëÖØÐÂµÇÂ¼", 2).show();
					Intent i = new Intent(AtyGetVip.this, AtyLogin.class);
					startActivity(i);
				} else {
					Toast.makeText(AtyGetVip.this, "¶Ò»»ÂëÓÐÎó£¡ÇëÄúÖØÐÂÌîÐ´", 1).show();
					code.setText("");
				}
			}

		});
	}
}
		
		
	

	

