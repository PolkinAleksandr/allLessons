package aleksandrpolkin.ru.lesson6;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInPager extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_TEXT_PICTURES = "arg_text_pic";

    private int pageNumber;
    private String text;
    private OnMyGetTextForActivity onMyGetTextForActivity;

    static FragmentInPager newInstance(int picture, String text) {
        FragmentInPager fragment = new FragmentInPager();
        Bundle arguments = new Bundle();
        arguments.putString(FragmentInPager.ARGUMENT_TEXT_PICTURES, text);
        arguments.putInt(FragmentInPager.ARGUMENT_PAGE_NUMBER,picture);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onMyGetTextForActivity = (OnMyGetTextForActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            text = getArguments().getString(ARGUMENT_TEXT_PICTURES);
            pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_pager, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(pageNumber);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDataPass(text);
            }
        });
        TextView textView = view.findViewById(R.id.textView_name_picture);
        textView.setText(text);
        return view;
    }

    public void onDataPass(String text){
        if(onMyGetTextForActivity != null) {
            Log.d("MyTag", "onMyGetTextForActivity");
            onMyGetTextForActivity.setTextForActivity(text);
        }
    }
}
