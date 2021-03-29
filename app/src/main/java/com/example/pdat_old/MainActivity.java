package com.example.pdat_old;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	public static String message = "I am in Lecture, I will call you later";
	public static Boolean startEnding = false;	
	public static Boolean nothighPriority = true;
	private static RecyclerView.Adapter adapter;
	private LinearLayoutManager  layoutManager;
	private static RecyclerView recyclerView;
	private static ArrayList<Mode_Model> data;
	Button btn_start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Recyler View
		recyclerView = findViewById(R.id.recyclerview);
		recyclerView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		addListItem();

		adapter = new RecyclerView_Adapter(MainActivity.this, data);
		recyclerView.setAdapter(adapter);
		//recycler view complete

		btn_start = findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startEnding = true;
				Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this,SecondAcitivity.class);
				intent.putExtra("mode_name",message);
				startActivity(intent);
			}
		});


		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
				String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
				requestPermissions(permissions, 101);
			}
		}
	}

	private void addListItem() {
		data = new ArrayList<Mode_Model>();
		data.add(new Mode_Model("Study Mode",R.drawable.ic_study));
		data.add(new Mode_Model("Lecture Mode",R.drawable.ic_lecture));
		data.add(new Mode_Model("Movie Mode",R.drawable.ic_movie));
		data.add(new Mode_Model("Sports Mode",R.drawable.ic_sports));

	}



	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case 101: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(this, "Permission granted: " + 101, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Permission NOT granted: " + 101, Toast.LENGTH_SHORT).show();
				}

				return;
			}
		}
	}
}