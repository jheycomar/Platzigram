package com.lopsa.platzigram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lopsa.platzigram.Model.Picture;
import com.lopsa.platzigram.PicturedetailActivity;
import com.lopsa.platzigram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ADMIN on 27/10/2017.
 */

public class PictureAdapterreciclerView extends  RecyclerView.Adapter<PictureAdapterreciclerView.PictureViewHolder>{

    private ArrayList<Picture> pictures;
    private int resurce;
    private Activity activity;

    public PictureAdapterreciclerView(ArrayList<Picture> pictures, int resurce, Activity activity) {
        this.pictures = pictures;
        this.resurce = resurce;
        this.activity = activity;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resurce,parent,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

        Picture picture=pictures.get(position);
        holder.userNameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLikeNumber());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCad);
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);

        //este metodo llama la pagina de detalle
        holder.pictureCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(activity, PicturedetailActivity.class);
                //si el SDK del dispositivo es mayor a Lollipop se anima
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity,view,activity.getString(R.string.transitionName_picture)).toBundle());
                }
                else {
                activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public  class  PictureViewHolder extends RecyclerView.ViewHolder{
         private ImageView  pictureCad;
         private TextView userNameCard;
         private TextView timeCard;
         private TextView likeNumberCard;
        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureCad=(ImageView)itemView.findViewById(R.id.PictureCard);
            userNameCard=(TextView) itemView.findViewById(R.id.UserNameCard);
            timeCard=(TextView)itemView.findViewById(R.id.TimeCard);
            likeNumberCard=(TextView)itemView.findViewById(R.id.LikeNumberCard);


        }
    }
}
