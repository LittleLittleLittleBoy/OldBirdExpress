package cn.edu.nju.laoniao.oldbirdexpress;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.nju.laoniao.oldbirdexpress.model.Order;

public class PublishOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_order_list);
        recyclerView=findViewById(R.id.my_publish_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(PublishOrderActivity.this));
        recyclerView.setAdapter(new MyViewAdapter());
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView list_item_user;
        private TextView list_item_school;
        private TextView list_item_from;
        private TextView list_item_time;
        private TextView list_item_to;
        private TextView list_item_createTime;
        private TextView list_item_money;

        private Order morder;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.order_list_item, parent, false));
            list_item_user=itemView.findViewById(R.id.list_item_user);
            list_item_school=itemView.findViewById(R.id.list_item_school);
            list_item_from=itemView.findViewById(R.id.list_item_from);
            list_item_time=itemView.findViewById(R.id.list_item_time);
            list_item_to=itemView.findViewById(R.id.list_item_to);
            list_item_createTime=itemView.findViewById(R.id.list_item_createTime);
            list_item_money=itemView.findViewById(R.id.list_item_money);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(PublishOrderActivity.this,OrderDetailActivity.class);
                    startActivity(intent);
                }
            });
        }
        public void bind(Order order){
            morder=order;
            list_item_user.setText(order.getUser());
            list_item_school.setText(order.getSchool());
            list_item_from.setText(order.getFromPlace());
            list_item_time.setText(order.getFromTime().getHour()+":"+order.getFromTime().getMinute()+"-"+order.getToTime().getHour()+":"+order.getToTime().getMinute());
            list_item_to.setText(order.getToPlace());
            list_item_createTime.setText((LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))-order.getCreateTime().toEpochSecond(ZoneOffset.of("+8")))/(60*1000)+1+"分钟前");
            list_item_money.setText("赚"+order.getPrice()+"元");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private class MyViewAdapter extends RecyclerView.Adapter<ViewHolder>{

        List<Order> orders=new ArrayList<>();
        {
            orders.add(new Order("0001","小明","","那经大学","菜鸟一站","南苑六社", LocalDateTime.now(),LocalTime.now(),LocalTime.now(),100,"enen","ahah",0));
            orders.add(new Order("0002","小明","","那经大学","菜鸟一站","南苑六社",LocalDateTime.now(),LocalTime.now(),LocalTime.now(),100,"","",0));

        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(PublishOrderActivity.this);

            return new ViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Order crime = orders.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}
