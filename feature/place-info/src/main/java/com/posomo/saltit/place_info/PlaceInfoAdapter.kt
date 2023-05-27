package com.posomo.saltit.place_info

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.posomo.saltit.place_info.databinding.ListItemDecorGrayBinding
import com.posomo.saltit.place_info.databinding.ListItemFooterBinding
import com.posomo.saltit.place_info.databinding.ListItemHeaderBinding
import com.posomo.saltit.place_info.databinding.ListItemMainImageBinding
import com.posomo.saltit.place_info.databinding.ListItemMenuCategoryBinding
import com.posomo.saltit.place_info.databinding.ListItemMenuFooterBinding
import com.posomo.saltit.place_info.databinding.ListItemMenuHeaderBinding
import com.posomo.saltit.place_info.databinding.ListItemMenuItemBinding
import com.posomo.saltit.place_info.databinding.ListItemReviewBinding
import com.posomo.saltit.place_info.databinding.ListItemTabBinding

class PlaceInfoAdapter : ListAdapter<PlaceInfoListItem, RecyclerView.ViewHolder>(PlaceInfoDiffUtil()) {

    private lateinit var context: Context
    private var userBudget: Int = 0

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun getItemViewType(position: Int): Int = currentList[position].viewType

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (PlaceInfoListItem.ViewType.values()[viewType]) {
            PlaceInfoListItem.ViewType.MAIN_IMAGE -> MainImageViewHolder(ListItemMainImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.HEADER -> HeaderViewHolder(ListItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.TAB -> TabViewHolder(ListItemTabBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.MENU_HEADER -> MenuHeaderViewHolder(ListItemMenuHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.MENU_ITEM -> MenuItemViewHolder(ListItemMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.MENU_FOOTER -> MenuFooterViewHolder(ListItemMenuFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.MENU_CATEGORY -> MenuCategoryViewHolder(ListItemMenuCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.REVIEW -> ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.DECOR -> DecorViewHolder(ListItemDecorGrayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            PlaceInfoListItem.ViewType.FOOTER -> FooterViewHolder(ListItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommonViewHolder<PlaceInfoListItem>).onBindView(currentList[position])
    }

    private abstract class CommonViewHolder<E: PlaceInfoListItem>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun onBindView(item: E)
    }

    private inner class MainImageViewHolder(private val binding: ListItemMainImageBinding) : CommonViewHolder<PlaceInfoListItem.MainImage>(binding) {
        override fun onBindView(item: PlaceInfoListItem.MainImage) = with(binding) {
            url = item.imageUrl
        }
    }

    private inner class HeaderViewHolder(private val binding: ListItemHeaderBinding) : CommonViewHolder<PlaceInfoListItem.Header>(binding) {
        override fun onBindView(item: PlaceInfoListItem.Header) = with(binding) {
            model = item.item
            shareClickEvent = View.OnClickListener {
                Toast.makeText(context, "서비스 준비중입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private inner class TabViewHolder(private val binding: ListItemTabBinding) : CommonViewHolder<PlaceInfoListItem.Tab>(binding) {

        private var index = 0
        override fun onBindView(item: PlaceInfoListItem.Tab) = with(binding) {
            selectedItem = index
            setMenuClickListener { index = 0 }
            setInfoClickListener { index = 1 }
        }
    }

    private inner class MenuHeaderViewHolder(private val binding: ListItemMenuHeaderBinding) : CommonViewHolder<PlaceInfoListItem.MenuHeader>(binding) {
        override fun onBindView(item: PlaceInfoListItem.MenuHeader) = with(binding){
            title = item.title
        }
    }

    private inner class MenuItemViewHolder(private val binding: ListItemMenuItemBinding) : CommonViewHolder<PlaceInfoListItem.MenuItem>(binding) {

        override fun onBindView(item: PlaceInfoListItem.MenuItem) = with(binding){
            model = item.menu
            budget = userBudget
            tvRecommend.isGone = item.menu.price > userBudget
        }
    }

    private inner class MenuFooterViewHolder(binding: ListItemMenuFooterBinding) : CommonViewHolder<PlaceInfoListItem.MenuFooter>(binding) {
        override fun onBindView(item: PlaceInfoListItem.MenuFooter) = Unit
    }

    private inner class MenuCategoryViewHolder(private val binding: ListItemMenuCategoryBinding) : CommonViewHolder<PlaceInfoListItem.MenuCategory>(binding) {
        override fun onBindView(item: PlaceInfoListItem.MenuCategory) = with(binding){
            title = item.category
        }
    }

    private inner class ReviewViewHolder(private val binding: ListItemReviewBinding) : CommonViewHolder<PlaceInfoListItem.Review>(binding) {
        override fun onBindView(item: PlaceInfoListItem.Review) = with(binding){
            setClickListener {
                val intent = Intent(it.context, CommonWebViewActivity::class.java)
                intent.putExtra("url", item.dinningCodeUrl)
                context.startActivity(intent)
            }
        }
    }

    private inner class DecorViewHolder(binding: ListItemDecorGrayBinding) : CommonViewHolder<PlaceInfoListItem.DecorGray>(binding) {
        override fun onBindView(item: PlaceInfoListItem.DecorGray) = Unit
    }

    private inner class FooterViewHolder(binding: ListItemFooterBinding) : CommonViewHolder<PlaceInfoListItem.Footer>(binding) {
        override fun onBindView(item: PlaceInfoListItem.Footer) = Unit
    }

    private class PlaceInfoDiffUtil : DiffUtil.ItemCallback<PlaceInfoListItem>() {
        override fun areItemsTheSame(oldItem: PlaceInfoListItem, newItem: PlaceInfoListItem): Boolean {
            return if (oldItem is PlaceInfoListItem.MenuHeader && newItem is PlaceInfoListItem.MenuHeader) {
                oldItem.title == newItem.title
            } else if (oldItem is PlaceInfoListItem.MenuItem && newItem is PlaceInfoListItem.MenuItem) {
                oldItem.menu.id == newItem.menu.id
            } else if (oldItem is PlaceInfoListItem.MenuCategory && newItem is PlaceInfoListItem.MenuCategory) {
                oldItem.category == newItem.category
            } else {
                oldItem.viewType == newItem.viewType
            }
        }

        override fun areContentsTheSame(oldItem: PlaceInfoListItem, newItem: PlaceInfoListItem): Boolean {
            return oldItem == newItem
        }
    }
}