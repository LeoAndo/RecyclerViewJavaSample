package com.leoleo.recyclerviewjavasample;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public final class MyListAdapter extends ListAdapter<Item, MyListAdapter.VH> {
    @Nullable
    private OnItemClickListener listener;

    public MyListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        final Item item = getItem(position);
        holder.bindTo(item);
    }

    public class VH extends RecyclerView.ViewHolder {
        private final View itemView;

        public VH(@NonNull final View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void bindTo(@NonNull final Item item) {
            final TextView title = itemView.findViewById(R.id.title);
            title.setText(item.getTitle());
            itemView.setOnClickListener(v -> {
                // final int position = getBindingAdapterPosition();
                final int position = getAdapterPosition();
                Log.d("VH", "onClick position-> " + position);
                if (position != NO_POSITION && listener != null) {
                    listener.onItemClick(getItem(position), position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(@NonNull final Item item, final int position);
    }

    public void setOnItemClickListener(@Nullable final OnItemClickListener l) {
        this.listener = l;
    }

    private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Item>() {
                @Override
                public boolean areItemsTheSame(@NonNull final Item oldItem, @NonNull final Item newItem) {
                    return oldItem.getInternalId() == newItem.getInternalId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull final Item oldItem, @NonNull final Item newItem) {
                    return oldItem.equals(newItem);
                }
            };
}