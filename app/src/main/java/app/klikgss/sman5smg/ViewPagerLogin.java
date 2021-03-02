package app.klikgss.sman5smg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ViewPagerLogin extends AppCompatActivity {

    private ActionBar actionBar;

    private ViewPager viewPager;

    private ArrayList<MyModel>modelArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_login);

        actionBar = getSupportActionBar();


        viewPager = findViewById(R.id.viewPager);

        loadCards();
        //set vierpager change

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
              String title = modelArrayList.get(position).getTitle();
                actionBar.setTitle(title);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void loadCards() {

        modelArrayList = new ArrayList<>();


        modelArrayList.add(new MyModel("Learning From Home",
                "Desc",
                "696969",R.drawable.wfh));

//        modelArrayList.add(new MyModel("Learning From Home",
//                "Desc",
//                "212121",
//                R.drawable.wfh));
//        modelArrayList.add(new MyModel("Learning From Home",
//                "Desc",
//                "212121",
//                R.drawable.wfh));

        myAdapter = new MyAdapter(this, modelArrayList);

        viewPager.setAdapter(myAdapter);
        viewPager.setPadding(100,0,100,0);

    }
}