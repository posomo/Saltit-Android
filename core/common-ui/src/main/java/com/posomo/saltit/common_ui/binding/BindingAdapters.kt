package com.posomo.saltit.common_ui.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun AppCompatImageView.bindImageUrl(imageUrl: String?) {
	if (imageUrl.isNullOrBlank()) return

	Glide.with(context)
		.load(imageUrl)
		.into(this)
}

@BindingAdapter("itemDecoration")
fun RecyclerView.bindsItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
	this.addItemDecoration(itemDecoration)
}