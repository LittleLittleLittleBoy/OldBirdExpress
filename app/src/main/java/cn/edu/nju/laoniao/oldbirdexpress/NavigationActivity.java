package cn.edu.nju.laoniao.oldbirdexpress;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.edu.nju.laoniao.oldbirdexpress.activity.ChatFragment;
import cn.edu.nju.laoniao.oldbirdexpress.activity.ExpressDetailFragment;
import cn.edu.nju.laoniao.oldbirdexpress.activity.InfoFragment;
import cn.edu.nju.laoniao.oldbirdexpress.activity.PublishOrderFragment;
import cn.edu.nju.laoniao.oldbirdexpress.activity.ViewPagerAdapter;
import cn.edu.nju.laoniao.oldbirdexpress.helper.BottomNavigationViewHelper;

public class NavigationActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_email:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_bike:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_message:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_info:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ExpressDetailFragment());
        adapter.addFragment(new PublishOrderFragment());
        adapter.addFragment(new ChatFragment());
        adapter.addFragment(new InfoFragment());
        viewPager.setAdapter(adapter);
    }

}
