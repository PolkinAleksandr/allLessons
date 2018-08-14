package aleksandrpolkin.ru.lesson7;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
        String finalTime;
        int finalPic;
        private OnMyGetTextForActivity onMyGetTextForActivity;


        MyViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView_name);
            textViewTime = itemView.findViewById(R.id.textView_time);
            imageViewBig = itemView.findViewById(R.id.imageView);
            imageViewSmall = itemView.findViewById(R.id.imageView2);
        }

        @SuppressLint("SetTextI18n")
        void setViewHolder(final ObjectsData objectsData){
            textViewName.setText(objectsData.getName());

            List<Divorces> divorces = objectsData.getDivorces();

            CheckTime checkTime = new CheckTime();
            finalPic = checkTime.checkTimeDrawable(divorces);
            finalTime = checkTime.getOpenBridgeTime(divorces);
            textViewTime.setText(finalTime);
            if(finalPic != 0)
            imageViewBig.setImageResource(finalPic);
            onMyGetTextForActivity = (OnMyGetTextForActivity) itemView.getContext();
                itemView.setOnClickListener(v ->
                        onMyGetTextForActivity.setTextForActivity(objectsData, finalTime, finalPic, OnMyGetTextForActivity.recycler));
        }

    }
}
