package com.lanren.liangmall;

import java.util.List;

import com.lanren.liangmall.aty.AtyLogin;
import com.lanren.liangmall.aty.AtySou;
import com.lanren.liangmall.aty.AtyXiangQing;
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

	// �󻮲˵���Ŀ
	private ResideMenuItem itemHuiyuan;
	private ResideMenuItem itemZhuangban;
	private ResideMenuItem itemShoucang;
	private ResideMenuItem itemXiangce;
	private ResideMenuItem itemFile;

	private ResideMenuInfo info;

	private RadioButton btnSou;

	// ��ȡ��½״̬�룡
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
		Log.v("TAG", "״̬��" + str + name + dengji);
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
			info = new ResideMenuInfo(this, R.drawable.ic_login, "���¼", "");
		}
	}

	@SuppressWarnings("deprecation")
	private void setUpMenu() {
		leftMenu = (Button) findViewById(R.id.title_bar_left_menu);

		// ������ť
		btnSou = (RadioButton) findViewById(R.id.rbtn_img);

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is
		// 150dip.
		resideMenu.setScaleValue(0.6f);
		// ��ֹʹ���Ҳ�˵�
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// ������Ŀ����;
		itemHuiyuan = new ResideMenuItem(this, R.drawable.ic_vip, "��Ա����");
		itemZhuangban = new ResideMenuItem(this, R.drawable.ic_information, "�ҵ���Ϣ");
		itemShoucang = new ResideMenuItem(this, R.drawable.ic_money, "�ҵ�Ǯ��");
		itemXiangce = new ResideMenuItem(this, R.drawable.ic_bug, "�ҵķ���");
		itemFile = new ResideMenuItem(this, R.drawable.ic_back, "�˳�����");

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

		// ������ť�����¼�
		btnSou.setOnClickListener(this);

		info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "��½", 1).show();
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
			Toast.makeText(MainActivity.this, "���ǻ�Ա", 1).show();
		} else if (view.equals(itemZhuangban)) {
			Toast.makeText(MainActivity.this, "����װ��", 1).show();
		} else if (view.equals(itemShoucang)) {
			Toast.makeText(MainActivity.this, "�����ղ�", 1).show();
		} else if (view.equals(itemXiangce)) {
			Toast.makeText(MainActivity.this, "�������", 1).show();
		} else if (view.equals(itemFile)) {
			Toast.makeText(MainActivity.this, "�˳�", 1).show();
	        System.exit(0);
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

	// �����ֻ��ϵ�BACK��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �жϲ˵��Ƿ�ر�
			if (is_closed) {
				// �ж����ε����ʱ������Ĭ������Ϊ2�룩
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();

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
