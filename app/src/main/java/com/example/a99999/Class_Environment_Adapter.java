package com.example.a99999;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sht.homework.R;

import java.util.List;

class Class_Environment_Adapter extends RecyclerView.Adapter<Class_Environment_Adapter.ViewHolder> {

    private List<Class_Environment> mWorldList;

    public Class_Environment_Adapter(List<Class_Environment> worldList) {
        mWorldList = worldList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View worldView;
        ImageView worldImage;
        TextView worldName;

        public ViewHolder(View view) {
            super(view);
            worldView = view;
            worldImage = (ImageView) view.findViewById(R.id.WorldTop_image);
            worldName = (TextView) view.findViewById(R.id.WorldTop_name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.world_top_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.worldView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Class_Environment_Adapter tag = (Class_Environment_Adapter) v.getTag();
                Class_Environment word = mWorldList.get(position);
                Intent intent = new Intent(v.getContext(), Web.class);
                intent.putExtra("name", word.getName());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        Class_Environment word = mWorldList.get(i);
        viewHolder.worldImage.setImageResource(word.getImageId());
        viewHolder.worldName.setText(word.getName());
    }

    @Override
    public int getItemCount() {
        return mWorldList.size();
    }


}