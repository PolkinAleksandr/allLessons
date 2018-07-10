package aleksandrpolkin.ru.lesson6;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2Child1 extends Fragment {

    static final int PAGE_COUNT = 3;
    static final String ARGUMENT_CHILD1 = "arg_child";
    private List<Pictures> pictures;


    public static Fragment2Child1 newInstance(){
        return new Fragment2Child1();
    }

    public Fragment2Child1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pictures pic = new Pictures();
        pictures = new ArrayList<>();
        pic.setPictures(R.drawable.img_1, "Картинка 1");
        pictures.add(pic);
        pic = new Pictures();
        pic.setPictures(R.drawable.pic2, "Картинка 2");
        pictures.add(pic);
        pic = new Pictures();
        pic.setPictures(R.drawable.picture3, "Картинка 3");
        pictures.add(pic);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_child1, container, false);
        ViewPager pager = v.findViewById(R.id.pagerView);

        TabLayout tabLayout = v.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(pager, true);

        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        OnMySetPicture onMySetPicture = (OnMySetPicture) pagerAdapter;
        onMySetPicture.setPicture(pictures);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return v;
    }


    interface OnMySetPicture{
        void setPicture(List<Pictures> picture);
    }
}