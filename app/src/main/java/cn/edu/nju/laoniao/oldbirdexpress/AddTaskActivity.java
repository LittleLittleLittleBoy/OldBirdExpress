package cn.edu.nju.laoniao.oldbirdexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import cn.edu.nju.laoniao.oldbirdexpress.constant.Constant;
import cn.edu.nju.laoniao.oldbirdexpress.user.UserName;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddTaskActivity extends AppCompatActivity {
    private String url= Constant.urlbase+"addNewOrder?user="+ UserName.user;
    private static final String TAG = "AddTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button=findViewById(R.id.create_new_task);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request
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
                            if (result.length()<33){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AddTaskActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AddTaskActivity.this,"创建失败",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                });
            }
        });
    }
}
