package aleksandrpolkin.ru.lesson7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import aleksandrpolkin.ru.lesson7.data.Divorces;
import aleksandrpolkin.ru.lesson7.data.ObjectsData;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private List<ObjectsData> objectsData;

    RecycleAdapter(List<ObjectsData> objectsData){
        this.objectsData = objectsData;
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {

        holder.setViewHolder(objectsData.get(position));

    }

    @Override
    public int getItemCount() {
        return objectsData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewTime;
        private ImageView imageViewBig;
        private ImageView imageViewSmall;
        private final int OPEN_BRIGHT = 100;
        private final int CLOSE_BRIGHT = 101;
        private final int WAIT_BRIDGE = 102;
        private int bridge;
        String finalTime;
        int finalPic;
        ObjectsData objectsData;
        private OnMyGetTextForActivity onMyGetTextForActivity;


        public MyViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewTime = itemView.findViewById(R.id.textView_time);
            imageViewBig = itemView.findViewById(R.id.imageView);
            imageViewSmall = itemView.findViewById(R.id.imageView2);
        }

        @SuppressLint("SetTextI18n")
        void setViewHolder(final ObjectsData objectsData){
            textViewName.setText(objectsData.getName());
//            Divorces divorces = objectsData.getDivorces().get(0);
            List<Divorces> divorces = objectsData.getDivorces();
            String time = "";
            int pic = 0;
            bridge = CLOSE_BRIGHT;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < divorces.size(); i++)
            {
                time = time + setStringDateTime(setTimeDateFormat(divorces.get(i).getStart())) + " - "
                        + setStringDateTime(setTimeDateFormat(divorces.get(i).getEnd())) + "  ";
                cal.setTime(setTimeDateFormat(divorces.get(i).getStart())); // sets calendar time/date
                cal.add(Calendar.HOUR_OF_DAY, -1); // adds one hour
                if(setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getStart())) >= 0){
                    if(setTimeDateFormat(getTimeLong()).compareTo(setTimeDateFormat(divorces.get(i).getEnd())) <= 0){
                        bridge = OPEN_BRIGHT;
                    }
                }else{
                    if(setTimeDateFormat(getTimeLong()).compareTo(cal.getTime()) >=0 && bridge != OPEN_BRIGHT){
                        bridge = WAIT_BRIDGE;
                    }
                }
            }

            textViewTime.setText(time);
            switch (bridge){
                case OPEN_BRIGHT:
                    pic = R.drawable.ic_brige_late;
                    break;
                case WAIT_BRIDGE:
                    pic = R.drawable.ic_brige_soon;
                    break;
                case CLOSE_BRIGHT:
                    pic = R.drawable.ic_brige_normal;
                    break;
            }
            if(pic != 0)
            imageViewBig.setImageResource(pic);

            finalTime = time;
            finalPic = pic;
            onMyGetTextForActivity = (OnMyGetTextForActivity) itemView.getContext();
                itemView.setOnClickListener(v ->
                        onMyGetTextForActivity.setTextForActivity(objectsData, finalTime, finalPic));
        }

        Date setTimeDateFormat(String text) {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("HH:mm:ss");
            Date docDate = null;
            try {
                docDate = format.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return docDate;
        }

        String setStringDateTime(Date date){
           return new SimpleDateFormat( "HH:mm", Locale.getDefault()).format(date);
        }

        public static String getTimeLong() {
            return new SimpleDateFormat( "HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        }
    }
}
