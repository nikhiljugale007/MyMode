package com.example.pdat_old;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
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

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.MyViewHolder> {

	private ArrayList<Mode_Model> dataset;
	private Context context;

	private static int lastClickedPosition = -1;
	private int selectedItem;

	public RecyclerView_Adapter(Context context, ArrayList<Mode_Model> dataset ) {
		this.context = context;
		this.dataset = dataset;
		selectedItem = 0;
	}

	@NonNull
	@Override
	public RecyclerView_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

		MyViewHolder viewHolder = new MyViewHolder(view);
		return viewHolder;
	}


	@Override
	public void onBindViewHolder(@NonNull RecyclerView_Adapter.MyViewHolder holder, int position) {
		TextView textView = holder.textView;
		ImageView imageView = holder.imageView;
		CardView cardView = holder.cardView;

		textView.setText(dataset.get(position).getMode_name());
		imageView.setImageResource(dataset.get(position).getMode_image());

		Mode_Model temp = dataset.get(position);
		MainActivity.message = temp.mode_name;

		holder.imageView.setBackgroundColor(Color.parseColor("#DFD7D7"));

		if(selectedItem == position){
			holder.imageView.setBackgroundColor(Color.RED);
		}
		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.message = dataset.get(position).getMode_name();
				switch (position){
					case 0:
						MainActivity.message = "I am studying right now, Call me later.";
						break;
					case 1:
						MainActivity.message = "I am in lecture now, I will Call you later.";
						break;
					case 2:
						MainActivity.message = "I am watching movie, Call me later.";
						break;
					case 3:
						MainActivity.message = "I am playing sports, phone not with me,  I will call you later.";
						break;
					default:
						MainActivity.message = "I am busy right now, Call me later";
				}
				int previousItem = selectedItem;
				selectedItem = position;
				Toast.makeText(context, dataset.get(position).getMode_name() + " = "+MainActivity.message, Toast.LENGTH_SHORT).show();

				notifyItemChanged(previousItem);
				notifyItemChanged(position);
			}
		});
	}
	@Override
	public int getItemCount() {
		return dataset.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder{
		TextView textView;
		ImageView imageView;
		CardView cardView;

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);

			textView = itemView.findViewById(R.id.textview);
			imageView = itemView.findViewById(R.id.imageview);
			cardView = itemView.findViewById(R.id.card_view);
		}
	}

}

