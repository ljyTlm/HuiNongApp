package com.lanren.liangmall.aty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.lanren.liangmall.FragmentTabHost;
import com.lanren.liangmall.MainActivity;
import com.lanren.liangmall.R;
import com.lanren.liangmall.net.NetHttpData;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtyLogin extends Activity implements OnClickListener {

	private Button loginbtn;
	private EditText edtname, edtpwd;
	private ProgressDialog dialog;
	private String username, userpwd;
	private Button registbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);

		loginbtn = (Button) findViewById(R.id.login);
		registbtn = (Button)findViewById(R.id.regist);
		edtname = (EditText) findViewById(R.id.name);
		edtpwd = (EditText) findViewById(R.id.passwd);

		loginbtn.setOnClickListener(this);
		registbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				username = edtname.getText().toString();
				userpwd = edtpwd.getText().toString();
				if ("".equals(username) || "".equals(userpwd)) {
					Toast.makeText(AtyLogin.this, "ÕËºÅ»òÃÜÂëÎª¿Õ£¡ ÇëÖØÐÂÌîÐ´£¡", 1).show();
					return;
				}
				NetHttpData.getHttpDao().getLogin(username, userpwd, 0, new JsonHttpResponseHandler("utf-8"){
					
					@Override
					public void onFailure(int statusCode, Header[] headers,Throwable throwable, JSONObject errorResponse) {
						Toast.makeText(AtyLogin.this, "×¢²áÊ§°Ü£¡£¡ Çë¼ì²éÍøÂçÁ¬½Ó£¡"+errorResponse, 1).show();
						super.onFailure(statusCode, headers, throwable, errorResponse);
					}
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
						// TODO Auto-generated method stub
						int status = response.optInt("status");
						if (status == 0) {
							Toast.makeText(AtyLogin.this, "×¢²áÊ§°Ü£¡¸ÃÓÃ»§ÒÑ´æÔÚ£¡£¡£¡", 1).show();
						}else {
							Toast.makeText(AtyLogin.this, "×¢²á³É¹¦£¡Çëµã»÷µÇÂ¼£¡£¡", 1).show();
						}
					}
				});
				
//				Intent intent = new Intent(AtyLogin.this, AtyWebView.class);
//				intent.putExtra("url", NetHttpData.getHttpDao().dataIp+"/HuiNong/Register");
//				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		username = edtname.getText().toString();
		userpwd = edtpwd.getText().toString();
		NetHttpData.getHttpDao().getLogin(username, userpwd, 1, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyLogin.this, "ÍøÂçÁ¬½ÓÊ§°Ü£¡"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("status");
				if (status == 1 || status == 2) {
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				    intent.setClass(AtyLogin.this,MainActivity.class);
					startActivity(intent);
					SaveUser(username, status);
					Toast.makeText(AtyLogin.this, "¹§Ï²ÄúµÇÂ¼³É¹¦£¡", 1).show();
					finish();
				} else if (status == 0) {
					Toast.makeText(AtyLogin.this, "ÕËºÅ»òÃÜÂë´íÎó,ÇëÖØÐÂµÇÂ½£¡", 1).show();
				}
			}

		});
	}

	public void SaveUser(String name, int vip) {
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //Êý¾Ý´æ´¢
		Editor ed = ps.edit();
		ed.putBoolean("isLogin", true);
		ed.putString("username", name);
		ed.putInt("vip", vip);
		ed.commit();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		finish();
	}
}
