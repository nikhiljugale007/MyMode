package com.example.pdat_old;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import java.lang.reflect.Method;
import com.android.internal.telephony.ITelephony;

public class IncomingCallReceiver extends BroadcastReceiver {
	String number;
	@Override
	public void onReceive(Context context, Intent intent) {

		ITelephony telephonyService;
		try {
			String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
			 number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

			if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){
				TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				try {
					@SuppressLint("SoonBlockedPrivateApi") Method m = tm.getClass().getDeclaredMethod("getITelephony");

					m.setAccessible(true);
					telephonyService = (ITelephony) m.invoke(tm);

					if ((number != null)) {
						if(MainActivity.startEnding){

							if(SecondAcitivity.blocked_number_map.containsKey(number)){
								int temp_count = SecondAcitivity.blocked_number_map.get(number);
								SecondAcitivity.blocked_number_map.put(number,temp_count+1);

								if(SecondAcitivity.blocked_number_map.get(number) > 2){
									if(SecondAcitivity.high_priority.isChecked()){
									}
									else{
										telephonyService.endCall();
										SecondAcitivity.blocked_numbers.add(number);
										SecondAcitivity.adapter.notifyDataSetChanged();
										sendMessage(context);
									}
								}
								else{
									telephonyService.endCall();
									SecondAcitivity.blocked_numbers.add(number);
									SecondAcitivity.adapter.notifyDataSetChanged();
									sendMessage(context);
								}
							}
							else{
								SecondAcitivity.blocked_number_map.put(number,1);
								telephonyService.endCall();
								SecondAcitivity.blocked_numbers.add(number);
								SecondAcitivity.adapter.notifyDataSetChanged();
								sendMessage(context);
							}

//							telephonyService.endCall();

//							try{
//								SmsManager smsManager = SmsManager.getDefault();
//								smsManager.sendTextMessage(number,null,"Hello world",null,null);
//								Toast.makeText(context, MainActivity.message, Toast.LENGTH_SHORT).show();
//							}
//							catch (Exception e){
//								Toast.makeText(context, "Message not sent", Toast.LENGTH_SHORT).show();
//							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

//				Toast.makeText(context, "Ring " + number, Toast.LENGTH_SHORT).show();

			}
//			if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)){
//				Toast.makeText(context, "Answered " + number, Toast.LENGTH_SHORT).show();
//			}
//			if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){
//				Toast.makeText(context, "Idle "+ number, Toast.LENGTH_SHORT).show();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendMessage(Context context) {
		Toast.makeText(context, "Ending the call from: " + number, Toast.LENGTH_SHORT).show();
		try{
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(number,null,"Hello world",null,null);
			Toast.makeText(context, MainActivity.message, Toast.LENGTH_SHORT).show();
		}
		catch (Exception e){
			Toast.makeText(context, "Message not sent", Toast.LENGTH_SHORT).show();
		}
	}


}

