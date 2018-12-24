package com.lanren.liangmall.aty;

import com.lanren.liangmall.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AtyStore extends Activity{
	
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_store);
		listView = (ListView)findViewById(R.id.listView1);
		
	}
}
