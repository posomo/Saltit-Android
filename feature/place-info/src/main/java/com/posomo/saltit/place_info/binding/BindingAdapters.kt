package com.posomo.saltit.place_info.binding

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.posomo.saltit.common_ui.R
import java.text.DecimalFormat

@BindingAdapter("focusedBackground")
fun TextView.bindFocusedBackground(isSelected: Boolean) {
    if (isSelected) {
        setTextColor(resources.getColor(R.color.saltit_blue_text, null))
        typeface = ResourcesCompat.getFont(context, R.font.mainfont_bold)
        setBackgroundResource(R.drawable.bg_tab_active)

    } else {
        setTextColor(resources.getColor(R.color.saltit_gray_02, null))
        typeface = ResourcesCompat.getFont(context, R.font.mainfont_regular)
        setBackgroundResource(R.drawable.bg_tab_inactive)
    }
}

@BindingAdapter("savingHelper")
fun TextView.bindSavingHelper(saving: Int) {
    if (saving < 0) {
        visibility = View.GONE
        return
    }
    val priceFormat = DecimalFormat("#,###").format(saving)
    visibility = View.VISIBLE
    text = String.format(
        resources.getString(com.posomo.saltit.place_info.R.string.budget_menu_saving_helper),
        priceFormat
    )
    val ssb = SpannableStringBuilder(text).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            8,
            text.length - 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    text = ssb
}

@BindingAdapter("price")
fun TextView.bindPrice(data: Int) {
    val priceFormat = "${DecimalFormat("#,###").format(data)} 원"
    text = priceFormat
}