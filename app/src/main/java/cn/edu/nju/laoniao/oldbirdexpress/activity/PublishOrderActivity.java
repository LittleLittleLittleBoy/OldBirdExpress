package cn.edu.nju.laoniao.oldbirdexpress.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.nju.laoniao.oldbirdexpress.R;
import cn.edu.nju.laoniao.oldbirdexpress.model.Order;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PublishOrderActivity extends AppCompatActivity {

    private static final String TAG = "PublishOrderActivity";

    private RecyclerView recyclerView;

    private Timer timer;

    private final String url="http://172.21.18.201:8080/getNewOrders?user=b";

    private MyViewAdapter myViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_order_list);
        recyclerView=findViewById(R.id.my_publish_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(PublishOrderActivity.this));

        myViewAdapter=new MyViewAdapter();
        recyclerView.setAdapter(myViewAdapter);

        createTimerTask();
    }

    private void createTimerTask() {
        timer=new Timer();
        TimerTask alermTask=new AlarmTask();
        timer.schedule(alermTask,0,500);
    }


    public class AlarmTask extends TimerTask{

        @Override
        public void run() {
            Log.e("de", "run: " );
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    .get()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, e.getMessage());
                }

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String result=response.body().string();

                        final List<Order> stus = new ArrayList<Order>();
                        JsonParser parser = new JsonParser();//创建一个JsonParse
                        //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象，然后通过JsonElement对象生成JsonArray序列
                        JsonArray jarray = parser.parse(result).getAsJsonArray();
                        Gson gson=new Gson();
                        //通过JsonArray把对象从中提取到List集合中来
                        for (JsonElement jsonElement : jarray) {
                            stus.add(gson.fromJson(jsonElement, Order.class));
                        }
                        if (stus.size()!=myViewAdapter.getItemCount()){
                            runOnUiThread(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void run() {
                                    myViewAdapter.setOrders(stus);
                                    myViewAdapter.notifyDataSetChanged();
                                }
                            });

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            });

        }
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
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(PublishOrderActivity.this);

            return new ViewHolder(layoutInflater, parent);
        }

        public void setOrders(List<Order> list){
            this.orders=list;
        }

        public List<Order> getOrders() {
            return orders;
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
