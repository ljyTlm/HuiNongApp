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
				Intent intent = new Intent(AtyLogin.this, AtyWebView.class);
				intent.putExtra("url", NetHttpData.getHttpDao().dataIp+"/HuiNong/Register");
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		username = edtname.getText().toString();
		userpwd = edtpwd.getText().toString();
		NetHttpData.getHttpDao().getLogin(username, userpwd, new JsonHttpResponseHandler("utf-8") {

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Toast.makeText(AtyLogin.this, "Õ¯¬Á¡¨Ω” ß∞‹£°"+errorResponse, 1).show();
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				int status = response.optInt("status");
				for (int i = 0; i < 10; i++) {
					Log.v("◊¢“‚£°£°", status+"");
				}
				if (status == 1 || status == 2) {
					Intent i = new Intent(AtyLogin.this, MainActivity.class);
					startActivity(i);
					SaveUser(username, status);
					Toast.makeText(AtyLogin.this, "πßœ≤ƒ˙µ«¬º≥…π¶£°", 1).show();
				} else if (status == 0) {
					Toast.makeText(AtyLogin.this, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ,«Î÷ÿ–¬µ«¬Ω£°", 1).show();
				}
			}

		});
	}

	public void SaveUser(String name, int vip) {
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); // ˝æ›¥Ê¥¢
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
