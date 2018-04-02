package cn.edu.nju.laoniao.oldbirdexpress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.nju.laoniao.oldbirdexpress.AddTaskActivity;
import cn.edu.nju.laoniao.oldbirdexpress.R;

public class ExpressDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bike_fragment,container,false);
        Button button=view.findViewById(R.id.show_create_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
