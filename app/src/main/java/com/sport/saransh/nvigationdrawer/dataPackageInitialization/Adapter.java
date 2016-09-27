package com.sport.saransh.nvigationdrawer.dataPackageInitialization;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sport.saransh.nvigationdrawer.R;
import com.sport.saransh.nvigationdrawer.datapackage.PojoNavigationDrwaer;

import java.util.List;

import static com.sport.saransh.nvigationdrawer.R.styleable.RecyclerView;

/**
 * Created by SARANSH on 3/13/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.CustViewHolder>{
  private Context context;
    private List<PojoNavigationDrwaer> list;
    public Adapter(Context context,List<PojoNavigationDrwaer> list) {
        this.context =context;
        this.list =list;

    }

    @Override
    public Adapter.CustViewHolder onCreateViewHolder(ViewGroup parent, int i) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_row_item,null);
        CustViewHolder custViewHolder =new CustViewHolder(view);
        return  custViewHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.CustViewHolder custViewHolder, int i)
    {
       PojoNavigationDrwaer dataobj = list.get(i);
        custViewHolder.tv1.setText(dataobj.getMitem());
        custViewHolder.imgview.setImageResource(dataobj.getImgres());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class CustViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

       protected TextView tv1;
        protected ImageView imgview;
        public CustViewHolder(View itemView) {
            super(itemView);
            this.tv1 = (TextView)itemView.findViewById(R.id.text_item);
            this.imgview =(ImageView)itemView.findViewById(R.id.rowIcon);


        }

    }

}
