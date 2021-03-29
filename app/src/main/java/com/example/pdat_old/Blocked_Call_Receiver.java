package com.example.pdat_old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

public class Blocked_Call_Receiver extends  RecyclerView.Adapter<Blocked_Call_Receiver.MyViewHolder> {
	private Vector<String> dataset;
	private Context context;


	public Blocked_Call_Receiver(Context context,  Vector<String> dataset) {
		this.dataset = dataset;
		this.context = context;
	}

	@NonNull
	@Override
	public Blocked_Call_Receiver.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blocked_call_item,parent,false);
		MyViewHolder viewHolder = new MyViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
		TextView textView = holder.textView;
		CardView cardView = holder.cardView;

		textView.setText(dataset.get(position));
		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Number Clicked", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder{
		TextView textView;
		CardView cardView;

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);

			textView = itemView.findViewById(R.id.tv_blocked_numbers);
			cardView = itemView.findViewById(R.id.card_view);
		}
	}
}
