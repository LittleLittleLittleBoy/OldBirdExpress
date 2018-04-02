package cn.edu.nju.laoniao.oldbirdexpress.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.nju.laoniao.oldbirdexpress.NavigationActivity;
import cn.edu.nju.laoniao.oldbirdexpress.R;
import cn.edu.nju.laoniao.oldbirdexpress.user.UserName;

public class LoginActivity extends AppCompatActivity {

    private Button buttona;
    private Button buttonb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttona=findViewById(R.id.login_a);
        buttonb=findViewById(R.id.login_b);

        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                UserName.user="a";
                startActivity(intent);
            }
        });
        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                UserName.user="b";
                startActivity(intent);
            }
        });
    }
}
