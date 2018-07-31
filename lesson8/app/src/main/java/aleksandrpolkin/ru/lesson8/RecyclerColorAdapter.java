package aleksandrpolkin.ru.lesson8;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

public class RecyclerColorAdapter extends RecyclerView.Adapter<RecyclerColorAdapter.ColorViewHolder> {

    private List<Integer> myColorList;
    private int checkColor;

    RecyclerColorAdapter(List<Integer> myColorList, int checkColor) {
        this.checkColor = checkColor;
        this.myColorList = myColorList;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_color, parent, false);
        return new ColorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        holder.setViewColor(myColorList.get(position), checkColor);
    }

    @Override
    public int getItemCount() {
        return myColorList.size();
    }

    class ColorViewHolder extends RecyclerView.ViewHolder {

        ImageButton buttonColor;
        OnMyColorClick onMyColorClick;

        ColorViewHolder(View itemView) {
            super(itemView);
            buttonColor = itemView.findViewById(R.id.button_color);

        }

        void setViewColor(final int color, int checkColor) {
            LayerDrawable layerDrawable = (LayerDrawable) itemView.getResources()
                    .getDrawable(R.drawable.alert_color);
            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable
                    .findDrawableByLayerId(R.id.oval);
            gradientDrawable.setColor(itemView.getResources().getColor(color));
            buttonColor.setBackground(layerDrawable);
            if (color == checkColor) {
                buttonColor.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_check));
            }
            onMyColorClick = (OnMyColorClick) itemView.getContext();
            buttonColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyColorClick.setOnMyClick(color);
                }
            });
        }
    }
}
