package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ImageViewHolder>{

    private int[] images;
    private Context mContext;

    public String username;
    public Location userLocation;





    public RecyclerAdapter(int[] images, Context Context){
        this.images = images;
        this.mContext = Context;


    }




    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_bear_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        String image_title = "NOOOO";
        double distance_btw = 0;

        int image_id = images[position];
        holder.bear_pic.setImageResource(image_id);

        switch (position) {
            case 0:
                image_title = "Great Bear Bell Bears";
                holder.latitude = 37.872061599999995;
                holder.longitude = -122.2578123;
                break;
            case 1:
                image_title = "Campanile Esplanade Bears";
                holder.latitude = 37.87233810000001;
                holder.longitude = -122.25792999999999;
                break;
            case 2:
                image_title = "In Stadium";
                holder.latitude = 0;
                holder.longitude = 0;
                break;
            case 3:
                image_title = "Les Bears";
                holder.latitude = 37.871707;
                holder.longitude = -122.253602;
                break;
            case 4:
                image_title = "Macchi Bears";
                holder.latitude = 37.874118;
                holder.longitude = -122.258778;
                break;
            case 5:
                image_title = "Class of 1927 Bear";
                holder.latitude = 37.869288;
                holder.longitude = -122.260125;
                break;
            case 6:
                image_title = "Stadium Entrance Bear";
                holder.latitude = 37.871305;
                holder.longitude = -122.252516;
                break;
            case 7:
                image_title = "South Hall Little Bear";
                holder.latitude = 37.871382;
                holder.longitude = -122.258355;
                break;
            case 8:
                image_title = "Strawberry Creek Topiary Bear";
                holder.latitude = 37.869861;
                holder.longitude = -122.261148;
                break;
        }

        holder.bear_pic_title.setText(image_title);
        Location bear_coord = new Location("bear coordinates");
        bear_coord.setLatitude(holder.latitude);
        bear_coord.setLongitude(holder.longitude);

        holder.bear_pic_distance.setText(String.valueOf(distance_btw) + " meters away");



        holder.parentLayout.setOnClickListener(new custom_OnClickListener(distance_btw, mContext) {

            @Override
            public void onClick(View view) {

                if (this.variable > 10) {

                    Toast.makeText(mContext, "Get Closer!", Toast.LENGTH_SHORT).show();

                } else {

                    Intent comment_intent = new Intent(mContext, CommentFeedActivity.class);
                    comment_intent.putExtra("username_text", username);
                    mContext.startActivity(comment_intent);
                }

            }

        });


    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView bear_pic;
        TextView bear_pic_title;
        TextView bear_pic_distance;
        RelativeLayout parentLayout;
        double latitude;
        double longitude;


        public ImageViewHolder(View itemView) {
            super(itemView);

            bear_pic = itemView.findViewById(R.id.bear_pic);
            bear_pic_title = itemView.findViewById(R.id.bear_pic_title);
            bear_pic_distance = itemView.findViewById(R.id.bear_pic_distance);
            parentLayout = itemView.findViewById(R.id.parent_layout);





        }
    }
}
