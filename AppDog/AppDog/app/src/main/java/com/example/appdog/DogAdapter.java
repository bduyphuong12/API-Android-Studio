package com.example.appdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogItemViewHolder> {
    private Context context;
    private ArrayList<DogBreed> dog;
    public DogAdapter(List<DogBreed> dog, Context c) {
        this.dog = (ArrayList<DogBreed>) dog;
        this.context = c;
    }


    public int getItemCount(){
        return dog.size();
    }
    @NonNull
    @Override
    public DogItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dogitem, parent, false);

        return new DogItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull DogItemViewHolder holder, int position) {
        DogBreed u = dog.get(position);
        Picasso.with(context)
                .load(u.getUrl())
                .into(holder.avatar);
        holder.tv_name.setText(u.getName());

    }


    public  class DogItemViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name;
        public ImageView avatar;

        public DogItemViewHolder(View ItemView){
            super(ItemView);
            tv_name = ItemView.findViewById(R.id.name);
            avatar = ItemView.findViewById(R.id.avatar);
        }
    }
}



