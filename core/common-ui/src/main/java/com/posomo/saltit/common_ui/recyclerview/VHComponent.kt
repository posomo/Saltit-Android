package com.posomo.saltit.common_ui.recyclerview

sealed interface VHComponent {
	sealed class SearchHeaderComponent : VHComponent {
		object AREA : VHComponent
	}
	sealed class SaltitPickStoreComponent : VHComponent {
		object FILTER : VHComponent
		object REFRESH : VHComponent
	}

	sealed class DEFAULT : VHComponent
}