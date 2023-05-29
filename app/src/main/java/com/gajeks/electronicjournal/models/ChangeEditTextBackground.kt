package com.gajeks.electronicjournal.models

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.util.Patterns
import android.widget.EditText
import com.gajeks.electronicjournal.R

fun EditText.changeBackground() {
    this.setOnFocusChangeListener { v, hasFocus ->
        setBackground(hasFocus or this.text.isNotEmpty())
    }
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun EditText.setBackground(isActive: Boolean) {
    val drawable = this.background as GradientDrawable
    drawable.mutate()
    val px = resources.getDimensionPixelSize(R.dimen.stroke_width)
    val color: Int = if (isActive)
        resources.getColor(R.color.accent, null)
    else
        resources.getColor(R.color.middle_gray, null)
    drawable.setStroke(px, color)
    this.post {
        this.compoundDrawables.forEach {
            it?.colorFilter = PorterDuffColorFilter(
                color,
                PorterDuff.Mode.SRC_IN
            )
        }
    }
}
