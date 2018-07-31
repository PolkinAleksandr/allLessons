package aleksandrpolkin.ru.lesson8;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import data.Notebook;

public class RecycleNoteAdapter extends RecyclerView.Adapter<RecycleNoteAdapter.MyViewHolder> {

    private List<Notebook> notebooks;

    RecycleNoteAdapter(List<Notebook> notebooks) {
        this.notebooks = notebooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setNotebook(notebooks.get(position));
    }

    @Override
    public int getItemCount() {
        return notebooks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView noteText;
        private OnMyClick onMyClick;

        MyViewHolder(final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            noteText = itemView.findViewById(R.id.note);

        }

        void setNotebook(final Notebook notebook) {
            titleText.setText(notebook.getTitleNote());
            noteText.setText(notebook.getNote());
            editVisible(titleText, notebook.getTitleNote());
            editVisible(noteText, notebook.getNote());
            itemView.setBackgroundColor(itemView.getResources().getColor(notebook.getColorBackground()));
            onMyClick = (OnMyClick) itemView.getContext();
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onMyClick.setOnMyLongClick(notebook);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyClick.setOnMyClick(notebook);
                }
            });
        }

        void editVisible(View view, String text) {
            if (text.equals("")) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
