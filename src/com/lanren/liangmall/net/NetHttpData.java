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
	
	//public static String dataIp="http://192.168.43.176:8080";
	public static String dataIp="http://192.168.200.138:8080";
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
	/**
	 * ��ȡ��ѯ�б�����
	 * */
	public void getNews(JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyNews";
		client.post(url, responseHandler);	
	}
	/**
	 * ��¼
	 * @param username
	 * @param userpwd
	 * @param responseHandler
	 */
	public void getLogin(String username,String userpwd,JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyLogin";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("userpwd", userpwd);
		client.post(url, params, responseHandler);
	}
	/**
	 * �һ�vip
	 * @param username
	 * @param codestr
	 * @param responseHandler
	 */
	public void getVip(String username, String codestr,JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetVip";
		RequestParams params = new RequestParams();
		params.add("code", codestr);
		params.add("username", username);
		client.post(url, params, responseHandler);
	}
	/**
	 * ��ȡ��Ա����
	 * @param username
	 * @param responseHandler
	 */
	public void getVipScore(String username,JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyVipScore";
		RequestParams params = new RequestParams();
		params.add("username", username);
		client.post(url, params, responseHandler);
	}
	/**
	 * ��ȡ���ֿɶһ�����Ʒ
	 * @param responseHandler
	 */
	public void getVipGifts(JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetGifts";
		client.post(url, responseHandler);
	}
	/**
	 * ���ֶһ�����
	 * @param id
	 * @param username
	 * @param responseHandler
	 */
	public void getGiftByScore(int id, String username, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetGiftByScore";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("id", id+"");
		client.post(url, params, responseHandler);
	}
	/**
	 * �ύ������Ϣ
	 * @param username
	 * @param text
	 * @param title
	 * @param responseHandler
	 */
	public void postFeedBack(String username, String text, String title, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyFeedBack";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("text", text);
		params.add("title", title);
		client.post(url, params, responseHandler);
	}
	/**
	 * ��ȡ���ﳵ�б�
	 * @param username
	 * @param responseHandler
	 */
	public void getDataCar(String username, Integer operation, String id, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetDataCar";
		RequestParams params = new RequestParams();
		params.add("id", id);
		params.add("username", username.toString());
		params.add("operation", operation.toString());
		client.post(url, params, responseHandler);
	}
	/**
	 * ��ȡǮ�������Ѽ�¼
	 * @param username
	 * @param operation
	 * @param responseHandler
	 */
	public void getMoney(String username, Integer operation, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetMoney";
		RequestParams params = new RequestParams();
		params.add("username", username.toString());
		params.add("operation", operation.toString());
		client.post(url, params, responseHandler);
		
	}

}
