package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BearAdapter extends RecyclerView.Adapter<BearAdapter.ViewHolder>{

    private ArrayList<BearsActivity.bearObject> bearLandMarks;
    private Context mContext;
    private String username;

    public BearAdapter(Context context, ArrayList<BearsActivity.bearObject> bears, String username) {
        mContext = context;
        bearLandMarks = bears;
        this.username = username;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_bear_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BearsActivity.bearObject currBear = bearLandMarks.get(position);
        holder.image.setImageResource(currBear.imageID);
        holder.imageName.setText(currBear.imageTitle);

        int distanceBTW = Math.round(currBear.userLocation.distanceTo(currBear.bearLocation));

        //Setting text color
        if (distanceBTW < 10) {
            holder.imageDistance.setText(Integer.toString(Math.round(distanceBTW)) + " meters away");
            holder.imageDistance.setTextColor(Color.rgb(21, 178, 76));
        } else {
            holder.imageDistance.setText(Integer.toString(distanceBTW) + " meters away");
            holder.imageDistance.setTextColor(Color.parseColor("#737373"));
        }


        holder.parentLayout.setOnClickListener(new custom_OnClickListener(distanceBTW, mContext, this.username, currBear.imageTitle){

            @Override
            public void onClick(View view) {
                if (this.distanceBTW > 10) {
                    Toast.makeText(mContext, "Get Closer!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent comment_intent = new Intent(mContext, CommentFeedActivity.class);
                    comment_intent.putExtra("username_text", this.username);
                    comment_intent.putExtra("landmark_name", this.landmark_name);
                    mContext.startActivity(comment_intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return bearLandMarks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView imageName;
        TextView imageDistance;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.bear_pic);
            imageName = itemView.findViewById(R.id.bear_pic_title);
            imageDistance = itemView.findViewById(R.id.bear_distance);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
