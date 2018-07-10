package aleksandrpolkin.ru.lesson6;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment2 extends Fragment {

    static final String FRAGMENT_TAG = "fragment2";
    private Fragment2Child1 fragment;
    private FragmentTransaction transaction;

    public static Fragment2 createInstance(){
        return new Fragment2();
    }


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_2, container, false);
        final Button buttonOpenPager = v.findViewById(R.id.button_fragment2);
        buttonOpenPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonOpenPager.getText().toString().equals(getResources().getString(R.string.text_open_banner))){
                    //open banner
                    fragment = Fragment2Child1.newInstance();
                    transaction = getChildFragmentManager().beginTransaction()
                            .add(R.id.container_fragment2,fragment, Fragment2Child1.ARGUMENT_CHILD1);
                    transaction.commit();
                    buttonOpenPager.setText(getResources().getString(R.string.text_close_banner));
                }else{
                    // close banner
                    transaction = getChildFragmentManager().beginTransaction()
                            .remove(fragment);
                    transaction.commit();
                    buttonOpenPager.setText(getResources().getString(R.string.text_open_banner));
                }
            }
        });
        return v;

    }

}
