package com.lanren.liangmall;

import java.util.List;

import com.lanren.liangmall.aty.AtyLogin;
import com.lanren.liangmall.aty.AtyMoney;
import com.lanren.liangmall.aty.AtySou;
import com.lanren.liangmall.aty.AtyVip;
import com.lanren.liangmall.aty.AtyGetVip;
import com.lanren.liangmall.sidemenu.ResideMenu;
import com.lanren.liangmall.sidemenu.ResideMenuInfo;
import com.lanren.liangmall.sidemenu.ResideMenuItem;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

	private ResideMenu resideMenu;

	// 左划菜单栏目
	private ResideMenuItem itemHuiyuan;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;

	private ResideMenuInfo info;

	private RadioButton btnSou;

	// 获取登陆状态码！
	public String name, dengji;
	public String sts = "1";

	private boolean is_closed = false, str;
	private long mExitTime;

	private Button leftMenu;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		quUser();
		Log.v("TAG", "状态吗：" + str + name + dengji);
		setUpMenu();
		setListener();
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		FragmentTabHost f1 = new FragmentTabHost();
		ft.add(R.id.main_fragment, f1, "f1");
		ft.show(f1);
		ft.commit();

	}

	@Override
	protected void onResume() {
		super.onResume();
		quUser();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		quUser();
	}

	public void quUser() {
		SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
		str = ps.getBoolean("isLogin", false);
		name = ps.getString("username", "");
		dengji = ps.getString("dengji", "");
		if (str) {
			info = new ResideMenuInfo(this, R.drawable.ic_launcher1, name, dengji);
		} else {
			info = new ResideMenuInfo(this, R.drawable.ic_login, "请登录", "");
		}
	}

	@SuppressWarnings("deprecation")
	private void setUpMenu() {
		leftMenu = (Button) findViewById(R.id.title_bar_left_menu);

		// 搜索按钮
		btnSou = (RadioButton) findViewById(R.id.rbtn_img);

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// 禁止使用右侧菜单
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// 侧栏项目名称;
		itemHuiyuan = new ResideMenuItem(this, R.drawable.ic_vip, "会员服务");
		itemZhuangban = new ResideMenuItem(this, R.drawable.ic_information, "我的消息");
		itemShoucang = new ResideMenuItem(this, R.drawable.ic_money, "我的钱包");
		itemXiangce = new ResideMenuItem(this, R.drawable.ic_bug, "我的反馈");
		itemFile = new ResideMenuItem(this, R.drawable.ic_back, "退出程序");

		resideMenu.addMenuItem(itemHuiyuan, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemZhuangban, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemShoucang, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemXiangce, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);

	}

	private void setListener() {
		resideMenu.addMenuInfo(info);

		itemHuiyuan.setOnClickListener(this);
		itemZhuangban.setOnClickListener(this);
		itemShoucang.setOnClickListener(this);
		itemXiangce.setOnClickListener(this);
		itemFile.setOnClickListener(this);

		// 搜索按钮监听事件
		btnSou.setOnClickListener(this);

		info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "登陆", 1).show();
				startActivity(new Intent(MainActivity.this, AtyLogin.class));
			}
		});
		
		leftMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.rbtn_img) {
			startActivity(new Intent(MainActivity.this, AtySou.class));
		} else if (view.equals(itemHuiyuan)) {
			SharedPreferences ps = getSharedPreferences("isLogin", Context.MODE_PRIVATE); //数据存储
			if (ps.getBoolean("isLogin", false)) {
				if (ps.getInt("vip", 1) == 2) {
					startActivity(new Intent(MainActivity.this, AtyVip.class));
				}else {
					startActivity(new Intent(MainActivity.this, AtyGetVip.class));
				}
			}else {
				startActivity(new Intent(MainActivity.this, AtyGetVip.class));
				Toast.makeText(MainActivity.this, "请您先登录！", 1).show();
			}
		} else if (view.equals(itemZhuangban)) {
			Toast.makeText(MainActivity.this, "我的消息待完善", 1).show();
		} else if (view.equals(itemShoucang)) {
			startActivity(new Intent(MainActivity.this, AtyMoney.class));
			Toast.makeText(MainActivity.this, "我的钱包待完善", 1).show();
		} else if (view.equals(itemXiangce)) {
			Toast.makeText(MainActivity.this, "我的反馈待完善", 1).show();
		} else if (view.equals(itemFile)) {
			Toast.makeText(MainActivity.this, "退出", 1).show();
	        //System.exit(0);
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu() {
			is_closed = true;
			leftMenu.setVisibility(View.VISIBLE);
		}
	};

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				// 判断两次点击的时间间隔（默认设置为2秒）
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
					super.onBackPressed();
				}
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
