package com.lanren.liangmall;

import com.lanren.liangmall.aty.AtyLogin;
import com.lanren.liangmall.aty.AtySou;
import com.lanren.liangmall.aty.AtyXiangQing;
import com.lanren.liangmall.sidemenu.ResideMenu;
import com.lanren.liangmall.sidemenu.ResideMenuInfo;
import com.lanren.liangmall.sidemenu.ResideMenuItem;

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
			info = new ResideMenuInfo(this, R.drawable.ic_launcher, "���¼", "");
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
		itemHuiyuan = new ResideMenuItem(this, R.drawable.ic_launcher, "��ͨ��Ա");
		itemZhuangban = new ResideMenuItem(this, R.drawable.ic_launcher, "����װ��");
		itemShoucang = new ResideMenuItem(this, R.drawable.ic_launcher, "�ҵ��ղ�");
		itemXiangce = new ResideMenuItem(this, R.drawable.ic_launcher, "�ҵ����");
		itemFile = new ResideMenuItem(this, R.drawable.ic_launcher, "�ҵ��ļ�");

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

		info.setOnClickListener(this);

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
		try {
			ResideMenuInfo v = (ResideMenuInfo)view;
			Toast.makeText(MainActivity.this, "��½", 1).show();
			startActivity(new Intent(MainActivity.this, AtyLogin.class));
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (view.getId() == R.id.rbtn_img) {
			startActivity(new Intent(MainActivity.this, AtySou.class));
		} else if (view == itemHuiyuan) {
			Toast.makeText(MainActivity.this, "�˷���δ����", 1).show();
		} else if (view == itemZhuangban) {
			Toast.makeText(MainActivity.this, "�˷���δ����", 1).show();
		} else if (view == itemShoucang) {
			Toast.makeText(MainActivity.this, "�˷���δ����", 1).show();
		} else if (view == itemXiangce) {
			Toast.makeText(MainActivity.this, "�˷���δ����", 1).show();
		} else if (view == itemFile) {
			Toast.makeText(MainActivity.this, "�˷���δ����", 1).show();
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
