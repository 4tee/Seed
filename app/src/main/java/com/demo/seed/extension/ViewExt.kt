package com.demo.seed.extension

import android.os.SystemClock
import android.view.View

private const val ON_CLICK_DELAY = 1000

fun View.setDebounceClickedListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = DebounceClickedListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

class DebounceClickedListener(
    private var defaultInterval: Int = ON_CLICK_DELAY,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}
