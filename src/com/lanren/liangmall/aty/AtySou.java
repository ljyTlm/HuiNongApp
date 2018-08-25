package com.lanren.liangmall.aty;

import java.util.ArrayList;

import com.lanren.liangmall.R;
import com.lanren.liangmall.R.id;
import com.lanren.liangmall.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class AtySou extends Activity {
	
	private MultiAutoCompleteTextView mactv;
	private ArrayAdapter<String> mactvAdapter;
	private String[] strs = new String[]{"ÄãºÃ£¡","ÄãºÃ£¡ÏþºÛ","ÄãºÃ£¡ºâÈý","ÄãºÃ£¡ÃôÐñ","àË£¡ÃôÐñ","àË£¡Ðì·«"};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sou_itme);
		
		mactv = (MultiAutoCompleteTextView) findViewById(R.id.sou_search_edit);
		mactvAdapter = new ArrayAdapter<String>(AtySou.this, android.R.layout.simple_dropdown_item_1line,strs);
		mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		mactv.setAdapter(mactvAdapter);
	}
}
