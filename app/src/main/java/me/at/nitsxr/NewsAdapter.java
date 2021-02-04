package me.at.nitsxr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import in.co.iamannitian.iamannitian.OnViewPagerClick;
import in.co.iamannitian.iamannitian.R;
import static android.content.Context.MODE_PRIVATE;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{
    private Context mContext;
    private List<NewsGetterSetter> mList;

    public NewsAdapter(Context mContext, List<NewsGetterSetter> mList)
    {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.news_scroll, parent, false);
        return  new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position)
    {
           final NewsGetterSetter getterSetter = mList.get(position);
           String news_title = getterSetter.getNewsTitle();
           String image_url = getterSetter.getImageUrl();
           final String newsId = getterSetter.getNewsId();

           holder.reactionCount.setText(getterSetter.getCount());

           if(getterSetter.getStatus().equals("1"))
               holder.reactionHeart.setImageResource(R.drawable.ic_favorite_black_24dp);
           else
               holder.reactionHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);

     holder.newsTitle.setText(news_title);
           Glide.with(mContext)
                .asBitmap()
                .load(image_url)
                .into(holder.newsImageView);

    holder.reactionHeart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int updated_status = 0;
            int updated_count = 0;

            if(getterSetter.getStatus().equals("1"))
            {
                updated_status = 0;
                updated_count =  Integer.parseInt(getterSetter.getCount()) - 1;
                holder.reactionCount.setText(updated_count+"");
                holder.reactionHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            else
            {
                updated_status = 1;
                updated_count  = Integer.parseInt(getterSetter.getCount()) + 1;
                holder.reactionCount.setText(updated_count+"");
                holder.reactionHeart.setImageResource(R.drawable.ic_favorite_black_24dp);
            }

            getterSetter.setStatus(updated_status+"");
            getterSetter.setCount(updated_count+"");

            UserReaction userReaction = new UserReaction(getterSetter,
                    mContext.getSharedPreferences("appData", MODE_PRIVATE)
                            .getString("userId",""), updated_status);
            userReaction.execute();
        }
    });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v)
               {
                   Intent intent =  new Intent(mContext,OnViewPagerClick.class);
                   Bundle b = new Bundle();
                   b.putSerializable("sampleObject", getterSetter);
                   intent.putExtras(b);
                   mContext.startActivity(intent);
               }
           });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext,
                        "This feature is not yet available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder
    {
        public TextView newsTitle;
        public TextView reactionCount;
        public ImageView newsImageView;
        public ImageView reactionHeart, shareButton;
        public CardView cardView;

        public NewsViewHolder(View itemView)
        {
            super(itemView);
            reactionCount = itemView.findViewById(R.id.reactionCount);
            reactionHeart = itemView.findViewById(R.id.reactionHeart);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsImageView = itemView.findViewById(R.id.newsImageView);
            cardView = itemView.findViewById(R.id.cardView);
            shareButton = itemView.findViewById(R.id.shareButton);
        }
    }
}
