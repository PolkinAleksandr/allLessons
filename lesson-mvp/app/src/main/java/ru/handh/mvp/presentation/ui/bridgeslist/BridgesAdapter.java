package ru.handh.mvp.presentation.ui.bridgeslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.handh.mvp.R;
import ru.handh.mvp.data.model.FinalBridge;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesAdapter extends RecyclerView.Adapter<BridgesAdapter.BridgeViewHolder> {

    private List<FinalBridge> bridges = new ArrayList<>();


    @Override
    public BridgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BridgeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bridge, parent, false));
    }

    @Override
    public void onBindViewHolder(BridgeViewHolder holder, int position) {
        holder.bind(bridges.get(position));
    }

    @Override
    public int getItemCount() {
        return bridges.size();
    }

    public void setBridges(List<FinalBridge> bridges) {
        this.bridges = bridges;
        notifyDataSetChanged();
    }

    static class BridgeViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewTime;
        private ImageView image;
        private BridgesChoiceGetId bridgesChoiceGetId;

        public BridgeViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textView_name);
            this.textViewTime = itemView.findViewById(R.id.textView_time);
            this.image = itemView.findViewById(R.id.image_state_bridge);
            bridgesChoiceGetId = (BridgesChoiceGetId) itemView.getContext();
        }

        public void bind(FinalBridge bridge) {
            textViewTitle.setText(bridge.getName());
            textViewTime.setText(bridge.getTime());
            image.setImageResource(bridge.getPic());
            itemView.setOnClickListener(v ->
                    bridgesChoiceGetId.transferId(bridge.getId()));
        }

    }
}
