package ru.geekbrains.android1.hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.ViewHolder> {
    private String[] data;
    private Context context;
    private int selectedPosition = 0;

    public RecyclerDataAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        setText(holder, position);
        setOnItemClickBehavior(holder, position);
        highlightSelectedPosition(holder, position);
    }

    private void setText(@NonNull ViewHolder holder, final int position) {
        holder.listItemTextView.setText(data[position]);
    }

    private void setOnItemClickBehavior(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    private void highlightSelectedPosition(@NonNull ViewHolder holder, final int position) {
        if(position == selectedPosition) {
            int color = ContextCompat.getColor(context, R.color.colorPrimary);
            holder.itemView.setBackgroundColor(color);
        } else {
            int color = ContextCompat.getColor(context, android.R.color.transparent);
            holder.itemView.setBackgroundColor(color);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView listItemTextView;
        View itemView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            initViews(itemView);
        }

        private void initViews(View itemView) {
            listItemTextView = itemView.findViewById(R.id.listItemTextView);
        }
    }

}
