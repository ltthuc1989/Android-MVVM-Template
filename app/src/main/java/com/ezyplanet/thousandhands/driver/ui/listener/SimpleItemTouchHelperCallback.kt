package com.ezyplanet.thousandhands.driver.ui.listener

import android.graphics.Canvas
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SimpleItemTouchHelperCallback(private val mAdapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {
    interface ItemTouchHelperAdapter {
        fun onItemDismiss(i: Int)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            ItemTouchHelper.Callback.makeMovementFlags(15, 0)
        } else ItemTouchHelper.Callback.makeMovementFlags(3, 48)
        //        if (viewHolder instanceof DashboardHeaderVH) {
        //            return Callback.makeMovementFlags(0, 0);
        //        }
        //        if (viewHolder instanceof DashboardEmptyVH) {
        //            return Callback.makeMovementFlags(0, 0);
        //        }
    }

    //
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder2: RecyclerView.ViewHolder): Boolean {
        return viewHolder.itemViewType == viewHolder2.itemViewType
    }

    //
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        this.mAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, f: Float, f2: Float, i: Int, z: Boolean) {
        if (i == 1) {
           // viewHolder.itemView.alpha = ALPHA_FULL - Math.abs(f) / viewHolder.itemView.width.toFloat()
            //viewHolder.itemView.translationX = f
            return
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z)
    }

    companion object {
        val ALPHA_FULL = 1.0f
    }

    //    public void onSelectedChanged(ViewHolder viewHolder, int i) {
    //        if (i != 0 && (viewHolder instanceof ItemTouchHelperViewHolder)) {
    //            ((ItemTouchHelperViewHolder) viewHolder).onItemSelected();
    //        }
    //        super.onSelectedChanged(viewHolder, i);
    //    }
    //
    //    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
    //        super.clearView(recyclerView, viewHolder);
    //        viewHolder.itemView.setAlpha(ALPHA_FULL);
    //        if (viewHolder instanceof ItemTouchHelperViewHolder) {
    //            ((ItemTouchHelperViewHolder) viewHolder).onItemClear();
    //        }
    //    }
}