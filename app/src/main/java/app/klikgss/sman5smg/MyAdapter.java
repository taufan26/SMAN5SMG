package app.klikgss.sman5smg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MyAdapter extends PagerAdapter {
private Context context;
private ArrayList<MyModel> modelArrayList;

    //constructor
    public MyAdapter(Context context, ArrayList<MyModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;

    }

    @Override
    public int getCount() {
        return modelArrayList.size();// Return size of item in list
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_item,container,false);

        ImageView bannerTv = view.findViewById(R.id.bannerTV);
        TextView titleTv = view.findViewById(R.id.titleTV);
        TextView descriptionTv = view.findViewById(R.id.descriptionTV);
        TextView dateTv = view.findViewById(R.id.dateTV);

        MyModel model = modelArrayList.get(position);
        final String title = model.getTitle();
        final String description = model.getDescription();
        final String date = model.getDate();
        int image = model.getImage();

        bannerTv.setImageResource(image);
        titleTv.setText(title);
        descriptionTv.setText(description);
        dateTv.setText(date);
        //
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, title +"\n" + description + "\n"
                + date, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view, position);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
