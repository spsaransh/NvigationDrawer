package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.datapackage.PojoFeedback;

import java.util.List;

/**
 * Created by SARANSH on 6/11/2016.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.FeedbackViewHolder>
{
    private Context context;
    private List<PojoFeedback> list;
    public FeedBackAdapter(Context context,List<PojoFeedback> list)
    {
        this.context =context;
        this.list =list;
    }
    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_row_item,null);
        FeedbackViewHolder feedbackViewHolder = new FeedbackViewHolder(view);
        return feedbackViewHolder;

    }

    @Override
    public void onBindViewHolder(final FeedbackViewHolder holder, int position)
    {
        PojoFeedback pojoFeedback = list.get(position);
       holder.tv1.setText(pojoFeedback.getFeedback());
        holder.sad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.happy.setBackgroundResource(R.drawable.happyfeed);
            holder.sad.setBackgroundResource(R.drawable.sadfilled);
            holder.neutral.setBackgroundResource(R.drawable.neutralnfilled);

        }
    });
        holder.happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.happy.setBackgroundResource(R.drawable.happyfilled);
                holder.sad.setBackgroundResource(R.drawable.sadunfilled);
                holder.neutral.setBackgroundResource(R.drawable.neutralnfilled);

            }
        });
        holder.neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.happy.setBackgroundResource(R.drawable.happyfeed);
                holder.sad.setBackgroundResource(R.drawable.sadunfilled);
                holder.neutral.setBackgroundResource(R.drawable.neutralunfilled);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FeedbackViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {


        protected TextView tv1;
        protected ImageView happy,sad,neutral;



        public FeedbackViewHolder(View itemView) {
            super(itemView);
            this.tv1 = (TextView) itemView.findViewById(R.id.text_item);
            this.happy =(ImageView)itemView.findViewById(R.id.happy);
            this.neutral=(ImageView)itemView.findViewById(R.id.neutral);
            this.sad =(ImageView)itemView.findViewById(R.id.sad);



        }
    }
}
