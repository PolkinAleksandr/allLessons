package aleksandrpolkin.ru.lesson6;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment1 extends Fragment /*implements MainActivity.ChangeTextInFragment*/{

    static final String FRAGMENT_TAG = "fragment1";
    static final String ARGUMENT_FRAGMENT1 = "arg_fr1";
    private String textFragment;

    public static Fragment1 createInstance(String text){
        Fragment1 fragment1 = new Fragment1();
        Bundle arg = new Bundle();
        arg.putString(ARGUMENT_FRAGMENT1, text);
        fragment1.setArguments(arg);
        return fragment1;
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        textFragment = getArguments().getString(ARGUMENT_FRAGMENT1);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_1, container, false);
        TextView textView = v.findViewById(R.id.textView_fragment1);
        textView.setText(textFragment);
        return v;
    }
}
