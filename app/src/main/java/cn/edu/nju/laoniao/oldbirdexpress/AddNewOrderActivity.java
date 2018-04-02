package cn.edu.nju.laoniao.oldbirdexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddNewOrderActivity extends AppCompatActivity {
    private static final String TAG = "AddNewOrderActivity";

    private static final String url="http:172.19.112.245:8080/addNewOrder?user=a";

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_order);

        addButton=findViewById(R.id.addButton);

        init();


    }

    private void init() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            String result=response.body().string();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                });

            }
        });
    }
}
