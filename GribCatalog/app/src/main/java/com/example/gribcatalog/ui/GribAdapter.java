package com.example.gribcatalog.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gribcatalog.R;

import java.util.List;

public class GribAdapter extends RecyclerView.Adapter<GribAdapter.ViewHolder> {
    private final Context context;
    private final List<Grib> items;

    public GribAdapter(Context context, List<Grib> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grib, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grib g = items.get(position);
        holder.tvName.setText(g.getName());
        holder.tvShort.setText(g.getShortDesc());
        holder.imgThumb.setImageResource(g.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GribDetailActivity.class);
            intent.putExtra("name", g.getName());
            intent.putExtra("desc", g.getLongDesc());
            intent.putExtra("img", g.getImageResId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView tvName, tvShort;

        ViewHolder(View itemView) {
            super(itemView);
            imgThumb = itemView.findViewById(R.id.imgThumb);
            tvName = itemView.findViewById(R.id.tvName);
            tvShort = itemView.findViewById(R.id.tvShort);
        }
    }
}
