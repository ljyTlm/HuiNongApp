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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);

		loginbtn = (Button) findViewById(R.id.button1);
		edtname = (EditText) findViewById(R.id.name);
		edtpwd = (EditText) findViewById(R.id.passwd);

		loginbtn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		username = edtname.getText().toString();
		userpwd = edtpwd.getText().toString();

		NetHttpData.getHttpDao().getLogin(username, userpwd, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyLogin.this, "��������ʧ�ܣ�"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("status");
				if (status == 1) {
					Intent i = new Intent(AtyLogin.this, MainActivity.class);
					startActivity(i);
					SaveUser();
				} else if (status == 0) {
					Toast.makeText(AtyLogin.this, "�˺Ż��������,�����µ�½��", 1).show();
				}
			}

		});
	}

	public void SaveUser() {
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
		Editor ed = ps.edit();
		ed.putBoolean("isLogin", true);
		ed.putString("username", "�ﰺ");
		ed.putString("dengji", "32");
		ed.commit();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		SaveUser();
		finish();
	}
}
