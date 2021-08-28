package com.campuscircle.app.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected final int itemLayoutId;
    protected LayoutInflater inflater;
    protected List<T> listData;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public RecyclerAdapter(Context context, List<T> listData, int itemLayoutId) {
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(itemLayoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        convert(holder, listData.get(position), position);

        holder.itemView.setOnClickListener((v) -> {

            if (itemClickListener != null) {
                itemClickListener.onClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener((v) -> {

            if (itemLongClickListener != null) {
                itemLongClickListener.onLongClick(v, position);
            }
            return false;
        });

    }

    /**
     * @param helper
     * @param item
     * @param position
     */
    public abstract void convert(RecyclerViewHolder helper, T item, int position);

    @Override
    public int getItemCount() {
        return listData.size();

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public void setItemLongClickListener(OnItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View parent, int position);
    }

    public interface OnItemLongClickListener {
        boolean onLongClick(View parent, int position);
    }
}
