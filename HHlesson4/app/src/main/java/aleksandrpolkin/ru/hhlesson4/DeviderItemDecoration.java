package aleksandrpolkin.ru.hhlesson4;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DeviderItemDecoration extends RecyclerView.ItemDecoration {

    private int devided;
    public final int SQUARE_VIEW = 0;
    public final int FLAT_VIEW = 1;

    public DeviderItemDecoration(int divided){
        this.devided = divided;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int viewType = parent.getAdapter().getItemViewType(position);

        if (viewType == SQUARE_VIEW) {
            if (position < 2) {
                outRect.top = devided * 3;
            }else{outRect.top = devided;}
            if (position % 2 == 0) {
                outRect.right = devided;
                outRect.left = devided * 2;
            } else {
                outRect.right = devided * 2;
                outRect.left = devided;
            }
            outRect.bottom = devided;
        }else{
            outRect.set(devided * 2,devided,devided * 2,devided);

        }
    }

}
