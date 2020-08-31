package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickedListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickedListener longClickedListener;

    public ItemsAdapter(List<String> items, OnLongClickedListener longClickedListener) {
        this.items = items;
        this.longClickedListener = longClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    // Binding data to a view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the position
        String item = items.get(position);
        // Bind item to specified view holder
        holder.bind(item);

    }

    // Tells recycler view number of items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views for each row
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view in the view holder with string item
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // Notify listener on long click position
                    longClickedListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
