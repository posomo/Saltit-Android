package com.posomo.saltit.common_ui.recyclerview

import com.posomo.saltit.model.ViewType

interface VHEventListener {
	fun onClick(viewType: ViewType, component: VHComponent = VHComponent.DEFAULT)
}