package com.example.myaccount;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListsAdapter extends
        RecyclerView.Adapter<ListsAdapter.ListViewHolder> {

    private final LinkedList<String> mList;
    private final LayoutInflater mInflater;
    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView ItemViews;

        final ListsAdapter mAdapter;

        public ListViewHolder(View itemView, ListsAdapter adapter) {
            super(itemView);
            ItemViews = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        int mPosition = getLayoutPosition();
        String element = mList.get(mPosition);
        Toast.makeText(view.getContext(), "Student "+mPosition, Toast.LENGTH_SHORT).show();
    }
}

    public ListsAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mList = wordList;
    }
    @Override
    public ListsAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.list_item, parent, false);
        return new ListViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ListsAdapter.ListViewHolder holder,
                                 int position) {
        String mCurrent = mList.get(position);
        holder.ItemViews.setText(mCurrent);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

}

