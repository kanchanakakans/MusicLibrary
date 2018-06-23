package musicplayer.project.kanchan.musicplayer.View;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Kanchan on 2/17/18.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DividerItemDecoration(Drawable divider) {
        mDivider = divider;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            mDivider.draw(canvas);
        }
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = mDivider.getIntrinsicHeight();
    }
    //Creating EndOffset Taken from SimpleDivider Lib
//    private void drawOffsetHorizontal(Canvas canvas, RecyclerView parent) {
//        int parentTop = parent.getPaddingTop();
//        int parentBottom = parent.getHeight() - parent.getPaddingBottom();
//
//        View lastChild = parent.getChildAt(parent.getChildCount() - 1);
//        RecyclerView.LayoutParams lastChildLayoutParams = (RecyclerView.LayoutParams) lastChild.getLayoutParams();
//        int offsetDrawableLeft = lastChild.getRight() + lastChildLayoutParams.rightMargin;
//        int offsetDrawableRight = offsetDrawableLeft + mDivider.getIntrinsicWidth();
//
//        mDivider.setBounds(offsetDrawableLeft, parentTop, offsetDrawableRight, parentBottom);
//        mDivider.draw(canvas);
//    }

}
