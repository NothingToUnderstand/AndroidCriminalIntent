package com.example.criminalintent

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.fragments.viewmodel.CrimeListViewModel


class TouchHelper(
    private val crimeListViewModel: CrimeListViewModel,
    private val context: Context
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    var background = ColorDrawable(Color.RED)
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        crimeListViewModel.crimeListLiveData.value?.let { crimeListViewModel.delete(it[viewHolder.adapterPosition]) }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView: View = viewHolder.itemView
        val isCancelled = !isCurrentlyActive && dX.toInt() == 0 && dY.toInt() == 0
        if (isCancelled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, false)
            return
        }
        val mBackground = ColorDrawable()
        val deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_forever);
        val intrinsicWidth = deleteDrawable!!.intrinsicWidth;
        val intrinsicHeight = deleteDrawable.intrinsicHeight;
        mBackground.color = Color.rgb(163, 25, 48)

        val deleteIconTop: Int = itemView.top + (itemView.height - intrinsicHeight) / 2
        val deleteIconBottom = deleteIconTop + intrinsicHeight
        val deleteIconMargin: Int = (itemView.height - intrinsicHeight) / 2
        val deleteIconLeft: Int
        val deleteIconRight:Int

        if (dX > 0) {
            mBackground.setBounds(
                itemView.left,
                itemView.top,
                itemView.right + dX.toInt(),
                itemView.bottom
            )
            deleteIconLeft = itemView.left+deleteIconMargin
            deleteIconRight = itemView.left+deleteIconMargin + intrinsicWidth
        } else {
            mBackground.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            deleteIconRight = itemView.right - deleteIconMargin
        }
        mBackground.draw(c)
        deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        deleteDrawable.draw(c);



        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, Paint())
    }
}