package com.posomo.saltit.place_info

import com.posomo.saltit.model.domain.RestaurantDetail
import com.posomo.saltit.model.dto.MenuDto

sealed class PlaceInfoListItem(val viewType: Int) {

    enum class ViewType {
        MAIN_IMAGE,
        HEADER,
        TAB,
        MENU_HEADER,
        MENU_ITEM,
        MENU_FOOTER,
        MENU_CATEGORY,
        REVIEW,
        DECOR,
        FOOTER
    }

    data class MainImage(
        val imageUrl: String
    ) : PlaceInfoListItem(ViewType.MAIN_IMAGE.ordinal)

    data class Header(
        val item: RestaurantDetail
    ) : PlaceInfoListItem(ViewType.HEADER.ordinal)

    object Tab : PlaceInfoListItem(ViewType.TAB.ordinal)

    data class MenuHeader(
        val title: String
    ) : PlaceInfoListItem(ViewType.MENU_HEADER.ordinal)

    data class MenuItem(
        val menu: MenuDto
    ) : PlaceInfoListItem(ViewType.MENU_ITEM.ordinal)

    object MenuFooter : PlaceInfoListItem(ViewType.MENU_FOOTER.ordinal)

    data class MenuCategory(
        val category: String
    ) : PlaceInfoListItem(ViewType.MENU_CATEGORY.ordinal)

    data class Review(
        val dinningCodeUrl: String
    ) : PlaceInfoListItem(ViewType.REVIEW.ordinal)

    object DecorGray : PlaceInfoListItem(ViewType.DECOR.ordinal)

    object Footer : PlaceInfoListItem(ViewType.FOOTER.ordinal)
}
