package aleksandrpolkin.ru.lesson6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

class MyFragmentPagerAdapter extends FragmentStatePagerAdapter implements Fragment2Child1.OnMySetPicture {

    private List<Pictures> pictures;

    MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       // List<Pictures> pictures = onMySetPicture.setPicture();
        if(pictures != null) {
            return FragmentInPager.newInstance(pictures.get(position).getId(), pictures.get(position).getNumber());
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return Fragment2Child1.PAGE_COUNT;
    }


    @Override
    public void setPicture(List<Pictures> picture) {
        this.pictures = picture;
    }
}
