package com.example.wangwei.polycare.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangwei.polycare.R;
import com.example.wangwei.polycare.activity.EventActivity;
import com.example.wangwei.polycare.data.Event;

import java.util.List;

/**
 * Created by wangwei on 2018/3/29.
 * 用来管理数据，把数据和前端连到一起，是一个展示数据的载体
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    public static final String ACTIVITY =  "debug here";
    private Context context;
    //abstraction，通过context来访问资源，还可以启动其他组件，activity，service，得到这种服务
    //像一个提供了应用的运行环境，通过这个来访问资源
    private List<Event> data;

    public RecyclerViewAdapter(Context context, List<Event> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        //用来加载布局
        LayoutInflater inflater= LayoutInflater.from(context);
        view=inflater.inflate(R.layout.activity_event, parent,false);

        return new MyViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText("Titre: "+data.get(position).getTitle());
        holder.arthor.setText(data.get(position).getReporter() +" : " );
        holder.date.setText("Date: "+data.get(position).getDate());
        holder.description.setText(data.get(position).getDescription());


        //set listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用来启动其他新的Activity。 二:作为传递数据和事件的桥梁
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("Title", data.get(position).getTitle());
                intent.putExtra("Name", data.get(position).getReporter());
                intent.putExtra("Category", data.get(position).getCategory());
                intent.putExtra("Importance", data.get(position).getImportance());
                intent.putExtra("Description", data.get(position).getDescription());
                intent.putExtra("State", data.get(position).getState());
                intent.putExtra("Date", data.get(position).getDate());
                intent.putExtra("Phone", data.get(position).getNumber());

                context.startActivity(intent);
            }
        });

    }

    /**
     * get the number of items
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * dans holder, we find the view by id, in cardview_item.xml
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, arthor,date,description;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            arthor = (TextView) itemView.findViewById(R.id.arthor);
            date = (TextView) itemView.findViewById(R.id.date);
            description =(TextView) itemView.findViewById(R.id.description);
            cardView=(CardView)itemView.findViewById(R.id.main);
        }
    }
}
