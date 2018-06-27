package aleksandrpolkin.ru.hhlesson4;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {


    private List<DatasetFull> datasetFulls;
    private final int SQUARE_VIEW = 0;
    private final int FLAT_VIEW = 1;

    MyAdapter(List<DatasetFull> datasetFulls){
        this.datasetFulls = datasetFulls;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == FLAT_VIEW){
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_flat, parent,false);
            return new ViewHolder(v);
        }else{
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent,false);
        return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDataset(datasetFulls.get(position));
    }

    @Override
    public int getItemCount() {
        return datasetFulls.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private TextView mTextView2;
        private ImageView imageView;

        ViewHolder(View itemView){
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }


        void setDataset(final DatasetFull datasetFull){
            mTextView.setText(datasetFull.getName());
            mTextView2.setText(datasetFull.getDescription());
            imageView.setImageResource(datasetFull.getImageView());
            if(datasetFull.getTextWarning()){
                mTextView2.setTextColor(Color.parseColor("#ff4242"));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, datasetFull.getName(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
            if (datasetFulls.get(position).getDescription() != null &&
                datasetFulls.get(position + 1).getDescription() == null &&
                position % 2 == 0) {
                    return FLAT_VIEW;
            } else if (datasetFulls.get(position).getDescription() != null) {
                return SQUARE_VIEW;
                }else return FLAT_VIEW;
        }
    }

