package com.example.cs160_sp18.prog3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapater extends RecyclerView.Adapter<RecyclerAdapater.ImageButtonHolder>{

    private int[] images;

    public RecyclerAdapater(int[] images){
        this.images = images;
    }



    @Override
    public ImageButtonHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_bear_layout, parent, false);
        ImageButtonHolder imageButtonHolder = new ImageButtonHolder(view);

        return imageButtonHolder;
    }

    @Override
    public void onBindViewHolder(ImageButtonHolder holder, int position) {

        String image_title = "NOOOO";

        int image_id = images[position];
        holder.bear_pic.setImageResource(image_id);

        switch (position) {
            case 0:
                image_title = "Bell Bears";
                break;
            case 1:
                image_title = "Bench Bears";
                break;
            case 2:
                image_title = "In Stadium";
                break;
            case 3:
                image_title = "Les Bears";
                break;
            case 4:
                image_title = "Macchi Bears";
                break;
            case 5:
                image_title = "Mlk Bear";
                break;
            case 6:
                image_title = "Outside Stadium";
                break;
            case 7:
                image_title = "South Hall";
                break;
            case 8:
                image_title = "Strawberry Creek";
                break;
        }

        holder.bear_pic_title.setText(image_title);
        holder.bear_pic_distance.setText("need to implement");


    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageButtonHolder extends RecyclerView.ViewHolder {

        ImageButton bear_pic;
        TextView bear_pic_title;
        TextView bear_pic_distance;

        public ImageButtonHolder(View itemView) {
            super(itemView);

            bear_pic = itemView.findViewById(R.id.bear_pic);
            bear_pic_title = itemView.findViewById(R.id.bear_pic_title);
            bear_pic_distance = itemView.findViewById(R.id.bear_pic_distance);




        }
    }
}
