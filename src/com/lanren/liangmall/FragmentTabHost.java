package com.lanren.liangmall;

import java.util.ArrayList;
import java.util.List;

import com.lanren.liangmall.aty.AtyFenLei;
import com.lanren.liangmall.aty.AtyGouWu;
import com.lanren.liangmall.aty.AtyHome;
import com.lanren.liangmall.aty.AtySou;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

public class FragmentTabHost extends Fragment {

	private LocalActivityManager localActivityManager;
	private TabHost tabHost;
	private TabWidget tabWidget;

	public FragmentTabHost() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tabhost, null);

		tabHost = (TabHost) view.findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) view.findViewById(android.R.id.tabs);

		localActivityManager = new LocalActivityManager(getActivity(), true);
		localActivityManager.dispatchCreate(savedInstanceState);
		tabHost.setup(localActivityManager);
		LayoutInflater f = LayoutInflater.from(getActivity());

		Intent localIntent1 = new Intent();
		View view1 = f.inflate(R.layout.item_a, null);
		localIntent1.setClass(getActivity(), AtyHome.class);
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(view1).setContent(localIntent1));

		Intent localIntent2 = new Intent();
		View view2 = f.inflate(R.layout.item_b, null);
		localIntent2.setClass(getActivity(), AtyFenLei.class);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(view2).setContent(localIntent2));

		Intent localIntent3 = new Intent();
		View view3 = f.inflate(R.layout.item_c, null);
		localIntent3.setClass(getActivity(), AtyGouWu.class);
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(view3).setContent(localIntent3));
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		localActivityManager.dispatchResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		localActivityManager.dispatchPause(getActivity().isFinishing());
	}

}
