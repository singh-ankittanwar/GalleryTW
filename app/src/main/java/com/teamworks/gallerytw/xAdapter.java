package com.teamworks.gallerytw;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class xAdapter extends RecyclerView.Adapter<xAdapter.xViewHolder> {

    private Context context;
    private List<apiItem> items;

    public xAdapter(Context context, List<apiItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public xViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.xitems, parent, false);
        return new xViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull xViewHolder holder, int position) {

        apiItem item = items.get(position);
        holder.xTitle.setText(item.getTitle());
        holder.xID.setText(Integer.toString(item.getId()));
        Picasso.get().load(item.getThumbnailUrl()).into(holder.xImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class xViewHolder extends RecyclerView.ViewHolder{
        ImageView xImage;
        TextView xID, xTitle;

        public xViewHolder(@NonNull View itemView) {
            super(itemView);

            xImage = (ImageView) itemView.findViewById(R.id.itmeIV);
            xID = (TextView) itemView.findViewById(R.id.itemID);
            xTitle = (TextView) itemView.findViewById(R.id.itemTitle);
        }
    }
}
