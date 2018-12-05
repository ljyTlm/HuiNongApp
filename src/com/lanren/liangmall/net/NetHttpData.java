package com.lanren.liangmall.net;

import org.apache.http.Header;

import android.R.integer;
import android.graphics.Bitmap;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class NetHttpData {
	
	public static String dataIp="http://192.168.43.176:8080";
	//public static String dataIp="http://192.168.1.107:8080";
	//public static String dataIp="http://192.168.1.104:8080";
	
	private static AsyncHttpClient client = null ;
	private static NetHttpData nethttpdao = null ;
	
	private NetHttpData (){
		client = new AsyncHttpClient();
	}
	
	public static NetHttpData getHttpDao(){
		if (nethttpdao==null) {
			nethttpdao = new NetHttpData();
		}
		return nethttpdao;
	}
	
	public void getNews(JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyNews";
		client.post(url, responseHandler);	
	}
	
	public void getFenLeiGoods(String classify ,JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/LiAng/FenLeiJson";
		RequestParams params = new RequestParams();
		params.add("classify", classify);
		client.post(url, params, responseHandler);	
	}
	
	public void getColor(){
		
	}
	
	public void isAddData(){
		
	}
	
	public void isDeleteData(){
		
	}
	
	
	public void getLogin(String username,String userpwd,JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyLogin";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("userpwd", userpwd);
		client.post(url, params, responseHandler);
	}

	public void getVip(String username, String codestr,JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetVip";
		RequestParams params = new RequestParams();
		params.add("code", codestr);
		params.add("username", username);
		client.post(url, params, responseHandler);
	}

	public void getVipScore(String username,JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyVipScore";
		RequestParams params = new RequestParams();
		params.add("username", username);
		client.post(url, params, responseHandler);
	}

	public void getVipGifts(JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetGifts";
		client.post(url, responseHandler);
	}

	public void getGiftByScore(int id, String username, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetGiftByScore";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("id", id+"");
		client.post(url, params, responseHandler);
	}

}
