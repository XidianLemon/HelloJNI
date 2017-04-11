package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_main);
	     btn = (Button) findViewById(R.id.button);
	     tv = (TextView) findViewById(R.id.text);
	     btn.setOnClickListener(new View.OnClickListener() {

	         @Override
	         public void onClick(View v) {
	             // TODO Auto-generated method stub
	             tv.setText(stringFromJni());
	         }
	     });
	 }
	 private native String stringFromJni();
	
	 static {
	     System.loadLibrary("HelloWorld");
	 }
}