package com.posomo.saltit.place_info

sealed class PlaceInfoListItem(val viewType: Int) {

    enum class ViewType {
        MAIN_IMAGE,
        HEADER,
        TAB,
        BUDGET_MENU_HEADER,
        MENU_ITEM,
        SUB_MENU_HEADER,
        MENU_FOOTER,
        EMPTY,
        MENU_CATEGORY,
        REVIEW,
        DECOR,
        FOOTER
    }

}
