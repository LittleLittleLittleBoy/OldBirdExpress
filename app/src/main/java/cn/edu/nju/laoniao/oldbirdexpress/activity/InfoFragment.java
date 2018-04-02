package cn.edu.nju.laoniao.oldbirdexpress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.edu.nju.laoniao.oldbirdexpress.R;

public class InfoFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment,container,false);
        ImageView imageView=view.findViewById(R.id.show_my_orders);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InfoFragment.this.getContext(),MyOrdersActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
