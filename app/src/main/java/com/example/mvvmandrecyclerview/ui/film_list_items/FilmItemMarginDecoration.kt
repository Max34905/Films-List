package com.example.mvvmandrecyclerview.ui.film_list_items

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmItemMarginDecoration(private val margin : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = margin
        outRect.bottom = margin
    }
}