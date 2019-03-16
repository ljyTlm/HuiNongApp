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
	//public static String dataIp="http://192.168.200.138:8080";
	//public static String dataIp="http://192.168.1.107:8080";
	public static String dataIp="http://192.168.0.102:8080";
	
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
	 * 获取咨询列表数据
	 * */
	public void getNews(JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyNews";
		client.post(url, responseHandler);	
	}
	/**
	 * 登录
	 * @param username
	 * @param userpwd
	 * @param responseHandler
	 */
	public void getLogin(String username,String userpwd, Integer operation, JsonHttpResponseHandler responseHandler){
		String url = dataIp+"/HuiNong/AtyLogin";
		RequestParams params = new RequestParams();
		params.add("username", username);
		params.add("userpwd", userpwd);
		params.add("operation", operation.toString());
		client.post(url, params, responseHandler);
	}
	/**
	 * 兑换vip
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
	 * 获取会员积分
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
	 * 获取积分可兑换的商品
	 * @param responseHandler
	 */
	public void getVipGifts(JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetGifts";
		client.post(url, responseHandler);
	}
	/**
	 * 积分兑换礼物
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
	 * 提交反馈信息
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
	 * 获取购物车列表
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
	 * 获取钱余额和消费记录
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

	/**
	 * 获取所有的商品信息、加入购物车
	 * @param username
	 * @param i
	 * @param jsonHttpResponseHandler
	 */
	public void getStore(String username, Integer id,Integer operation, JsonHttpResponseHandler responseHandler) {
		// TODO Auto-generated method stub
		String url = dataIp+"/HuiNong/AtyGetStore";
		RequestParams params = new RequestParams();
		params.add("username", username.toString());
		params.add("operation", operation.toString());
		params.add("id", id.toString());
		client.post(url, params, responseHandler);
	}

}
