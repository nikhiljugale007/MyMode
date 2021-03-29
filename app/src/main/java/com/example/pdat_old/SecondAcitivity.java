package com.example.pdat_old;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

public class SecondAcitivity extends AppCompatActivity {

	public static Vector<String> blocked_numbers = new Vector<String>();
	public static HashMap<String,Integer> blocked_number_map = new HashMap<String, Integer>();

	RecyclerView recyclerView;
	LinearLayoutManager linearLayoutManager;
	public static RecyclerView.Adapter adapter;
	TextView mode_name;
	Button btn_stop , btn_see_numbers;
	public static Switch high_priority;
	String mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_acitivity);

		if (getSupportActionBar() != null) {
			getSupportActionBar().hide();
		}


		recyclerView = findViewById(R.id.blocked_number_recyclerView);
		recyclerView.setHasFixedSize(true);

		linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		adapter = new Blocked_Call_Receiver(SecondAcitivity.this,blocked_numbers);
		recyclerView.setAdapter(adapter);

		mode_name = findViewById(R.id.mode_name);
		btn_stop = findViewById(R.id.btn_stop);

		high_priority = findViewById(R.id.checkbox);

		Intent intent = getIntent();
		mode = intent.getStringExtra("mode_name");

		mode_name.setText(mode);

		high_priority.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				MainActivity.nothighPriority = isChecked;
			}
		});

		btn_stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.startEnding = false;
				blocked_numbers.clear();
				Intent intent1 = new Intent(SecondAcitivity.this,MainActivity.class);
				startActivity(intent1);
				finish();
			}
		});
	}
}